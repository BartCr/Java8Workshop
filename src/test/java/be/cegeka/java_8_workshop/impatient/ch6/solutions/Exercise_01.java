package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import be.cegeka.java_8_workshop.TextReader;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAccumulator;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_01 {

    @Test
    public void solution() throws Exception {
        AtomicReference<String> longestWord = new AtomicReference<>();
        LongAccumulator longestLength = new LongAccumulator(Math::max, 0);
        List<String> words = TextReader.getWordsFromFile("/alice.txt");
        words.parallelStream().forEach(
                next -> longestWord.updateAndGet(
                        current -> {
                            String result = next.length() > longestLength.intValue() ? next : current;
                            longestLength.accumulate(next.length());
                            return result;
                        }));
        assertThat(longestWord.get()).isEqualTo("unenforceability");
        assertThat(longestLength.get()).isEqualTo(16);
    }
}
