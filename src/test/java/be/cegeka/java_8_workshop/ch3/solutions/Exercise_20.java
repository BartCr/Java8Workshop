package be.cegeka.java_8_workshop.ch3.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_20 {

    @Test
    public void solution() {
        List<String> input = Arrays.asList("1", "2", "3");

        assertThat(map(input, Long::valueOf)).containsOnlyOnce(1L, 2L, 3L);

    }

    private static <T, U> List<U> map(List<T> list, Function<T, U> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }
}
