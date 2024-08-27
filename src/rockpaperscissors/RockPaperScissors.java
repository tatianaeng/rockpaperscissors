/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rockpaperscissors;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import java.util.Random;


/**
 *
 * @author tatianaeng
 */

/*
DIRECTIONS:
Write a program that lets the user play the game of Rock, Paper, Scissors against the computer. The program should work as follows:
When the program begins, a random number in the range of 0 through 2 is generated. 
If the number is 0, then the computer has chosen rock. 
If the number is 1, then the computer has chosen paper. 
If the number is 2, then the computer has chosen scissors. (Don’t display the computer’s choice yet.)
To make his or her selection, the user clicks an image on the screen.
The computer’s choice is displayed.

A winner is selected according to the following rules:
If one player chooses rock and the other player chooses scissors, then rock wins. (The rock smashes the scissors.)
If one player chooses scissors and the other player chooses paper, then scissors wins. (Scissors cuts paper.)
If one player chooses paper and the other player chooses rock, then paper wins. (Paper wraps rock.)
If both players make the same choice, the game is a tie.

Have a way to play again or end the game.
Keep track of the number of the wins for the player and the number of wins for the computer.
Display the win totals in your GUI while the game is being played. 
*/

public class RockPaperScissors extends Application
{

    /**
     * @param args the command line arguments
     */
    
    // variables
    final int sceneWidth = 500;
    final int sceneHeight = 400;
    
    int computerNumberSelection;
    int userScore = 0;
    int computerScore = 0;
    
    ImageView rockImageView;
    ImageView paperImageView;
    ImageView scissorsImageView;
    
    Label selections;
    Label score;
    
    Random rand;

    String computerChoice;
    String userChoice;
    
    String rock = "ROCK";
    String paper = "PAPER";
    String scissors = "SCISSORS";
    
    public static void main(String[] args) 
    {
        // launch the application
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        // set the stage title
        primaryStage.setTitle("Rock, Paper, Scissors Game: by Tatiana Eng and Orlando Marin");
        
        // add an opening message with user instructions
        Label userInstructions = new Label("Select an image below.");
        
        // create a rockPhoto object
        Image rockPhoto = new Image("file:/Users/orlandomarin/Downloads/JavaProjectRock.png");
        
        // create an imageview object that holds the rock image
        rockImageView = new ImageView(rockPhoto);
        
        // create a paperPhoto object
        Image paperPhoto = new Image("file:/Users/orlandomarin/Downloads/JavaProjectPaper.png");
        
        // create an imageview object that holds the paper image
        paperImageView = new ImageView(paperPhoto);
        
        // create a scissorsPhoto object
        Image scissorsPhoto = new Image("file:/Users/orlandomarin/Downloads/JavaProjectScissors.png");
        
        // create an imageview object that holds the scissors image
        scissorsImageView = new ImageView(scissorsPhoto);
        
        // create a "play again" button
        Button playAgain = new Button("Play again");
        
        // create an "end game" button
        Button endGame = new Button("End game");
        
        // add a message wishing the user good luck that will eventually show what the user and the computer chose
        selections = new Label("Good luck! The results will appear here once you make a selection.");
        
        // add a message that displays the score between the user and computer
        score = new Label("The score will appear here.");
        
        // create an HBox for the rock, paper, and scissors images
        HBox hbox1 = new HBox(25,rockImageView, paperImageView, scissorsImageView);
        
        // center hbox1
        hbox1.setAlignment(Pos.CENTER);
        
        // create an HBox for the "play again" and "end game" buttons
        HBox hbox2 = new HBox(25,playAgain, endGame);
        
        // center hbox2
        hbox2.setAlignment(Pos.CENTER);
        
        // create a VBox that holds the messages and HBoxes
        VBox vbox = new VBox(25,userInstructions, hbox1, selections, score, hbox2);
        
        // center the VBox
        vbox.setAlignment(Pos.CENTER);
        
        // ADD FUNCTIONALITY
        // generate a random number for the computer's choice (0 is Rock, 1 is Paper, and 2 is Scissors)
        rand = new Random();
        newRandomInt();
        
        // event handler for when the user selects rock
        // update the selections message, don't let the user select another image, and update the user instructions message
        rockImageView.setOnMouseClicked(event ->
                {
                    userChoice = rock;
                    updateSelections();
                    disableImageViewInteraction();
                    userInstructions.setText("Click 'Play again' or 'End game'.");
                });
        
        // event handler for when the user selects paper
        // update the selections message, don't let the user select another image, and update the user instructions message
        paperImageView.setOnMouseClicked(event ->
                {
                    userChoice = paper;
                    updateSelections();
                    disableImageViewInteraction();
                    userInstructions.setText("Click 'Play again' or 'End game'.");
                });
        
        // event handler for when the user selects scissors
        // update the selections message, don't let the user select another image, and update the user instructions message
        scissorsImageView.setOnMouseClicked(event ->
                {
                    userChoice = scissors;
                    updateSelections();
                    disableImageViewInteraction();
                    userInstructions.setText("Click 'Play again' or 'End game'.");
                });  
        
        // add an event handler for when the user selects the play again button
        // computer gets a new random int, update the user instructions, selections, and score messages
        // allow the user to click on one of the images again and allow them to click on the "end game" button
        playAgain.setOnAction(event ->
                {
                    newRandomInt();
                    userInstructions.setText("Select an image below.");
                    selections.setText("Good luck! The results will appear here once you make a selection");
                    score.setText("The score will appear here.");
                    enableImageViewInteraction();
                    endGame.setDisable(false);
                });
        
        // add an event handler for when the user clicks on the "end game" button
        // update the selections and score message to show the final score
        // reset the user and computer scores, and disable interaction with the images (user cannot click on them)
        // disable the end game button so it can't be pressed again, and update the user instructions message
        endGame.setOnAction(event ->
                {
                    selections.setText("Thank you for playing!");
                    score.setText(String.format("The final score is...\nYou: %d\nComputer: %d", userScore, computerScore));
                    userScore = 0;
                    computerScore = 0;
                    disableImageViewInteraction();
                    endGame.setDisable(true);
                    userInstructions.setText("For more fun, click 'Play again'!");
                });
        
        // add the VBox to the scene
        Scene scene = new Scene(vbox, sceneWidth, sceneHeight);
        
        // add the scene to the stage
        primaryStage.setScene(scene);
        
        // show the stage
        primaryStage.show();
    }
    
    // create an updateSelections method to update the selections message after the user chooses an image
    public void updateSelections()
    {
        // add if statement for various outcomes, starting with tie outcomes, then outcomes where the user wins, then outcomes where the user loses
        if (userChoice.equals(computerChoice))
        {
            selections.setText(String.format("It's a tie! You chose %s, and the computer chose %s.", userChoice, computerChoice));
            score.setText(String.format("Your score: %d\nComputer's score: %d", userScore, computerScore));
        }
        else if ((userChoice.equals(rock) && computerChoice.equals(scissors)) || (userChoice.equals(paper) && computerChoice.equals(rock)) || (userChoice.equals(scissors) && computerChoice.equals(paper)))
        {
            selections.setText(String.format("You win! You chose %s, and the computer chose %s. ", userChoice, computerChoice));
            userScore += 1;
            score.setText(String.format("Your score: %d\nComputer's score: %d", userScore, computerScore));
        }
        else if ((userChoice.equals(rock) && computerChoice.equals(paper)) || (userChoice.equals(paper) && computerChoice.equals(scissors)) || (userChoice.equals(scissors) && computerChoice.equals(rock)))
        {
            selections.setText(String.format("You lose! You chose %s, and the computer chose %s. ", userChoice, computerChoice));
            computerScore += 1;
            score.setText(String.format("Your score: %d\nComputer's score: %d", userScore, computerScore));
        }
    }
    
    // create a method where the computer generates a random int
    public void newRandomInt()
    {
        // computer generates a new random integer between 0-2
        computerNumberSelection = rand.nextInt(3);
        
        // assign 0 to rock, 1 to paper, and 2 to scissors
        if (computerNumberSelection == 0)
        {
            computerChoice = rock;
        }
        else if (computerNumberSelection == 1)
        {
            computerChoice = paper;
        }
        else if (computerNumberSelection == 2)
        {
            computerChoice = scissors;
        }
    }
    
    // create a method to disable further interaction with the images after the user makes their selection
    public void disableImageViewInteraction()
    {
        rockImageView.setDisable(true);
        paperImageView.setDisable(true);
        scissorsImageView.setDisable(true);
    }
    
    // create a method to allow user to interact with images again
    public void enableImageViewInteraction()
    {
        rockImageView.setDisable(false);
        paperImageView.setDisable(false);
        scissorsImageView.setDisable(false);
    }
    
}

