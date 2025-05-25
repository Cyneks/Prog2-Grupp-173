//package se.su.inlupp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class Gui extends Application {
  private String imageFile;

  //Om changes = 0, inga ändringar har skett som måste sparas. Om changes = 1, ändrignar har skett som bör sparas.
  private int changes;

  private record ConnectionData(String name, String time) {}

  public void start(Stage stage) {
    Graph<Node> graph = new ListGraph<Node>();
    ArrayList<Circle> selected = new ArrayList<>();

    stage.setWidth(525);
    stage.setMaxHeight(1000);
    stage.setTitle("Pathfinder");

    Menu menu = new Menu("File");
    MenuItem newMapButton = new MenuItem("New Map");
    MenuItem openButton = new MenuItem("Open");
    MenuItem saveButton = new MenuItem("Save");
    MenuItem saveImageButton = new MenuItem("Save Image");
    MenuItem exitButton = new MenuItem("Exit");
    menu.getItems().addAll(newMapButton, openButton, saveButton, saveImageButton, exitButton);

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(menu);

    HBox buttons = new HBox(5);
    buttons.setAlignment(Pos.TOP_CENTER);
    Button findPathButton = new Button("Find Path");
    Button showConnectionButton = new Button("Show Connection");
    Button newPlaceButton = new Button("New Place");
    Button newConnectionButton = new Button("New Connection");
    Button changeConnectionButton = new Button("Change Connection");
    buttons.getChildren().addAll(findPathButton, showConnectionButton, newPlaceButton, newConnectionButton, changeConnectionButton);

    ImageView imgView = new ImageView();
    imgView.setPreserveRatio(true);

    Pane viewPane = new Pane(imgView);

    VBox root = new VBox(menuBar, buttons, viewPane);
    root.setAlignment(Pos.TOP_CENTER);
    root.setSpacing(5);

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

    //Meny
    newMapButton.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().addAll(
              new FileChooser.ExtensionFilter("GIF", "*.gif"),
              new FileChooser.ExtensionFilter("PNG", "*.png"),
              new FileChooser.ExtensionFilter("JPG", "*.jpg"),
              new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
      );
      File map = fileChooser.showOpenDialog(stage);
      imageFile = map.getPath();

      try {
        Image image = new Image(map.toURI().toURL().toString());

        setMap(stage, image, imgView);

        //imgView.setImage(image);

        //stage.setMaxWidth(image.getWidth() + 16);
        //stage.setMaxHeight(image.getHeight() + 99);
        //stage.setWidth(image.getWidth() + 16);
        //stage.setHeight(image.getHeight() + 99);
        //imgView.setFitWidth(image.getWidth());
      } catch(MalformedURLException ex) {
        ex.printStackTrace();
      }
    });

    openButton.setOnAction(e -> {
      HashMap<String, Node> nodes = new HashMap<>();

      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Graph", "*.graph"));
      File file = fileChooser.showOpenDialog(stage);

      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String image = br.readLine();
        String[] path = image.split(":");
        if (path.length == 2) {
          imageFile = path[1];
        } else {
          imageFile = path[1] + ":" + path[2];
        }
        setMap(stage, new Image(image), imgView);

        String line = br.readLine();
        String[] parts = line.split(";");

        String name;
        double coordX;
        double coordY;
        for(int i = 0; i < parts.length; i += 3) {
          name = parts[i];
          coordX = Double.valueOf(parts[i + 1]);
          coordY = Double.valueOf(parts[i + 2]);

          createLocation(name, coordX, coordY, graph, selected, viewPane);

          Node place = new Node(name, coordX, coordY);
          nodes.put(parts[i], place);
        }

        while ((line = br.readLine()) != null) {
          parts = line.split(";");

          String from = parts[0];
          String to = parts[1];
          String method = parts[2];
          int time = Integer.valueOf(parts[3]);

          if (!graph.pathExists(nodes.get(from), nodes.get(to))) {
            createConnection(nodes.get(from), nodes.get(to), method, time, graph, viewPane);
          }
        }
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });

    saveButton.setOnAction(e -> {
      if (changes == 1) {
        FileChooser saver = new FileChooser();
        saver.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Graph", "*.graph"));
        File file = saver.showSaveDialog(stage);

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))) {
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

          graphInfo.setLength(graphInfo.length() - 1);

          buffer.write(graphInfo.toString());
        } catch (IOException ex) {
          ex.printStackTrace();
        }

        changes = 0;
      } else {
        ShowErrorTab("No changes to save");
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
          //Node loc = new Node(result.get(), locationHandler.getX(), locationHandler.getY());
          //graph.add(loc);

          //Circle location = new Circle();
          //location.setCenterX(locationHandler.getX());
          //location.setCenterY(locationHandler.getY());
          //location.setRadius(10.0f);
          //location.setFill(Color.RED);
          //location.managedProperty().set(false);
          //location.setUserData(loc);

          //Label locationName = new Label(result.get());
          //locationName.setFont(Font.font("Helvetica", FontWeight.BOLD,14));
          //locationName.relocate(locationHandler.getX(), locationHandler.getY() + 5);
          //viewPane.getChildren().addAll(location, locationName);

          //location.setOnMouseClicked(selectHandler -> {
          //  if (selected.size() <= 2) {
          //    if (selected.contains(location)) {
          //      location.setFill(Color.RED);
          //      selected.remove(location);
          //    } else if (!selected.contains(location) && selected.size() < 2) {
          //      location.setFill(Color.BLUE);
          //      selected.add(location);
          //    }
          //  }
          //});

          changes = 1;
        }

        newPlaceButton.setDisable(false);
        scene.setCursor(Cursor.DEFAULT);

        imgView.setOnMouseClicked(null);
      });
    });

    newConnectionButton.setOnAction(e -> {
      if (selected.size() != 2){
        ShowErrorTab("You need to select two locations");
        return;
      }

      Node a = (Node) selected.get(0).getUserData();
      Node b = (Node) selected.get(1).getUserData();

      if (graph.pathExists(a, b)) {
        ShowErrorTab(String.format("A connection between %s and %s already exists", a.getName(), b.getName()));
        return;
      };

      // TextInputDialog connectionDialog = new TextInputDialog();
      // connectionDialog.setHeaderText(String.format("Connection from %s to %s", a.getName(), b.getName()));
      // connectionDialog.setTitle("Connection");
      // connectionDialog.setContentText("Name:");

      //Optional<String> result = connectionDialog.showAndWait();
      // if (result.isPresent()){



      // }

      Dialog<ConnectionData> dialog = new Dialog<>();
      dialog.setTitle("Connection");
      dialog.setHeaderText(String.format("Connection from %s to %s", a.getName(), b.getName()));

      ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

      TextField nameField = new TextField();
      //nameField.setPromptText("Name");

      TextField timeField = new TextField();
      //timeField.setPromptText("Time");

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
            ShowErrorTab("Please enter a number in the time textbox.");
        }

      });

    });

    showConnectionButton.setOnAction(e -> {

      if (selected.size() != 2){

        ShowErrorTab("You need to select two places!");
        return;

      }

      Node a = (Node) selected.get(0).getUserData();
      Node b = (Node) selected.get(1).getUserData();

      if (graph.pathExists(a, b)){

        for (Edge<Node> edge : graph.getEdgesFrom(a)){

          if (edge.getDestination().equals(b)){

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

            TextField nameField = new TextField(edge.name);
            nameField.setEditable(false);
            
            TextField timeField = new TextField(String.valueOf(edge.weight));
            timeField.setEditable(false);

            pane.add(new Label("Name:"), 0, 0);
            pane.add(nameField, 1, 0);
            pane.add(new Label("Time:"), 0, 1);
            pane.add(timeField, 1, 1);

            dialog.getDialogPane().setContent(pane);
            dialog.showAndWait();
            return;

          }else ShowErrorTab("Connection not found");

        }

      }else {
        ShowErrorTab(String.format("There is no connection between %s and %s.", a.getName(), b.getName()));
        return;
      }

    });

    changeConnectionButton.setOnAction(e -> {

      if (selected.size() != 2){
        ShowErrorTab("Two places must be selected!");
        return;
      }

      Node a = (Node) selected.get(0).getUserData();
      Node b = (Node) selected.get(1).getUserData();

      if (graph.pathExists(a, b)){

        for (Edge<Node> edge : graph.getEdgesFrom(a)){

          if (edge.destination.equals(b)){

            Dialog<ConnectionData> dialog = new Dialog<>();
            dialog.setTitle("Connection");
            dialog.setHeaderText(String.format("Connection from %s to %s", a.getName(), b.getName()));

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

            GridPane pane = new GridPane();

            pane.setHgap(6);
            pane.setVgap(10);

            pane.setPadding(new Insets(10, 80, 10, 80));

            TextField nameField = new TextField(edge.name);
            nameField.setEditable(false);

            TextField timeField = new TextField();

            pane.add(new Label("Name:"), 0, 0);
            pane.add(nameField, 1, 0);
            pane.add(new Label("Time:"), 0, 1);
            pane.add(timeField, 1, 1);

            dialog.getDialogPane().setContent(pane);

            dialog.setResultConverter(buttonType ->{
              if (buttonType == okButton){
                edge.setWeight(Integer.parseInt(timeField.getText()));
              }
              return null;
            });

            dialog.showAndWait();
            
            for (Edge<Node> otherEdge : graph.getEdgesFrom(b)){

              if (otherEdge.destination.equals(a)){
                otherEdge.setWeight(Integer.parseInt(timeField.getText()));
              }

            }

          }

        }

      }else {
        ShowErrorTab(String.format("There is no connection between %s and %s.", a.getName(), b.getName()));
        return;
      }

    });

  }

  //Hjälpmetoder

  private void setMap(Stage stage, Image image, ImageView imgView) {
    imgView.setImage(image);
    stage.setMaxWidth(image.getWidth() + 16);
    stage.setMaxHeight(image.getHeight() + 99);
    stage.setWidth(image.getWidth() + 16);
    stage.setHeight(image.getHeight() + 99);
    imgView.setFitWidth(image.getWidth());

    changes = 1;
  }

  private void createLocation(String name, double coordX, double coordY, Graph<Node> graph, ArrayList<Circle> selected, Pane viewPane) {
    Node loc = new Node(name, coordX, coordY);
    graph.add(loc);

    Circle location = new Circle();
    location.setCenterX(coordX);
    location.setCenterY(coordY);
    location.setRadius(10.0f);
    location.setFill(Color.RED);
    location.managedProperty().set(false);
    location.setUserData(loc);

    Label locationName = new Label(name);
    locationName.setFont(Font.font("Helvetica", FontWeight.BOLD,14));
    locationName.relocate(coordX, coordY + 5);
    viewPane.getChildren().addAll(location, locationName);

    location.setOnMouseClicked(selectHandler -> {
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

  private void createConnection(Node a, Node b, String name, int time, Graph<Node> graph, Pane viewPane) {
    graph.connect(a, b, name, time);

    Line connectionLine = new Line(a.getCoordX(), a.getCoordY(), b.getCoordX(), b.getCoordY());
    connectionLine.setStroke(Color.BLACK);
    connectionLine.setStrokeWidth(3);
    viewPane.getChildren().add(connectionLine);
  }

  private void ShowErrorTab(String message){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  //Mainmetod

  public static void main(String[] args) {
    launch(args);
  }
}
