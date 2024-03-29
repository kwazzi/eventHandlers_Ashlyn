/*
//// INSTRUCTIONS /////////////////////////////////////
//  Double Click - changes color to coral            //
//  Normal Click - changes color to blue             //
//  Right Click - adds a circle to the corner        //
//  Arrow Keys - moves the circle                    //
//  Enter - changes the circles size                 //
//  Space - moves the circle to a random position    //
///////////////////////////////////////////////////////
*/
package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        Scene scene = new Scene(pane,500,500);
        Rectangle rect = drawFruit();
        pane.getChildren().add(rect);
        int score = 0;

        // creating the main circle
        Circle circle = drawCircle(Color.RED, 40, 250,250);
        /////////////// MOUSE EVENTS /////////////////////////////////

        // creates a new circle when you right click on the main circle
        circle.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton() == MouseButton.SECONDARY){
                Circle thisCircle = drawCircle(Color.DARKSLATEBLUE, 20, 50,50);
                pane.getChildren().add(thisCircle);
                animate(thisCircle);
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
        double speed = 10;
        scene.setOnKeyPressed((KeyEvent event) -> {
            // makes circle smaller if they click enter
            if(event.getCode() == KeyCode.ENTER){
                circle.setRadius(Math.random() * 100);
                moveFruit(rect, circle);
            }
            // moves the circle whereever they chose
            else if(event.getCode() == KeyCode.UP){
                double y = circle.getCenterY() - speed;
                if(circle.getCenterY() < 0){
                    y = circle.getCenterY();
                }
                circle.setCenterY(y);
                moveFruit(rect, circle);
            }
            else if(event.getCode() == KeyCode.DOWN){
                double y = circle.getCenterY() + speed;
                if(circle.getCenterY() > 500){
                    y = circle.getCenterY();
                }
                circle.setCenterY(y);
                moveFruit(rect, circle);
            }
            else if(event.getCode() == KeyCode.RIGHT){
                double x = circle.getCenterX() + speed;
                if(circle.getCenterX() > 500){
                    x = circle.getCenterX();
                }
                circle.setCenterX(x);
            }
            else if(event.getCode() == KeyCode.LEFT){
                double x = circle.getCenterX() - speed;
                if(circle.getCenterX() < 0){
                    x = circle.getCenterX();
                }
                circle.setCenterX(x);
                moveFruit(rect, circle);
            }
            // moves position of the circle
            else if(event.getCode() == KeyCode.SPACE){
                circle.setCenterX(Math.random() * 500);
                circle.setCenterY(Math.random() * 500);
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

    public Rectangle drawFruit(){
        Rectangle rect = new Rectangle();
        rect.setHeight(20);
        rect.setWidth(20);
        double x = Math.random() * 480;
        double y = Math.random() * 480;
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.DEEPSKYBLUE);
        return rect;
    }

    public void animate(Circle circle){
        PathTransition pt = new PathTransition(Duration.millis(5000),
           new Line(100,200,100,20), circle     );
        pt.setCycleCount(5);
        pt.play();

    }

    public void moveFruit(Rectangle rect, Circle circle){
        double top = circle.getCenterY() - circle.getRadius();
        double bottom = circle.getCenterY() + circle.getRadius();
        double right = circle.getCenterX() + circle.getRadius();
        double left = circle.getCenterX() - circle.getRadius();
        boolean moved = false;

        boolean a = circle.getCenterX() > rect.getX() && circle.getCenterX() < rect.getX() + rect.getHeight();
        boolean b = circle.getCenterY() > rect.getY() && circle.getCenterY() < rect.getY() + rect.getWidth();
        boolean rightPos = right > rect.getX() && right < rect.getX() + rect.getWidth() && right < rect.getY() + rect.getHeight() && right > rect.getY();
        boolean leftPos = left > rect.getX() && left < rect.getX() + rect.getWidth();
        boolean topPos = top < (rect.getY() + rect.getHeight()) && top > rect.getY();
        if(a && b || rightPos){
            double x = Math.random() * 480;
            double y = Math.random() * 480;
            rect.setX(x);
            rect.setY(y);
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}