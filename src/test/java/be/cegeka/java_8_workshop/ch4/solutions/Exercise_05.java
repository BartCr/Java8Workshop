package be.cegeka.java_8_workshop.ch4.solutions;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Exercise_05 extends Application {

    private Button smaller;
    private Button larger;
    private Slider gauge;

    public static <T, R> ObservableValue<R> observe(Function<T, R> function, ObservableValue<T> observableValue) {
        return new SimpleObjectProperty<>(function.apply(observableValue.getValue()));
    }

    public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> function, ObservableValue<T> observableValue1, ObservableValue<U> observableValue2) {
        return new SimpleObjectProperty<>(function.apply(observableValue1.getValue(), observableValue2.getValue()));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        smaller = new Button("<<");
        larger = new Button(">>");
        gauge = new Slider(0, 100, 50);

        larger.disableProperty().bind(observe(t -> t.intValue() >= 100, gauge.valueProperty()));
        smaller.disableProperty().bind(observe(t -> t.intValue() <= 0, gauge.valueProperty()));

        primaryStage.setScene(new Scene(new HBox(smaller, gauge, larger)));
        primaryStage.show();
    }
}
