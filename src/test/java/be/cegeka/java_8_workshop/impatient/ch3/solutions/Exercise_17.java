package be.cegeka.java_8_workshop.impatient.ch3.solutions;

import org.junit.Test;

import java.util.function.Consumer;

public class Exercise_17 {

    @Test
    public void solution() {
        doInParallelAsync(() -> System.out.print("First"), () -> System.out.println("Second"), Throwable::printStackTrace);
    }

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        new Thread(() -> {
            try {
                first.run();
            } catch (Throwable e) {
                handler.accept(e);
            }
        }).start();
        new Thread(() -> {
            try {
                second.run();
            } catch (Throwable e) {
                handler.accept(e);
            }
        }).start();
    }
}
