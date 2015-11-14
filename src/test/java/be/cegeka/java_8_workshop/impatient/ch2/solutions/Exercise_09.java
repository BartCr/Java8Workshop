package be.cegeka.java_8_workshop.impatient.ch2.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_09 {

    @Test
    public void solution() throws Exception {

        List<Integer> first = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> second = Arrays.asList(6, 7, 8);

        List<Integer> resultOne = Stream.of(first, second).reduce((l1, l2) -> {
                    ArrayList<Integer> result = new ArrayList<>(l1);
                    result.addAll(l2);
                    return result;
                }
        ).orElse(Collections.emptyList());
        assertThat(resultOne).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> resultTwo = Stream.of(first, second).reduce(new ArrayList<>(), (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                }
        );
        assertThat(resultTwo).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> resultThree = Stream.of(first, second).reduce(new ArrayList<>(),
                (a, l) -> {
                    a.addAll(l);
                    return a;
                },
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                }
        );
        assertThat(resultThree).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);

    }
}
