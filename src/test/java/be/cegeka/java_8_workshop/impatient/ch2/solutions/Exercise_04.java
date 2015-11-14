package be.cegeka.java_8_workshop.impatient.ch2.solutions;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_04 {

    @Test
    public void solution() throws Exception {
        int[] values = { 1, 4, 9, 16 };

        Stream<int[]> valuesStream = Stream.of(values);
        IntStream valuesIntStream = IntStream.of(values);
        Stream<Integer> intValuesStream = IntStream.of(values).boxed();

        assertThat(valuesStream.count()).isEqualTo(1);
        assertThat(valuesIntStream.count()).isEqualTo(4);
        assertThat(intValuesStream.count()).isEqualTo(4);
    }
}
