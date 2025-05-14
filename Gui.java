package se.su.inlupp;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
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
  public void start(Stage stage) {
    Graph<Node> graph = new ListGraph<Node>();
    ArrayList<Circle> selected = new ArrayList<>();

    FileChooser fileChooser = new FileChooser();

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
      File map = fileChooser.showOpenDialog(stage);
      try {
        Image image = new Image(map.toURI().toURL().toString());

        imgView.setImage(image);

        stage.setMaxWidth(image.getWidth() + 17);
        stage.setMaxHeight(image.getHeight() + 99);
        stage.setWidth(image.getWidth() + 17);
        stage.setHeight(image.getHeight() + 99);
        imgView.setFitWidth(image.getWidth());
      } catch(MalformedURLException ex) {
        ex.printStackTrace();
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
          Node loc = new Node(result.get(), locationHandler.getX(), locationHandler.getY());
          graph.add(loc);

          Circle location = new Circle();
          location.setCenterX(locationHandler.getX());
          location.setCenterY(locationHandler.getY());
          location.setRadius(10.0f);
          location.setFill(Color.RED);
          location.managedProperty().set(false);
          location.setUserData(loc);

          Label locationName = new Label(result.get());
          locationName.setFont(Font.font("Helvetica", FontWeight.BOLD,14));
          locationName.relocate(locationHandler.getX(), locationHandler.getY() + 5);
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

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    grid.add(new Label("Name:"), 0, 0);
    grid.add(nameField, 1, 0);
    grid.add(new Label("Time:"), 0, 1);
    grid.add(timeField, 1, 1);

    dialog.getDialogPane().setContent(grid);

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

        } catch (NumberFormatException ex) {
            ShowErrorTab("Please enter a number in the time textbox.");
        }

    });

    });
  }

  private record ConnectionData(String name, String time) {}

  private void ShowErrorTab(String message){

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
