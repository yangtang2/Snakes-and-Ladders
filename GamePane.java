
/**
 * Write a description of class GamePane here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javafx.scene.text.FontWeight;
import javafx.application.Application;
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
import java.util.* ;
public class GamePane extends Pane{
    private Cell[][] cells=new Cell[10][10];
    private Cell[][] topCells=new Cell[10][10];
    private Text[][] texts=new Text[10][10];
    private GridPane gridPane=new GridPane();
    private GridPane topGridPane=new GridPane();
    
    public GamePane(){
        
        //Initiaze the texts
        for(int i=0; i<10; i++) {
        	for(int j=0; j<10; j++)
                    texts[i][j]=new Text();
        }
        
        //Add cells onto gridPane
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                gridPane.add(cells[i][j]=new Cell(), j, i);
            }
        }
        
        //Add topCells onto topGridPane
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                topGridPane.add(topCells[i][j]=new Cell(), j, i);
            }
        }
        
        //Add texts onto the cells
        for(int i=0; i<10; i++){
        	for(int j=0; j<10; j++)
        	    cells[i][j].getChildren().add(texts[i][j]);
        }
        
        //Set font for the texts
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++)
                texts[i][j].setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        }
        
        //Set value for texts
        int m=100;
        int n=81;
        for(int i=0; i<10; i++){
            boolean bm=false;
            boolean bn=false;
            for(int j=0; j<10; j++){
                if((i+2)%2==0){
                    int im=m-j;
                    String sm=""+im;
                    texts[i][j].setText(sm);
                    bm=true;
            }
                else{
                    int in=n+j;
                    String sn=""+in;
                    texts[i][j].setText(sn);
                    bn=true;
            }
        }
        
            if(bm)
                m-=20;
            else
                n-=20;
        }
        
        //Set background for the game arena
        for(int i=0; i<10; i++){
            if(i%2==0){
                for(int j=0; j<10; j++){
                    if(j%2!=0)
                        cells[i][j].setStyle("-fx-background-color: red");
                }
            }
            else{
                for(int j=0; j<10; j++){
                    if(j%2==0)
                        cells[i][j].setStyle("-fx-background-color: red");
                }
            }
        }
        
        //Add gridPane to pane
        addElements();
    }
    
    //Show the image of player without selected
    public void showPlayer(Player player, int playerNumber){
        topGridPane.getChildren().removeAll(player.player, player.playerSelected);
        topGridPane.add(player.player, player.getXPosition(), player.getYPosition());
        if(playerNumber==1)
            topGridPane.setHalignment(player.player, HPos.LEFT);
        else
            topGridPane.setHalignment(player.player, HPos.RIGHT);
    }
    
    //show the image of player selected
    public void showSelectedPlayer(Player player, int playerNumber){
        topGridPane.getChildren().removeAll(player.player, player.playerSelected);
        topGridPane.add(player.playerSelected, player.getXPosition(), player.getYPosition());
        if(playerNumber==1)
            topGridPane.setHalignment(player.playerSelected, HPos.LEFT);
            else
            topGridPane.setHalignment(player.playerSelected, HPos.RIGHT);
    }
    
    //Define how to move the player
    public void move(Player player, int playerNumber, int dieValue){
        if(player.getYPosition()%2!=0){
             if(player.getXPosition()+dieValue>9){
                 player.setXPosition(19-player.getXPosition()-dieValue);
                 player.setYPosition(player.getYPosition()-1);
                 showPlayer(player, playerNumber);
                }
             else{
                 player.setXPosition(player.getXPosition()+dieValue);
                 showPlayer(player, playerNumber);
             }
            }
        else{
             if(player.getYPosition()==0&&player.getXPosition()-dieValue<0){
                 player.setXPosition(dieValue-player.getXPosition());
                 showPlayer(player, playerNumber);
                }
             else if(player.getXPosition()-dieValue<0){
                player.setXPosition(dieValue-player.getXPosition()-1);
                player.setYPosition(player.getYPosition()-1);
                showPlayer(player, playerNumber);
             }
             else{
                player.setXPosition(player.getXPosition()-dieValue);
                showPlayer(player, playerNumber);
            }
        }
    }
    
    //Add gridPane, snakes, ladders and rockets onto game arena
    public void addElements(){
        ImageView snake0=new ImageView("image/snake1.png");
        snake0.setFitHeight(360);
        snake0.setFitWidth(380);
        snake0.setX(170);
        snake0.setY(150);
        ImageView snake1=new ImageView("image/snake1.png");
        snake1.setFitHeight(300);
        snake1.setFitWidth(300);
        snake1.setX(365);
        snake1.setY(365);
        ImageView snake2=new ImageView("image/snake2.png");
        snake2.setFitHeight(300);
        snake2.setFitWidth(200);
        snake2.setX(435);
        snake2.setY(70);
        ImageView snake3=new ImageView("image/snake3.png");
        snake3.setFitHeight(400);
        snake3.setFitWidth(340);
        snake3.setX(210);
        snake3.setY(250);
        ImageView snake4=new ImageView("image/snake4.png");
        snake4.setFitHeight(180);
        snake4.setFitWidth(180);
        snake4.setX(150);
        snake4.setY(70);
        ImageView snake5=new ImageView("image/snake5.png");
        snake5.setFitHeight(400);
        snake5.setFitWidth(250);
        snake5.setX(0);
        snake5.setY(20);
        ImageView snake6=new ImageView("image/snake6.png");
        snake6.setFitHeight(220);
        snake6.setFitWidth(230);
        snake6.setX(100);
        snake6.setY(330);
        
        ImageView ladder0=new ImageView("image/ladder.png");
        ladder0.setFitHeight(250);
        ladder0.setFitWidth(45);
        ladder0.setX(300);
        ladder0.setY(359);   
        ladder0.setRotate(-15);
        ImageView ladder1=new ImageView("image/ladder.png");
        ladder1.setFitHeight(242);
        ladder1.setFitWidth(45);
        ladder1.setX(42);
        ladder1.setY(299);   
        ladder1.setRotate(15);
        ImageView ladder2=new ImageView("image/ladder.png");
        ladder2.setFitHeight(245);
        ladder2.setFitWidth(45);
        ladder2.setX(232);
        ladder2.setY(33);   
        ladder2.setRotate(15);
        ImageView ladder3=new ImageView("image/ladder.png");
        ladder3.setFitHeight(110);
        ladder3.setFitWidth(40);
        ladder3.setX(565);
        ladder3.setY(38);   
        ladder3.setRotate(-30);
        ImageView ladder4=new ImageView("image/ladder.png");
        ladder4.setFitHeight(150);
        ladder4.setFitWidth(40);
        ladder4.setX(460);
        ladder4.setY(155);   
        ladder4.setRotate(-45);
        
        ImageView rocket0=new ImageView("image/rocket1.png");
        rocket0.setFitHeight(550);
        rocket0.setFitWidth(180);
        rocket0.setX(350);
        rocket0.setY(180);   
        rocket0.setRotate(48);
        ImageView rocket1=new ImageView("image/rocket2.png");
        rocket1.setFitHeight(550);
        rocket1.setFitWidth(180);
        rocket1.setX(383);
        rocket1.setY(-29);   
        rocket1.setRotate(-30);
        
        getChildren().addAll(gridPane, ladder4, snake0, snake1, snake2, snake3, snake6, ladder0, ladder1, snake5, ladder2, snake4, ladder3, rocket0, rocket1, topGridPane);
    }
    
    //Inner class of grid unit
    class Cell extends StackPane{
    public Cell(){      
        setPrefSize(65, 65);
        setAlignment(Pos.CENTER);    
    }
    }
}


