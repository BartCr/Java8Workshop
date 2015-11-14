package be.cegeka.java_8_workshop.impatient.ch2.solutions;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise_05 {

    @Test
    public void solution() throws Exception {
        List<Long> result =
                randomStream(25214903917L, 11, (long) Math.pow(2, 48), 0)
                        .limit(5)
                        .collect(Collectors.<Long>toList());

        Assertions.assertThat(result).containsExactly(0L, 11L, 277363943098L, 11718085204285L, 49720483695876L);

    }

    public static Stream<Long> randomStream(long a, long c, long m, long seed) {
        return Stream.iterate(seed, l -> (a * l + c) % m);
    }
}
