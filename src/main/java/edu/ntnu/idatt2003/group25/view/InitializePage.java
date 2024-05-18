package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.controller.InitializePageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.view.menus.TopMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class InitializePage extends View {
  ScreenController screenController;
  private BorderPane initPane = new BorderPane();

  public InitializePage(ScreenController screenController){
    this.screenController = screenController;
    addObserver(new InitializePageController(screenController));
    setUp();
  }

  @Override
  public void setUp() {
    TopMenu topMenu = new TopMenu(screenController);
    topMenu.setUp();

    initPane.setTop(topMenu.getMenu());

    StackPane infoBoxBackground = new StackPane();
    Rectangle box = new Rectangle(600, 400);
    box.getStyleClass().add("rectangle");

    Text heading = new Text("Chaos Game");
    heading.getStyleClass().add("heading");
    heading.setStyle("-fx-font-size: 40");

    Text subheading = new Text("Choose an option:");
    subheading.getStyleClass().add("heading");
    subheading.setStyle("-fx-font-size: 20");

    ComboBox<String> optionBox = new ComboBox<>();
    optionBox.getItems().addAll(
        "Create new Affine Transform","Create new Julia Transform", "Julia Transform",
        "Sierpinski Triangle", "Barnsley Fern","Lévy Dragon", "Heighway Dragon", "Read from file");

    optionBox.setMinSize(180, 30);

    Button startButton  = new Button("Start Game");

    startButton.setOnAction(e-> updateObserver("button clicked", optionBox.getValue()));

    VBox infoArea = new VBox(30, heading, subheading, optionBox, new VBox(), startButton);
    infoArea.setAlignment(Pos.CENTER);
    infoArea.setPadding(new Insets(40));

    infoBoxBackground.getChildren().addAll(box, infoArea);
    infoBoxBackground.setAlignment(Pos.CENTER);
    infoBoxBackground.setPadding(new Insets(150));

    initPane.setCenter(infoBoxBackground);
  }



  @Override
  public BorderPane getPane() {
    return initPane;
  }

}
