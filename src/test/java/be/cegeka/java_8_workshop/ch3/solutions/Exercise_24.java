package be.cegeka.java_8_workshop.ch3.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_24 {

    @Test
    public void exercise_24() {
        List<Pair<String>> list = Arrays.asList(new Pair<>("a", "b"), new Pair<>("c", "d"));

        List<String> result = list.stream().flatMap(stringPair -> Stream.of(stringPair.obj1, stringPair.obj2)).collect(Collectors.toList());

        assertThat(result).containsExactly("a", "b", "c", "d");
    }
}
