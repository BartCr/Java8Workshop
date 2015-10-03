package be.cegeka.java_8_workshop.ch3.solutions;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
public class Exercise_21 {

    @Test
    public void solution() throws Exception {
        Future<String> result = map(CompletableFuture.completedFuture("Test"), s -> {
            return new StringBuilder(s).reverse().toString();
        });

        assertThat(result.get()).isEqualTo("tseT");
    }

    public static <T, U> Future<U> map(Future<T> future, Function<T, U> function) {
        return new Future<U>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return function.apply(future.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return function.apply(future.get(timeout, unit));
            }
        };
    }
}
