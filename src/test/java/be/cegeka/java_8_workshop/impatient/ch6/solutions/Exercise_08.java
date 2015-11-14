package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Exercise_08 {

    @Test
    public void solution() throws Exception {
        for (int i = 750_000; i < 100_000_000; i+=1) {
            int[] array = createArray(i);

            Stopwatch stopwatch = Stopwatch.createStarted();
            Arrays.sort(array);
            stopwatch.stop();
            long linear = stopwatch.elapsed(TimeUnit.MILLISECONDS);

            stopwatch.reset();

            array = createArray(i);
            stopwatch.start();
            Arrays.parallelSort(array);
            stopwatch.stop();
            long parallel = stopwatch.elapsed(TimeUnit.MILLISECONDS);

            if (parallel < linear) {
                System.out.println("parallel = " + parallel);
                System.out.println("linear = " + linear);
                System.out.println("Array of size: " + i);
                break;
            }
            if (i % 1000 == 0) {
                System.out.println(i);
                System.out.println("parallel = " + parallel);
                System.out.println("linear = " + linear);
            }
        }
    }

    private int[] createArray(int size) {
        int[] array = new int[size];
        Arrays.setAll(array, i -> size - i);
        return array;
    }

}
