package be.cegeka.java_8_workshop.ch3.solutions;

import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Exercise_16 {

    @Test
    public void solution() {
        doInOrderAsync(() -> System.out.print("First"), () -> System.out.println("Second"), Throwable::printStackTrace);
        doInOrderAsync(() -> "First", (s, t) -> {
            if (s != null) {
                System.out.println(s);
            } else {
                t.printStackTrace();
            }
        });

        // TODO: Discuss use case
    }

    public static void doInOrderAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        new Thread(() -> {
            try {
                first.run();
                second.run();
            } catch (Throwable t) {
                handler.accept(t);
            }
        }).start();
    }

    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        new Thread(() -> {
            try {
                T result = first.get();
                second.accept(result, null);
            } catch (Throwable t) {
                second.accept(null, t);
            }
        }).start();
    }
}
