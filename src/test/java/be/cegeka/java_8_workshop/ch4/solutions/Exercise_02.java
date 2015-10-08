package be.cegeka.java_8_workshop.ch4.solutions;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import org.junit.Test;

@SuppressWarnings("ALL")
public class Exercise_02 {

    @Test
    public void solution() throws Exception {

    }


    private static class MyApp extends Application {

        private int value = 0;
        private IntegerProperty valueProperty;

        public int getValue() {
            return valueProperty() != null ? valueProperty.get() : value;
        }

        public void setValue(int value) {
            if (valueProperty() != null) {
                valueProperty.set(value);
            } else {
                this.value = value;
            }
        }

        public IntegerProperty valueProperty() {
            if (valueProperty == null) {
                valueProperty = new SimpleIntegerProperty(value);
            }
            return valueProperty;
        }

        @Override
        public void start(Stage primaryStage) throws Exception {

        }
    }
}
