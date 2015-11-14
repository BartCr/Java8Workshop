package be.cegeka.java_8_workshop.impatient.ch4.solutions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static javafx.beans.binding.Bindings.*;

public class Exercise_04 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle(50, 50, 50);
        circle.setFill(Color.web("#003366"));

        Scene scene = new Scene(new Pane(circle), 100, 100);

        circle.centerXProperty().bind(divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(divide(scene.heightProperty(), 2));
        circle.radiusProperty().bind(min(circle.centerXProperty(), circle.centerYProperty()));
        stage.setScene(scene);
        stage.show();
    }

}
