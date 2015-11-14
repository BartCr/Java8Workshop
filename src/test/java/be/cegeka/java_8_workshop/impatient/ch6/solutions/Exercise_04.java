package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import be.cegeka.java_8_workshop.TextReader;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_04 {

    @Test
    public void solution() throws Exception {
        List<String> words = TextReader.getWordsFromFile("/alice.txt");

        LongAccumulator maxLength = new LongAccumulator(Math::max, 0);
        LongAccumulator minLength = new LongAccumulator(Math::min, Long.MAX_VALUE);

        words.forEach(w -> {
            maxLength.accumulate(w.length());
            minLength.accumulate(w.length());
        });

        assertThat(maxLength.get()).isEqualTo(16);
        assertThat(minLength.get()).isEqualTo(0);
    }
}
