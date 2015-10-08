package be.cegeka.java_8_workshop.ch2.solutions;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_08 {

    @Test
    public void solution() throws Exception {
        Stream<Integer> odds = Stream.of(1, 3, 5, 7, 9, 11, 13, 15);
        Stream<Integer> evens = Stream.of(2, 4, 6, 8, 10, 12);

        List<Integer> result = zip(odds, evens).collect(Collectors.toList());

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> secondIter = second.iterator();
        return first.flatMap(t -> secondIter.hasNext() ? Stream.of(t, secondIter.next()) : null);
    }
}
