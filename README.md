# Chaos Games, Portfolio project IDATT2003

Student IDs: 
- Johanne Fixdal: 10020
- Sofia Håbrekke: 10039

## Background 

This product is a result of the semester project given to the students in BIDATA at NTNU, for the course IDATT2003. The project involves developing an application with a graphical user interface using Java and javafx. 


## Description 

This application is a chaos game, a game that iterates through a list of transformtions to create geometrical figures. When mapping transformations' end point, the results are geometrical figures that can be dispalyed on the screen. In this chaos game it is possible to map these figures on a screen, and make personal adjustments to the fractal displayed. 

## Link to repository
https://gitlab.stud.idi.ntnu.no/idatt2003.2024.group25/chaosgames

## To run the project: 
- Open the project in your IDE (ex. IntelliJ or Visual Studio Code)
- In the terminal write the command: mvn javafx:run

## To run the tests: 
- Open the project in your IDE (ex. IntelliJ or Visual Studio Code)
- In the terminal write the command: mvn test 

## Project Structure
### src: the package contaning the source code of the implementation. 
#### main: the folder contaning the functional code
The src is divided into three components: Model-View-Controller. 
- [Model](src/main/java/edu/ntnu/idatt2003/group25/model): The model component includes the logic. 
- [View](src/main/java/edu/ntnu/idatt2003/group25/view): The view component includes the frontend. 
- [Controller](src/main/java/edu/ntnu/idatt2003/group25/controller): The controller component handles user interaction and binds the view and the model. 

#### test: the folder contaning the JUnit tests. 
[Tests](src/test): The test folder contains JUnit test for the model classes.  


## Refrences
Under development ChatGPT and GitHub CoPilot have been used as a support, and to generate some javadocs. 

