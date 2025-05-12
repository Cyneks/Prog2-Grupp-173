package se.su.inlupp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;

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
  }

  public static void main(String[] args) {
    launch(args);
  }
}
