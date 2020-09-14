
/**
 * Write a description of class GameEntry here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javafx.scene.text.FontWeight;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import java.sql.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.Slider;
import javafx.scene.control.ScrollBar;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.File;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.HPos;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.*;
public class GameEntry extends Application{
    private GamePane gamePane=new GamePane();
    private DicePane dicePane=new DicePane();
    private Player player1=new Player("image/P1.png", "image/P1_selected.png");
    private Player player2=new Player("image/P2.png", "image/P2_selected.png");
    private int playerOrder=1;
    private Label label=new Label("Player1 rolls!");
    private int player1Number;
    private int player2Number;
    private DieListenerClass listener=new DieListenerClass();
    
    @Override //Override the start method in the Application class
    public void start(Stage primaryStage){
        //Set the size of the players
        player1.player.setFitWidth(30);
        player2.player.setFitWidth(30);
        player1.player.setFitHeight(60);
        player2.player.setFitHeight(60);
        player1.playerSelected.setFitWidth(30);
        player2.playerSelected.setFitWidth(30);
        player1.playerSelected.setFitHeight(60);
        player2.playerSelected.setFitHeight(60);
        
        //Create the images of two static players
        ImageView staticPlayer1=new ImageView("image/staticPlayer1.png");
        ImageView staticPlayer2=new ImageView("image/staticPlayer2.png");
        staticPlayer1.setFitHeight(120);
        staticPlayer1.setFitWidth(50);
        staticPlayer2.setFitHeight(120);
        staticPlayer2.setFitWidth(50);
        
        
        //Initialize two players
        gamePane.showPlayer(player1, 1);
        gamePane.showPlayer(player2, 2);
        
        //Set the property of label text
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
        label.setTextFill(Color.ORANGE);
        label.setContentDisplay(ContentDisplay.BOTTOM);
        
        //Add listener for die
        dicePane.dieValueProperty.addListener(listener);
        
        //Set mouse click event for die and decide who go first
        dicePane.setOnMouseClicked(e->{
            dicePane.changeDice();
            if(dicePane.getRolledTimes()==1){
                player1Number=dicePane.getDieValue();
                label.setText("Player2 rolls!");
            }
            if(dicePane.getRolledTimes()==2){
                player2Number=dicePane.getDieValue();
                if(player1Number==player2Number)
                    dicePane.setRolledTimes(0);
                else if(player1Number>player2Number){
                    label.setText("Player1 go first!");
                    playerOrder=1;
                    gamePane.showSelectedPlayer(player1, 1); //If player1 go first, highlight it
                }
                else{
                    label.setText("Player2 go first!");
                    playerOrder=2;
                    gamePane.showSelectedPlayer(player2, 2); //If player2 go first, highlight it
                }
            }     
        }); 
        
        //Create a pane to hold the label
        StackPane stackPane=new StackPane();
        stackPane.getChildren().add(label);
        
        //Create a pane to hold two static players
        HBox hBox=new HBox(50);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(staticPlayer1, staticPlayer2);
        
        
        //Create a pane to hold two players and die
        VBox control=new VBox(45);
        control.setPadding(new Insets(5, 5, 5, 5));
        control.getChildren().addAll(dicePane, stackPane, hBox);
        VBox.setMargin(dicePane, new Insets(100));
        
        //Create a pane to hold the whole game arena
        BorderPane borderPane=new BorderPane();
        borderPane.setLeft(control);
        borderPane.setRight(gamePane);
        
        //Create a scene and place it in the stage
        Scene scene=new Scene(borderPane, 950, 650);
        primaryStage.setTitle("Snake and Ladder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //Move the player after rolling the die
    public void moveTo(){
        if(playerOrder==1){
            label.setText("Player2's turn!");
            gamePane.move(player1, 1, dicePane.getDieValue()+1);
            if(checkWin(player1)){
                label.setText("Player1 Wins!");
                dicePane.dieValueProperty.removeListener(listener);
            }
            transferTo(player1); //Transfer player1 if encountered snake, ladder or rocket
            gamePane.showPlayer(player1, 1);
            if(dicePane.getDieValue()==5){
                playerOrder=1;
                gamePane.showSelectedPlayer(player1, 1);//If the dieValue is 6, highlight player1 again
                gamePane.showPlayer(player2, 2);
            }
            else{
                playerOrder=2;
                gamePane.showSelectedPlayer(player2, 2);//If the dieValue is not 6, highlight player2 before moving it
            }
        }
        else{
            label.setText("Player1's turn!");
            gamePane.move(player2, 2, dicePane.getDieValue()+1);
            if(checkWin(player2)){
            label.setText("Player2 Wins!");
            dicePane.dieValueProperty.removeListener(listener);
            }
            transferTo(player2); //Transfer player2 if encountered snake, ladder or rocket
            gamePane.showPlayer(player2, 2);
            if(dicePane.getDieValue()==5){
                playerOrder=2;
                gamePane.showSelectedPlayer(player2, 2);//If the dieValue is 6, highlight player2 again
                gamePane.showPlayer(player1, 1);
            }
            else{
                playerOrder=1;
                gamePane.showSelectedPlayer(player1, 1);//If the dieValue is not 6, highlight player1 before moving it
            }
        }
    }
    
    //Transfer the player if encountered snake, ladder or rocket
    public void transferTo(Player player){
        if(player.getXPosition()==3&&player.getYPosition()==9){
            player.setXPosition(9);
            player.setYPosition(4);
            }
        if(player.getXPosition()==9&&player.getYPosition()==7){
            player.setXPosition(5);
            player.setYPosition(0);
            }
        if(player.getXPosition()==0&&player.getYPosition()==8){
            player.setXPosition(1);
            player.setYPosition(4);
            }
        if(player.getXPosition()==5&&player.getYPosition()==9){
            player.setXPosition(4);
            player.setYPosition(5);
            }
        if(player.getXPosition()==3&&player.getYPosition()==4){
            player.setXPosition(4);
            player.setYPosition(0);
            }
        if(player.getXPosition()==8&&player.getYPosition()==4){
            player.setXPosition(6);
            player.setYPosition(2);
            }
        if(player.getXPosition()==9&&player.getYPosition()==2){
            player.setXPosition(9);
            player.setYPosition(0);
            }
        if(player.getXPosition()==2&&player.getYPosition()==0){
            player.setXPosition(1);
            player.setYPosition(6);
            }
        if(player.getXPosition()==3&&player.getYPosition()==1){
            player.setXPosition(2);
            player.setYPosition(3);
            }
        if(player.getXPosition()==2&&player.getYPosition()==5){
            player.setXPosition(3);
            player.setYPosition(8);
            }
        if(player.getXPosition()==4&&player.getYPosition()==4){
            player.setXPosition(7);
            player.setYPosition(9);
            }
        if(player.getXPosition()==7&&player.getYPosition()==3){
            player.setXPosition(3);
            player.setYPosition(7);
            }
        if(player.getXPosition()==7&&player.getYPosition()==1){
            player.setXPosition(8);
            player.setYPosition(5);
            }
        if(player.getXPosition()==9&&player.getYPosition()==6){
            player.setXPosition(6);
            player.setYPosition(9);
            }
    }
        
    //Check whether the player win or not
    public boolean checkWin(Player player){
        if(player.getXPosition()==0&&player.getYPosition()==0)
            return true;
        else 
            return false;
    }
    
    //Inner class to define listener
    class DieListenerClass implements InvalidationListener{
        public void invalidated(Observable ov){
            if(dicePane.getRolledTimes()>2)
            moveTo();
        }
    }
}
    
   
        
    

    

