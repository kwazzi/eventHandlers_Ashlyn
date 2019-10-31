package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        Scene scene = new Scene(pane,500,500);

        Circle circle = new Circle();
        circle.setRadius(80);
        circle.setCenterX(250);
        circle.setCenterY(250);
        Color circleColor = Color.rgb(205,12,189);
        circle.setFill(circleColor);

        pane.getChildren().add(circle);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Handle Events");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
