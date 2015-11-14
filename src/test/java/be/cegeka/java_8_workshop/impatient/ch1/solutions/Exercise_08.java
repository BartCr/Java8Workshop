package be.cegeka.java_8_workshop.impatient.ch1.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Exercise_08 {
    @Test
    public void solution() throws InterruptedException {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println("forEach: " + name));
        }

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            runners.add(() -> System.out.println("indexed: " + name));
        }

        for (Runnable runner : runners) {
            new Thread(runner).start();
            Thread.sleep(500);
        }
    }
}
