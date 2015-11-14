package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import be.cegeka.java_8_workshop.TextReader;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_07 {

    @Test
    public void solution() throws Exception {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        TextReader.getWordsFromFile("/alice.txt")
                .stream()
                .forEach(w -> map.computeIfAbsent(w, k -> (long) k.length()));
        Map.Entry<String, Long> entry =
                map.reduceEntries(
                        Runtime.getRuntime().availableProcessors(),
                        (v1, v2) -> v1.getValue() > v2.getValue() ? v1 : v2
                );
        assertThat(entry.getKey()).isEqualTo("unenforceability");
    }
}
