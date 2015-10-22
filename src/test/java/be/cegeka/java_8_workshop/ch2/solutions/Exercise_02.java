package be.cegeka.java_8_workshop.ch2.solutions;

import be.cegeka.java_8_workshop.TextReader;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_02 {

    @Test
    public void solution() throws Exception {
        List<String> words = TextReader.getWordsFromFile("/alice.txt");
        AtomicInteger invocationCounter = new AtomicInteger();
        AtomicInteger totalCounter = new AtomicInteger();

        long count = words.stream().filter(w -> {
            totalCounter.incrementAndGet();
            if (w.length() > 12) {
                invocationCounter.incrementAndGet();
                return true;
            }
            return false;
        }).limit(5).count();

        assertThat(invocationCounter.get()).isEqualTo(5);
        assertThat(count).isEqualTo(5);
        assertThat(totalCounter.get()).isGreaterThan(invocationCounter.get());

    }
}
