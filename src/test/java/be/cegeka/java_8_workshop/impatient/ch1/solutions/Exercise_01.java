package be.cegeka.java_8_workshop.impatient.ch1.solutions;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_01 {

    @Test
    public void solution() {
        Thread mainThread = Thread.currentThread();

        String[] array = {"one", "two"};

        Arrays.sort(array, (o1, o2) -> {
            assertThat(mainThread).isSameAs(Thread.currentThread());
            return o1.compareTo(o2);
        });
    }

}
