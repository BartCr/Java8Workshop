package be.cegeka.java_8_workshop.impatient.ch3.solutions;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.Test;

public class Exercise_05 {

    @Test
    public void exercise_05() {
        Image image = new WritableImage(100, 100);
        transform(image, (x, y, color) -> x < 10 || x > image.getWidth() - 10 || y < 10 || y > image.getHeight() - 10 ? Color.GRAY : color);
    }

    public static Image transform(Image in, ColorTransformer ct) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y,
                        ct.apply(x, y, in.getPixelReader().getColor(x, y)));
        return out;
    }

    @FunctionalInterface
    public interface ColorTransformer {
        Color apply(int x, int y, Color colorAtXY);
    }

}
