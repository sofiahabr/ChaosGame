package edu.ntnu.idatt2003.group25.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class InitializePage extends View {
  private BorderPane initPane = new BorderPane();

  public InitializePage(){
    setUp();
  }

  @Override
  public void setUp() {
    initPane.setStyle("-fx-background-color: white; ");

    StackPane infoBoxBackground = new StackPane();
    Rectangle box = new Rectangle(600, 400);
    box.setStyle("-fx-fill: #D9D9D9; -fx-border-width: 20; -fx-border-color: black; -fx-border-radius: 100;");

    Text heading = new Text("Chaos Game");
    heading.setStyle("-fx-font-size: 30");
    Text subheading = new Text("Choose an option:");
    subheading.setStyle("-fx-font-size: 20");

    ComboBox<String> optionBox = new ComboBox<>();
    optionBox.getItems().addAll(
        "Create new Transform", "Julia Transform",
        "Sierpinski Triangle", "Barnsley Fern", "Read from file");

    optionBox.setMinSize(180, 30);
    optionBox.setStyle("-fx-background-color: white");
    optionBox.setOnAction(e-> {updateObserver(optionBox.getValue());});

    Button startButton  = new Button("Start Game");
    startButton.setStyle("-fx-font-size: 16; -fx-background-color: white; -fx-padding: 5 20; -fx-border-radius: 10");

    VBox infoArea = new VBox(30, heading, subheading, optionBox, startButton);
    infoArea.setAlignment(Pos.TOP_CENTER);
    infoArea.setPadding(new Insets(40));

    infoBoxBackground.getChildren().addAll(box, infoArea);
    infoBoxBackground.setAlignment(Pos.TOP_CENTER);
    infoBoxBackground.setPadding(new Insets(150));

    initPane.setCenter(infoBoxBackground);
  }

  @Override
  public Pane getPane() {
    return initPane;
  }

}
