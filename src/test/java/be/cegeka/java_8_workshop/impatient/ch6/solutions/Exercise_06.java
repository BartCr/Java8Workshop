package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import be.cegeka.java_8_workshop.TextReader;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_06 {

    @Test
    public void solution() throws Exception {
        ConcurrentHashMap<String, Set<String>> map = new ConcurrentHashMap<>();

        Arrays.asList("/alice.txt", "/war-and-peace.txt")
                .parallelStream()
                .forEach(f -> {
                    try {
                        TextReader.getWordsFromFile(f)
                                .stream()
                                .forEach(w -> map.computeIfAbsent(w, k -> new HashSet<>()).add(f));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
//        map.forEach((k, v) -> System.out.println(k + ": " + v));

        assertThat(map.get("peace")).containsOnly("/war-and-peace.txt");
        assertThat(map.get("Alice")).containsOnly("/alice.txt");
        assertThat(map.get("order")).containsOnly("/alice.txt", "/war-and-peace.txt");
        assertThat(map.get("Java")).isNull();
    }
}
