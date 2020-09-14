
/**
 * Write a description of class DicePane here.
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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
public class DicePane extends StackPane{
    private ImageView[] dice=new ImageView[6];
    private FadeTransition ft1=new FadeTransition(Duration.millis(1000), this);
    private int dieValue;
    private int rolledTimes=0;
    protected DoubleProperty dieValueProperty=new SimpleDoubleProperty();
    
    //Initiate six dice images and set the size
    public DicePane(){
        dice[0]=new ImageView("image/diceuno.png");
        dice[1]=new ImageView("image/dicedos.png");
        dice[2]=new ImageView("image/dicetres.png");
        dice[3]=new ImageView("image/dicecuatro.png");
        dice[4]=new ImageView("image/dicecinco.png");
        dice[5]=new ImageView("image/diceseis.png");
        for(int i=0; i<dice.length; i++){
            dice[i].setFitHeight(80);
            dice[i].setFitWidth(80);
        }
        getChildren().add(dice[0]);
    }
    
    //Change the number of die using gradient aniamtion
    public void changeDice(){
        this.getChildren().clear();
        double medium=Math.random()*6;
        dieValueProperty.set(medium);
        dieValue=(int)(medium);
        getChildren().add(dice[dieValue]);
        ft1.setFromValue(0);
        ft1.setToValue(1);
        ft1.setCycleCount(1);
        ft1.play();
        dieValueProperty.doubleValue();
        rolledTimes++;
    }
    public int getDieValue(){
        return dieValue;
    }
    public int getRolledTimes(){
        return rolledTimes;
    }
    public void setRolledTimes(int rolledTimes){
        this.rolledTimes=rolledTimes;
    }
}
