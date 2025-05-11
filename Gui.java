package se.su.inlupp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.File;
import java.net.MalformedURLException;

public class Gui extends Application {
  public void start(Stage stage) {
    Graph<Node> graph = new ListGraph<Node>();

    FileChooser fileChooser = new FileChooser();

    stage.setWidth(525);
    stage.setMaxHeight(1000);

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

    VBox root = new VBox(menuBar, buttons, imgView);
    root.setAlignment(Pos.TOP_CENTER);
    root.setSpacing(5);

    Scene scene = new Scene(root);
    stage.setTitle("Pathfinder");
    stage.setScene(scene);
    stage.show();

    newMapButton.setOnAction(e -> {
      File map = fileChooser.showOpenDialog(stage);
      try {
        Image image = new Image(map.toURI().toURL().toString());

        double aspectRatio = image.getHeight() / image.getWidth();

        imgView.setImage(image);

        stage.setWidth(image.getWidth() + (image.getWidth() * 0.2));
        stage.setHeight(image.getHeight() + 5 + 99);
        imgView.setFitWidth(image.getWidth());

        //Alternativt
        //imgView.setFitWidth(image.getWidth() * 0.9 / (image.getWidth() / root.getWidth()));
        //stage.setHeight(imgView.getFitWidth() * aspectRatio + 99 + 5);
      } catch(MalformedURLException ex) {
        ex.printStackTrace();
      }
    });
  }

  public static void main(String[] args) {
    launch(args);
  }
}
