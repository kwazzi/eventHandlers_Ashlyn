/*
//// INSTRUCTIONS ////////////////////////////////
//  Double Click - changes color to coral       //
//  Normal Click - changes color to blue        //
//  Right Click - adds a circle to the corner   //
//  Shift - makes circle bigger                 //
//  Enter - makes circle smaller                //
//  Space - moves the circle                    //
//////////////////////////////////////////////////
*/
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        Scene scene = new Scene(pane,500,500);
        
        // creating the main circle
        Circle circle = drawCircle(Color.RED, 80, 250,250);
 /////////////// MOUSE EVENTS /////////////////////////////////
 
        // creates a new circle when you right click on the main circle
        circle.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton() == MouseButton.SECONDARY){
                pane.getChildren().add(drawCircle(Color.BLUE, 20, 50, 50));
            }
            // changes the color of the circle when you double click on the main circle
            else if(event.getClickCount() == 2){
                circle.setFill(Color.CORAL);
            }
            // changes the color of the circle when you click on the main circle
            else if(event.getButton() == MouseButton.PRIMARY){
                circle.setFill(Color.CADETBLUE);
            }
        });
////////////// KEYEVENTS ///////////////////
        scene.setOnKeyPressed((KeyEvent event) -> {
            // makes circle smaller if they click enter
            if(event.getCode() == KeyCode.ENTER){
                circle.setRadius(20);
            }
            // makes circle bigger if they click shift
            else if(event.getCode() == KeyCode.SHIFT){
                circle.setRadius(120);
            }
            // moves position of the circle
            else if(event.getCode() == KeyCode.SPACE){
                circle.setCenterX(220);
                circle.setCenterY(220);
            }
        });
        
        pane.getChildren().add(circle);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Handle Events");
        primaryStage.show();
    }

    public Circle drawCircle(Color color, double radius, double x, double y){
        Circle temp = new Circle();
        temp.setRadius(radius);
        temp.setCenterX(x);
        temp.setCenterY(y);
        temp.setFill(color);
        return temp;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

