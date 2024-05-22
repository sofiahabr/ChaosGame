package edu.ntnu.idatt2003.group25.view;


import edu.ntnu.idatt2003.group25.controller.InitializePageController;
import edu.ntnu.idatt2003.group25.controller.ScreenController;
import edu.ntnu.idatt2003.group25.view.menus.TopMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The type Initialize page.
 */
public class InitializePage extends View {
  /**
   * The Screen controller.
   */
  ScreenController screenController;
  private final BorderPane initPane = new BorderPane();

  /**
   * Instantiates a new Initialize page.
   * The initialize page is the first page the user sees when the application is started.
   * The initialize page is used to choose what game the user wants to play.
   *
   * @param screenController the screen controller
   */
  public InitializePage(ScreenController screenController) {
    this.screenController = screenController;
    addObserver(new InitializePageController(screenController));
    setUp();
  }

  /**
   * The method that sets up the UI- layout.
   */

  @Override
  public void setUp() {
    TopMenu topMenu = new TopMenu(screenController);
    topMenu.setUp();
    initPane.setTop(topMenu.getMenu());


    Rectangle box = new Rectangle(MainLogic.width * 0.6, MainLogic.height * 0.6);
    box.getStyleClass().add("rectangle");

    Text heading = new Text("Chaos Game");
    heading.getStyleClass().add("heading");
    heading.setStyle("-fx-font-size: 40");

    Text subheading = new Text("Choose an option:");
    subheading.getStyleClass().add("heading");
    subheading.setStyle("-fx-font-size: 20");

    ComboBox<String> optionBox = new ComboBox<>();
    optionBox.getItems().addAll(
        "Create new Affine Transform", "Create new Julia Transform", "Julia Transform",
        "Sierpinski Triangle", "Barnsley Fern", "Lévy Dragon", "Heighway Dragon", "Read from file");

    optionBox.setMinSize(180, 30);

    Button startButton  = new Button("Start Game");
    startButton.setOnAction(e -> {
      if (optionBox.getValue() == null) {
        optionBox.setPromptText("Please choose a transform");
        optionBox.getStyleClass().add("error");
      } else {
        updateObserver("button clicked", optionBox.getValue());
      }
    });

    VBox infoArea = new VBox(30, heading, subheading, optionBox, new VBox(), startButton);
    infoArea.setAlignment(Pos.CENTER);

    StackPane infoBoxBackground = new StackPane();
    infoBoxBackground.getChildren().addAll(box, infoArea);
    infoBoxBackground.setAlignment(Pos.CENTER);

    initPane.setCenter(infoBoxBackground);
  }

  /**
   * A method for accessing panes of the views.
   *
   * @return the pane
   */

  @Override
  public BorderPane getPane() {
    return initPane;
  }

}
