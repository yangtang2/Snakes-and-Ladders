
/**
 * Write a description of class Player here.
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
public class Player
{
    protected ImageView player;
    protected ImageView playerSelected;
    private int XPosition=0;
    private int YPosition=9;
    
    public Player(String position1, String position2){
        player=new ImageView(position1);
        playerSelected=new ImageView(position2);
    }
    public void setXPosition(int XPosition){
        this.XPosition=XPosition;
    }
    public void setYPosition(int YPosition){
        this.YPosition=YPosition;
    }
    public int getXPosition(){
        return XPosition;
    }
    public int getYPosition(){
        return YPosition;
    }
}
