package be.cegeka.java_8_workshop.ch2.solutions;

import be.cegeka.java_8_workshop.ch2.TextReader;
import org.junit.Test;

import java.util.List;

public class Exercise_03 {

    @Test
    public void solution() throws Exception {
        List<String> words = TextReader.getWordsFromFile("/war-and-peace.txt");

        measure(() -> words.stream().filter(w -> w.length() > 12).count());

        measure(() -> words.parallelStream().filter(w -> w.length() > 12).count());
    }

    public void measure(Runnable runnable) throws InterruptedException {
        System.gc();
        System.gc();
        Thread.sleep(1000);
        long start = System.currentTimeMillis();
        runnable.run();
        long stop = System.currentTimeMillis();
        System.out.printf("Duration %d%n", stop - start);
    }
}
