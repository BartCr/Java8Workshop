package be.cegeka.java_8_workshop.ch2.solutions;

import be.cegeka.java_8_workshop.TextReader;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_12 {

    @Test
    public void solution() throws Exception {
        List<String> words = TextReader.getWordsFromFile("/war-and-peace.txt");

        AtomicIntegerArray shortWords = new AtomicIntegerArray(12);
        words.parallelStream().forEach(w -> {
            if (w.length() < 12) shortWords.getAndIncrement(w.length());
        });

        assertThat(shortWords.get(0)).isEqualTo(0);
        assertThat(shortWords.get(1)).isEqualTo(21309);
        assertThat(shortWords.get(2)).isEqualTo(95294);
        assertThat(shortWords.get(3)).isEqualTo(143475);
        assertThat(shortWords.get(4)).isEqualTo(98438);
        assertThat(shortWords.get(5)).isEqualTo(58417);
        assertThat(shortWords.get(6)).isEqualTo(49014);
        assertThat(shortWords.get(7)).isEqualTo(42496);
        assertThat(shortWords.get(8)).isEqualTo(29715);
        assertThat(shortWords.get(9)).isEqualTo(17487);
        assertThat(shortWords.get(10)).isEqualTo(10681);
        assertThat(shortWords.get(11)).isEqualTo(4412);
    }
}
