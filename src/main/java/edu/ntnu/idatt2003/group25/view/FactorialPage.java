package edu.ntnu.idatt2003.group25.view;

import edu.ntnu.idatt2003.group25.model.ChaosGame;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescription;
import edu.ntnu.idatt2003.group25.model.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.group25.model.ChaosGameFileHandler;
import edu.ntnu.idatt2003.group25.model.Complex;
import edu.ntnu.idatt2003.group25.model.Vector2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class FactorialPage {
  public BorderPane borderPane =  new BorderPane();
//  ChaosGameDescription description = ChaosGameDescriptionFactory.createJuliaTransformation(new Complex(-0.74543, 0.11301));
  ChaosGameDescription description = new ChaosGameDescription(new ArrayList<>(), new Vector2D(0,0), new Vector2D(0,0));

  ChaosGame chaosGame = new ChaosGame(description, Math.round(MainView.width*0.6f), Math.round(MainView.height*0.6f));
  int steps = 0;
  public FactorialPage()  {
    createLayout();
  }
  public BorderPane factorialPane() {
    return borderPane;
  }
  public void draw(){
    int[][] canvas = chaosGame.getCanvas().getCanvasArray();
    Canvas pixelCanvas = new Canvas(chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();
    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        double number = canvas[i][j] / 10f;
        if ((number) > 0) {
          if ((number) < 1){
            gc.setFill(Color.color(1,0,number));
          }
          else if ((number) < 2){
            gc.setFill(Color.color(2 - number, 0,1));
          }
          else {
            gc.setFill(Color.color(0,0,1));
          }
          gc.fillRect(j, i, 1, 1); // Draw a single pixel
        }
      }
    }
    borderPane.setCenter(pixelCanvas);
  }

  public void createLayout() {
    borderPane.setStyle("-fx-background-color: white; ");

    createSideBar();

    HBox topBox = new HBox(0);
    borderPane.setTop(topBox);
  }

  public void playGame(){
    chaosGame.runSteps(steps);
    draw();
  }
  public void clear() {
    chaosGame.getCanvas().clear();
    draw();
  }
  public void setDescription(ChaosGameDescription newDescription) {
    this.description = newDescription;
    this.chaosGame = new ChaosGame(newDescription, chaosGame.getCanvas().getWidth(), chaosGame.getCanvas().getHeight());
  }
  public void setSteps(int newSteps) {
    this.steps = newSteps;
  }

  private void createSideBar() {
    VBox sidebarMenu = new VBox(40);
    sidebarMenu.setStyle("-fx-background-color: #D9D9D9; ");
    sidebarMenu.setPadding(new Insets(20));

    // Create choose steps area
    Text chooseSteps = new Text("Choose steps");
    chooseSteps.setStyle("-fx-font-size: 14; ");

    TextField inputSteps = new TextField();
    inputSteps.setPromptText(" Ex. 500...");
    inputSteps.setMaxWidth(180);
    inputSteps.setMinHeight(30);
    inputSteps.setStyle("-fx-background-radius: 10");

    VBox stepsArea = new VBox(10);
    stepsArea.getChildren().addAll(chooseSteps, inputSteps);
    stepsArea.setAlignment(Pos.CENTER);


    // Create choose transforms area
    Text chooseTransform = new Text("Choose transform");
    chooseTransform.setStyle("-fx-font-size: 14");

    ComboBox<String> transformDropDown = new ComboBox<>();
    transformDropDown.setMinSize(180, 30);
    transformDropDown.setStyle("-fx-background-color: white; -fx-background-radius: 10");

    transformDropDown.getItems().addAll(
        "Create Affine Transform", "Create Julia Transform", "Julia Transform",
        "Sierpinski Triangle", "Barnsley Fern", "Read from file");

    VBox transformsArea = new VBox(10);
    transformsArea.getChildren().addAll(chooseTransform, transformDropDown);
    transformsArea.setAlignment(Pos.CENTER);


    // Create min/ max area
    Text minTitle = new Text(" Min: ");
    Text maxTitle = new Text(" Max: ");

    minTitle.setStyle("-fx-font-size: 14;");
    maxTitle.setStyle("-fx-font-size: 14;");

    TextField inputMax = new TextField();
    inputMax.setMaxWidth(80);
    inputMax.setMinHeight(30);
    inputMax.setStyle("-fx-background-radius: 10");

    TextField inputMin = new TextField();
    inputMin.setMaxWidth(80);
    inputMin.setMinHeight(30);
    inputMin.setStyle("-fx-background-radius: 10");


    VBox minBox = new VBox(10);
    VBox maxBox = new VBox(10);

    minBox.getChildren().addAll(minTitle, inputMin);
    maxBox.getChildren().addAll(maxTitle, inputMax);


    HBox minMaxBox = new HBox(20);
    minMaxBox.getChildren().addAll(minBox, maxBox);
    minMaxBox.setAlignment(Pos.CENTER);

    // Bottom Buttons
    String buttonStyle = "-fx-background-radius: 10; -fx-min-height: 30; -fx-text-fill: white; -fx-font-size: 12;";

    Button playButton = new Button("Play");
    playButton.setStyle(buttonStyle + "-fx-background-color: black; -fx-min-width: 80; ");
    playButton.setOnAction(e -> {
      setStepsAction(inputSteps);
      playGame();
    });

    Button breakButton = new Button("Break");
    breakButton.setStyle(buttonStyle + "-fx-background-color: red; -fx-min-width: 80; ");
    breakButton.setOnAction(e-> clear());

    Button saveButton = new Button("Save to file");
    saveButton.setStyle(buttonStyle + "-fx-background-color: green; -fx-min-width: 180; ");

    Button addTransform = new Button("Add transform");
    addTransform.setStyle(buttonStyle + "-fx-background-color: black; -fx-min-width: 180; ");

    HBox topButtonBox = new HBox(20);
    topButtonBox.getChildren().addAll(playButton, breakButton);


    VBox buttonBox = new VBox(20);
    buttonBox.getChildren().addAll(addTransform, new VBox(), topButtonBox, saveButton);
    buttonBox.setAlignment(Pos.BASELINE_CENTER);
    buttonBox.setPadding(new Insets(0,0,100,0));

    VBox contentBox = new VBox(10);
    VBox.setVgrow(contentBox, Priority.ALWAYS);

    transformDropDown.setOnAction(e-> {
      String selectedTransform = transformDropDown.getValue();

      switch (selectedTransform) {
        case "Create Affine Transform":
          contentBox.getChildren().clear();
          contentBox.getChildren().addAll(stepsArea, transformsArea, minMaxBox, getAffineSetUp());
          break;
        case "Sierpinski Triangle":
          setDescription(ChaosGameDescriptionFactory.createSierpinski());
          contentBox.getChildren().clear();
          contentBox.getChildren().addAll(stepsArea, transformsArea, minMaxBox, getAffineSetUp());
          break;
        case "Barnsley Fern":
          setDescription(ChaosGameDescriptionFactory.createBarnsleyFern());
          contentBox.getChildren().clear();
          contentBox.getChildren().addAll(stepsArea, transformsArea, minMaxBox, getAffineSetUp());
          break;
        case "Create Julia Transform":
          contentBox.getChildren().clear();
          contentBox.getChildren().addAll(stepsArea, transformsArea, minMaxBox, getJuliaSetUp());
          break;
        case "Julia Transform":
          setDescription(ChaosGameDescriptionFactory.createJuliaTransformation(new Complex(-0.7, 0.1)));
          contentBox.getChildren().clear();
          contentBox.getChildren().addAll(stepsArea, transformsArea, minMaxBox, getJuliaSetUp());
          break;
        default:
          contentBox.getChildren().clear();
          contentBox.getChildren().addAll(stepsArea, transformsArea, minMaxBox);
      }
    });



// Wrapper VBox for other components
    contentBox.getChildren().addAll(stepsArea, transformsArea, minMaxBox);

    sidebarMenu.getChildren().addAll(contentBox, buttonBox);
    sidebarMenu.setPadding(new Insets(60));
    borderPane.setLeft(sidebarMenu);

  }
  private VBox getAffineSetUp() {
    // Create min/ max area
    Text aMatrixLabel = new Text("Matrix A: ");
    aMatrixLabel.setStyle("-fx-font-size: 14;");

    TextField a00Input = new TextField();
    a00Input.setMaxWidth(80);
    a00Input.setMinHeight(30);
    a00Input.setStyle("-fx-background-radius: 10");

    TextField a01Input = new TextField();
    a01Input.setMaxWidth(80);
    a01Input.setMinHeight(30);
    a01Input.setStyle("-fx-background-radius: 10");

    TextField a10Input = new TextField();
    a10Input.setMaxWidth(80);
    a10Input.setMinHeight(30);
    a10Input.setStyle("-fx-background-radius: 10");

    TextField a11Input = new TextField();
    a11Input.setMaxWidth(80);
    a11Input.setMinHeight(30);
    a11Input.setStyle("-fx-background-radius: 10");

    HBox a0 = new HBox(20);
    HBox a1 = new HBox(20);

    a0.getChildren().addAll(a00Input, a01Input);
    a1.getChildren().addAll(a10Input, a11Input);

    VBox matrix = new VBox(10);
    matrix.getChildren().addAll(aMatrixLabel, a0, a1);


    Text bVectorLabel = new Text("Vector b: ");
    bVectorLabel.setStyle("-fx-font-size: 14;");

    TextField b1Input = new TextField();
    b1Input.setMaxWidth(80);
    b1Input.setMinHeight(30);
    b1Input.setStyle("-fx-background-radius: 10");

    TextField b2Input = new TextField();
    b2Input.setMaxWidth(80);
    b2Input.setMinHeight(30);
    b2Input.setStyle("-fx-background-radius: 10");

    HBox b = new HBox(10);
    b.getChildren().addAll(b1Input, b2Input);

    VBox vector = new VBox(10);
    vector.getChildren().addAll(bVectorLabel, b);

    return new VBox(20, matrix, vector);
  }
  public VBox getJuliaSetUp() {
    Text complexLabel = new Text("Complex: ");
    complexLabel.setStyle("-fx-font-size: 14;");

    Text realLabel = new Text(" Real: ");
    realLabel.setStyle("-fx-font-size: 12;");
    TextField realInput = new TextField();
    realInput.setMaxWidth(80);
    realInput.setMinHeight(30);
    realInput.setStyle("-fx-background-radius: 10");

    VBox realBox = new VBox(10);
    realBox.getChildren().addAll(realLabel, realInput);

    Text imaginaryLabel = new Text(" Imaginary: ");
    imaginaryLabel.setStyle("-fx-font-size: 12");
    TextField imaginaryInput = new TextField();
    imaginaryInput.setMaxWidth(80);
    imaginaryInput.setMinHeight(30);
    imaginaryInput.setStyle("-fx-background-radius: 10");

    VBox imaginaryBox = new VBox(10);
    imaginaryBox.getChildren().addAll(imaginaryLabel, imaginaryInput);

    HBox inputBoxArea = new HBox(20);
    inputBoxArea.getChildren().addAll(realBox, imaginaryBox);

    return new VBox(15, complexLabel, inputBoxArea);
  }

  private void setStepsAction(TextField textField) {
    int steps = Integer.parseInt(textField.getText());
    setSteps(steps);
  }
}
