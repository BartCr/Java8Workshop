package be.cegeka.java_8_workshop.ch2.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_10 {

    @Test
    public void solution() throws Exception {
        List<Double> doubles = Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0);

        final AtomicInteger count = new AtomicInteger();
        Double result = doubles.stream().reduce(0d,
                (total, current) -> {
                    int number = count.incrementAndGet();
                    return (total * (number - 1) + current) / number;
                });
        assertThat(result).isEqualTo(2.0);

        double simple = doubles.stream().mapToDouble(d -> d).average().getAsDouble();
        assertThat(simple).isEqualTo(2.0);
    }
}
