package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_03 {

    @Test
    public void atomicLong() throws Exception {
        AtomicLong result = checkPerformance(new AtomicLong(0), AtomicLong::getAndIncrement);
        assertThat(result.get()).isEqualTo(100_000_000);
    }

    @Test
    public void longAdder() throws Exception {
        LongAdder result = checkPerformance(new LongAdder(), LongAdder::increment);
        assertThat(result.sum()).isEqualTo(100_000_000);
    }

    private <T> T checkPerformance(T t, Consumer<T> consumer) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        Stopwatch stopwatch = Stopwatch.createUnstarted();

        stopwatch.start();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100_000; j++) {
                    consumer.accept(t);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(15, TimeUnit.SECONDS);
        stopwatch.stop();
        System.out.println(stopwatch);
        return t;
    }
}
