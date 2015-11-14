package be.cegeka.java_8_workshop.impatient.ch3.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_23 {

    @Test
    public void exercise_23() {
        List<Pair<String>> list = Arrays.asList(new Pair<>("a", "b"), new Pair<>("c", "d"));

        List<String> result = list.stream().map((p) -> p.obj1).collect(Collectors.toList());
        assertThat(result).containsExactly("a", "c");
    }

}
