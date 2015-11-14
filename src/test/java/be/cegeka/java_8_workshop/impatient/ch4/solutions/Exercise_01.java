package be.cegeka.java_8_workshop.impatient.ch4.solutions;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class Exercise_01 extends ApplicationTest {

    private Label label;
    private TextField textField;

    @Test
    public void solution() throws Exception {
        clickOn(textField);
        write("World!");
        verifyThat(label, hasText("HelloWorld!"));

        doubleClickOn(textField);
        write("TestFX Rocks!");
        verifyThat(label, hasText("TestFX Rocks!"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        label = new Label("Hello");
        label.setFont(new Font(100));
        textField = new TextField(label.getText());

        label.textProperty().bindBidirectional(textField.textProperty());

        Scene scene = new Scene(new VBox(10, label, textField), 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
