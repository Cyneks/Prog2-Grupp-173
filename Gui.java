// PROG 2 VT2025, Inlämningsuppgift, del 2
// Grupp 173
// Emil Berglund Löwgren, embe7155
// Emmi Bertlin, embe0071
// Mattias Liska, mali9460

package se.su.inlupp;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class Gui extends Application {
  // Sträng med path till bildfilen så att den kan användas i andra lambdauttryck
  private String imageFile;

  // Om changes = 0, inga ändringar har skett som måste sparas. Om changes = 1, ändrignar har skett som bör sparas
  private int changes = 0;

  private record ConnectionData(String name, String time) {}

  public void start(Stage stage) {
    Graph<Node> graph = new ListGraph<>();

    // ArrayList för valda platser (cirklar). Använder ArrayList för att kunna använda index
    ArrayList<Circle> selected = new ArrayList<>();

    // Sätter formen på start-fönstret. Max-höjd på 1000 för att passa på 1920x1080 skärmar
    stage.setWidth(525);
    stage.setMaxHeight(1000);
    stage.setTitle("Pathfinder");

    // Meny med flera olika menyval, stoppas sen i en MenuBar nedan
    Menu menu = new Menu("File");
    MenuItem newMapButton = new MenuItem("New Map");
    MenuItem openButton = new MenuItem("Open");
    MenuItem saveButton = new MenuItem("Save");
    MenuItem saveImageButton = new MenuItem("Save Image");
    MenuItem exitButton = new MenuItem("Exit");
    menu.getItems().addAll(newMapButton, openButton, saveButton, saveImageButton, exitButton);

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(menu);

    // Hbox för att ordna knappar vågrätt
    HBox buttons = new HBox(5);
    buttons.setAlignment(Pos.TOP_CENTER);
    Button findPathButton = new Button("Find Path");
    Button showConnectionButton = new Button("Show Connection");
    Button newPlaceButton = new Button("New Place");
    Button newConnectionButton = new Button("New Connection");
    Button changeConnectionButton = new Button("Change Connection");
    buttons.getChildren().addAll(findPathButton, showConnectionButton, newPlaceButton, newConnectionButton, changeConnectionButton);

    // ImageView som krävs för att stoppa in en bild i GUIn
    ImageView imgView = new ImageView();
    imgView.setPreserveRatio(true);

    // Pane som är "arbetsytan" och håller bilden, alla platser och dess kopplingar
    // Pane istället för exempelvis StackPane då StackPanes tvingar centrering av alla noder, inklusive cirklar osv
    Pane viewPane = new Pane(imgView);

    // Vbox för att ordna menyn, knapparna och "arbetsytan" lodrätt
    VBox root = new VBox(menuBar, buttons, viewPane);
    root.setAlignment(Pos.TOP_CENTER);
    root.setSpacing(5);

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

    //Meny
    newMapButton.setOnAction(e -> {
      // Ger en confirmation-dialog ifall osparade ändringar finns
      if (changes == 1) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Unsaved changes, continue anyway?");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL) {
          return;
        }
      }

      // Rensar programmet på alla bilder, cirklar, linjer, rensar listan med valda cirklar och rensar grafen
      clearProgram(graph, selected, viewPane, imgView);

      // Filechooser med ExtensionFilters som bara tillåter att man öppnar filer med de vanligaste bildfil-ändelserna
      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().addAll(
              new FileChooser.ExtensionFilter("GIF", "*.gif"),
              new FileChooser.ExtensionFilter("PNG", "*.png"),
              new FileChooser.ExtensionFilter("JPG", "*.jpg"),
              new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
      );

      File map = fileChooser.showOpenDialog(stage);

      // if-sats som kontrollerar ifall en fil valdes i FileChoosern
      if (map != null) {
        // Stoppar in path till bilden i en variabel som kan återanvändas i andra metoder/lambda-uttryck
        imageFile = map.getPath();

        try {
          // Då map är en file och inte en länk till en fil, skapar en länk till filen som Image-konstruktorn kan ta
          Image image = new Image(map.toURI().toURL().toString());

          // Stoppar bilden i en ImageView samt formar om fönstret för att passa bilden
          setMap(stage, image, imgView);

          changes = 0;
        } catch(MalformedURLException ex) {
          showErrorTab("Error has ocurred, please try again");
        }
      }
    });

    openButton.setOnAction(e -> {
      if (changes == 1) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Unsaved changes, continue anyway?");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL) {
          return;
        }
      }

      // Rensar programmet på alla bilder, cirklar, linjer, rensar listan med valda cirklar och rensar grafen.
      clearProgram(graph, selected, viewPane, imgView);

      HashMap<String, Node> nodes = new HashMap<>();

      // Filechooser med ExtensionFilters som bara tillåter att man öppnar .graph filer
      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Graph", "*.graph"));
      File saveFile = fileChooser.showOpenDialog(stage);

      // if-sats som kontrollerar ifall en fil valdes i FileChoosern
      if (saveFile != null) {
        try (BufferedReader br = new BufferedReader(new FileReader(saveFile))) {
          // Läser in bilden från första raden i sparfilen
          String image = br.readLine();

          // Tar filnamnet/path till filen från första raden och stoppar i variabel som kan användas i andra metoder/lambda-uttryck
          String[] path = image.split(":");
          if (path.length == 2) {
            imageFile = path[1];
          } else {
            imageFile = path[1] + ":" + path[2];
          }

          // Stoppar bilden i en ImageView samt formar om fönstret för att passa bilden
          setMap(stage, new Image(image), imgView);

          String line = br.readLine();
          String[] parts = line.split(";");

          String name;
          double coordX;
          double coordY;
          for(int i = 0; i < parts.length; i += 3) {
            name = parts[i];
            coordX = Double.parseDouble(parts[i + 1]);
            coordY = Double.parseDouble(parts[i + 2]);

            // Skapar platser (noder i grafen + cirklar på kartan) med inläst data
            createLocation(name, coordX, coordY, graph, selected, viewPane);

            // Skapar kopia och stoppar i HashMap för att kunna komma åt noden med namn
            Node place = new Node(name, coordX, coordY);
            nodes.put(parts[i], place);
          }

          // Itererar genom de resterande raderna med kopplingar och skapar de kopplingarna
          while ((line = br.readLine()) != null) {
            parts = line.split(";");

            String from = parts[0];
            String to = parts[1];
            String method = parts[2];
            int time = Integer.parseInt(parts[3]);

            // Undviker att skapa dubbla kopplingar mellan platser
            if (graph.getEdgeBetween(nodes.get(from), nodes.get(to)) == null) {
              createConnection(nodes.get(from), nodes.get(to), method, time, graph, viewPane);
            }
          }

          changes = 0;
        } catch (FileNotFoundException ex) {
          showErrorTab("File not found");
        } catch (IOException ex) {
          showErrorTab("Error has ocurred, please try again");
        }
      }
    });

    saveButton.setOnAction(e -> {
      // FileChooser som skapar en fil med .graph-ändelser genom ett ExtensionFilter
      FileChooser saver = new FileChooser();
      saver.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Graph", "*.graph"));
      File file = saver.showSaveDialog(stage);

      // if-sats som kontrollerar ifall en fil skapades i FileChoosern
      if (file != null) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))) {
          // Bygger en sträng med StringBuilder
          StringBuilder graphInfo = new StringBuilder();

          graphInfo.append("file:").append(imageFile).append("\n");
          for (Node node : graph.getNodes()) {
            graphInfo.append(node.getName()).append(";").append(node.getCoordX()).append(";").append(node.getCoordY()).append(";");
          }

          graphInfo.setLength(graphInfo.length() - 1);
          graphInfo.append("\n");

          for (Node node : graph.getNodes()) {
            for (Edge<Node> edge : graph.getEdgesFrom(node)) {
              graphInfo.append(node.getName()).append(";").append(edge.getDestination()).append(";").append(edge.getName()).append(";").append(edge.getWeight()).append("\n");
            }
          }

          // Stoppar den byggda strängen i BufferedWriter
          graphInfo.setLength(graphInfo.length() - 1);

          // Skriver strängen i BufferedWritern till den nyskapade filen
          buffer.write(graphInfo.toString());

          changes = 0;
        } catch (IOException ex) {
          showErrorTab("Error has ocurred, please try again");
        }
      }
    });

    saveImageButton.setOnAction(e -> {
      try {
        WritableImage image = viewPane.snapshot(null, null);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        File outputFile = new File("../capture.png");
        ImageIO.write(bufferedImage, "png", outputFile);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Image saved");
        alert.showAndWait();

      } catch (IOException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "IO Error"+ex.getMessage());
        alert.showAndWait();
      }
    });

    exitButton.setOnAction(e -> {
      if (changes == 1) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Unsaved changes, exit anyway?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Warning!");
        alert.setHeaderText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
          Platform.exit();
        }
      } else {
        Platform.exit();
      }
    });

    // Får krysset i högra hörnet att fungera likt Exit i menyn
    stage.setOnCloseRequest(e -> {
      if (changes == 1) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Unsaved changes, exit anyway?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Warning!");
        alert.setHeaderText(null);

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.CANCEL) {
          e.consume();
        }
      }
    });

    //Knappar
    newPlaceButton.setOnAction(e -> {
      newPlaceButton.setDisable(true);
      scene.setCursor(Cursor.CROSSHAIR);

      imgView.setOnMouseClicked(locationHandler -> {
        TextInputDialog input = new TextInputDialog();
        input.setHeaderText(null);
        input.setTitle("Name");
        input.setContentText("Name of place:");

        Optional<String> result = input.showAndWait();
        if (result.isPresent()) {
          createLocation(result.get(), locationHandler.getX(), locationHandler.getY(), graph, selected, viewPane);

          changes = 1;
        }

        newPlaceButton.setDisable(false);
        scene.setCursor(Cursor.DEFAULT);

        // Gör så att inget längre händer när man klickar på bilden
        imgView.setOnMouseClicked(null);
      });
    });

    newConnectionButton.setOnAction(e -> {
      if (selected.size() != 2){
        showErrorTab("You need to select two locations");
        return;
      }

      // Hämtar UserData från cirklarna som innehåller noden i grafen de motsvarar.
      Node a = (Node) selected.get(0).getUserData();
      Node b = (Node) selected.get(1).getUserData();

      if (graph.pathExists(a, b)) {
        showErrorTab(String.format("A connection between %s and %s already exists", a.getName(), b.getName()));
        return;
      }

      Dialog<ConnectionData> dialog = new Dialog<>();
      dialog.setTitle("Connection");
      dialog.setHeaderText(String.format("Connection from %s to %s", a.getName(), b.getName()));

      ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

      TextField nameField = new TextField();

      TextField timeField = new TextField();

      GridPane pane = new GridPane();
      pane.setHgap(10);
      pane.setVgap(10);
      pane.setPadding(new Insets(10, 80, 10, 80));

      pane.add(new Label("Name:"), 0, 0);
      pane.add(nameField, 1, 0);
      pane.add(new Label("Time:"), 0, 1);
      pane.add(timeField, 1, 1);

      dialog.getDialogPane().setContent(pane);

      dialog.setResultConverter(dialogButton -> {
        if (dialogButton == okButtonType) {
          return new ConnectionData(nameField.getText(), timeField.getText());
        }
        return null;
      });

      Optional<ConnectionData> result = dialog.showAndWait();
      result.ifPresent(connectionData -> {
        try {
          int time = Integer.parseInt(connectionData.time());

          graph.connect(a, b, connectionData.name(), time);

          Line connectionLine = new Line(a.getCoordX(), a.getCoordY(), b.getCoordX(), b.getCoordY());
          connectionLine.setStroke(Color.BLACK);
          connectionLine.setStrokeWidth(3);
          viewPane.getChildren().add(connectionLine);

          changes = 1;
        } catch (NumberFormatException ex) {
          showErrorTab("Please enter a number in the time textbox.");
        }
      });
    });

    showConnectionButton.setOnAction(e -> {
      if (selected.size() != 2) {
        showErrorTab("You need to select two places!");
        return;
      }

      // Hämtar UserData från cirklarna som innehåller noden i grafen de motsvarar.
      Node a = (Node) selected.get(0).getUserData();
      Node b = (Node) selected.get(1).getUserData();

      if (graph.pathExists(a, b)) {
        for (Edge<Node> edge : graph.getEdgesFrom(a)) {
          if (edge.getDestination().equals(b)) {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Connection");
            dialog.setHeaderText(String.format("Connection from %s to %s", a.getName(), b.getName()));

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            GridPane pane = new GridPane();

            pane.setHgap(6);
            pane.setVgap(10);

            pane.setPadding(new Insets(10, 80, 10, 80));

            TextField nameField = new TextField(edge.getName());
            nameField.setEditable(false);

            TextField timeField = new TextField(String.valueOf(edge.getWeight()));
            timeField.setEditable(false);

            pane.add(new Label("Name:"), 0, 0);
            pane.add(nameField, 1, 0);
            pane.add(new Label("Time:"), 0, 1);
            pane.add(timeField, 1, 1);

            dialog.getDialogPane().setContent(pane);
            dialog.showAndWait();
            return;
          }
        }

        showErrorTab("Connection not found");
      } else {
        showErrorTab(String.format("There is no connection between %s and %s.", a.getName(), b.getName()));
      }
    });

    changeConnectionButton.setOnAction(e -> {
      if (selected.size() != 2) {
        showErrorTab("Two places must be selected!");
        return;
      }

      // Hämtar UserData från cirklarna som innehåller noden i grafen de motsvarar.
      Node a = (Node) selected.get(0).getUserData();
      Node b = (Node) selected.get(1).getUserData();

      if (graph.pathExists(a, b)) {
        for (Edge<Node> edge : graph.getEdgesFrom(a)) {
          if (edge.getDestination().equals(b)) {

            Dialog<ConnectionData> dialog = new Dialog<>();
            dialog.setTitle("Connection");
            dialog.setHeaderText(String.format("Connection from %s to %s", a.getName(), b.getName()));

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

            GridPane pane = new GridPane();

            pane.setHgap(6);
            pane.setVgap(10);

            pane.setPadding(new Insets(10, 80, 10, 80));

            TextField nameField = new TextField(edge.getName());
            nameField.setEditable(false);

            TextField timeField = new TextField();

            pane.add(new Label("Name:"), 0, 0);
            pane.add(nameField, 1, 0);
            pane.add(new Label("Time:"), 0, 1);
            pane.add(timeField, 1, 1);

            dialog.getDialogPane().setContent(pane);

            dialog.setResultConverter(buttonType -> {
              if (buttonType == okButton) {
                edge.setWeight(Integer.parseInt(timeField.getText()));
              }
              return null;
            });

            dialog.showAndWait();

            for (Edge<Node> otherEdge : graph.getEdgesFrom(b)) {
              if (otherEdge.getDestination().equals(a)) {
                otherEdge.setWeight(Integer.parseInt(timeField.getText()));
              }
            }

            changes = 1;
          }
        }
      } else {
        showErrorTab(String.format("There is no connection between %s and %s.", a.getName(), b.getName()));
      }
    });

    findPathButton.setOnAction(e -> {
      if (selected.size() != 2){
        showErrorTab("Two places must be selected!");
        return;
      }

      Node a = (Node) selected.get(0).getUserData();
      Node b = (Node) selected.get(1).getUserData();

      if (graph.getPath(a, b) != null) {
        // Bygger en sträng med StringBuilder
        StringBuilder sb = new StringBuilder();

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Message");
        dialog.setHeaderText(String.format("The Path from %s to %s:", a.getName(), b.getName()));

        GridPane pane = new GridPane();

        TextArea travelInformation = new TextArea();

        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButton);

        int totalTime = 0;
        for (Edge<Node> edge : graph.getPath(a, b)) {
          sb.append(String.format("to %s by %s takes %s", edge.getDestination(), edge.getName(), edge.getWeight())).append("\n");
          totalTime += edge.getWeight();
        }

        sb.append(String.format("Total %s", totalTime));

        // Stoppar en byggda strängen i TextArean
        travelInformation.setText(sb.toString());

        pane.add(travelInformation, 0 ,0);

        dialog.getDialogPane().setContent(pane);
        dialog.showAndWait();

      } else {
        showErrorTab(String.format("There is no path from %s to %s.", a.getName(), b.getName()));
      }
    });
  }

  //Hjälpmetoder

  private void setMap(Stage stage, Image image, ImageView imgView) {
    imgView.setImage(image);

    // +16, annars passar inte hela bilden
    stage.setMaxWidth(image.getWidth() + 16);
    stage.setWidth(image.getWidth() + 16);

    // +99, annars passar inte hela bilden
    stage.setMaxHeight(image.getHeight() + 99);
    stage.setHeight(image.getHeight() + 99);
    imgView.setFitWidth(image.getWidth());
  }

  // Graf, pane osv. stoppas in då metoden inte kan komma åt en graf/pane som skapas i en annan metod (start-metoden)
  private void createLocation(String name, double coordX, double coordY, Graph<Node> graph, ArrayList<Circle> selected, Pane viewPane) {
    Node place = new Node(name, coordX, coordY);
    graph.add(place);

    Circle location = new Circle();
    location.setCenterX(coordX);
    location.setCenterY(coordY);
    location.setRadius(10.0f);
    location.setFill(Color.RED);
    location.managedProperty().set(false);

    // Sätter cirkelns UserData till motsvarande nod i grafen, för att sedan komma åt noden genom cirkeln
    location.setUserData(place);

    // Label med platsens namn under cirkeln
    Label locationName = new Label(name);
    locationName.setFont(Font.font("Helvetica", FontWeight.BOLD,14));
    locationName.relocate(coordX, coordY + 5);
    viewPane.getChildren().addAll(location, locationName);

    // Sätter beteende för cirklarna
    location.setOnMouseClicked(selectHandler -> {
      // If-sats som ser till att bara 1-2 cirklar kan väljas
      if (selected.size() <= 2) {
        if (selected.contains(location)) {
          location.setFill(Color.RED);
          selected.remove(location);
        } else if (!selected.contains(location) && selected.size() < 2) {
          location.setFill(Color.BLUE);
          selected.add(location);
        }
      }
    });
  }

  // Återanvänder kod från newConnectionButton. Graf och Pane stoppas in då metoden inte kan komma åt en graf/pane som skapas i en annan metod (start-metoden)
  private void createConnection(Node a, Node b, String name, int time, Graph<Node> graph, Pane viewPane) {
    graph.connect(a, b, name, time);

    Line connectionLine = new Line(a.getCoordX(), a.getCoordY(), b.getCoordX(), b.getCoordY());
    connectionLine.setStroke(Color.BLACK);
    connectionLine.setStrokeWidth(3);
    viewPane.getChildren().add(connectionLine);
  }

  // Skapar ett ett Errorfönster med givet errormeddelande
  private void showErrorTab(String message){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  // Återställer allt i programmet till startläge, dvs. tomt. Graf, Pane osv. stoppas in då metoden inte kan komma åt en graf/pane som skapas i en annan metod (start-metoden)
  private void clearProgram(Graph<Node> graph, ArrayList<Circle> selected, Pane viewPane, ImageView imgView) {
    // Skapar ny lista med alla noder i grafen för att undvika att iterera genom ett set som aktivt minskar ändras. Kan annars orsaka error
    ArrayList<Node> toRemove = new ArrayList<>(graph.getNodes());

    for (Node node : toRemove) {
      graph.remove(node);
    }

    selected.clear();
    viewPane.getChildren().clear();

    // Stoppar in ImageView igen då den alltid krävs i vårt Pane
    viewPane.getChildren().add(imgView);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
