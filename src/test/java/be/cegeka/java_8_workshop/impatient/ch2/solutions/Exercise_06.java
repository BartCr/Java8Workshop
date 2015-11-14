package be.cegeka.java_8_workshop.impatient.ch2.solutions;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exercise_06 {

    @Test
    public void solution() throws Exception {
        List<Character> result = characterStream("Hello World").collect(Collectors.toList());

        Assertions.assertThat(result).containsExactly('H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd');
    }

    public static Stream<Character> characterStream(String s) {
        return IntStream.rangeClosed(0, s.length() - 1).mapToObj(s::charAt);
    }
}
