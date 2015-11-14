package be.cegeka.java_8_workshop.impatient.ch1.solutions;

import be.cegeka.java_8_workshop.impatient.ch1.ex07.ChainedRunnable;
import org.junit.Test;

public class Exercise_07 {

    @Test
    public void solution() {
        new Thread(andThen(
                () -> System.out.println("1"),
                () -> System.out.println("2")
        )).start();


        new Thread(new ChainedRunnable(() -> System.out.println("1"))
                .andThen(() -> System.out.println("2"))
                .andThen(() -> System.out.println("3"))).start();
    }

    public static Runnable andThen(Runnable r1, Runnable r2) {
        return () -> {
            r1.run();
            r2.run();
        };
    }
}
