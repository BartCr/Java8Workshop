package be.cegeka.java_8_workshop.ch6.solutions;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercise_11 {
    private static final Random random = new Random();

    @Test
    public void solution() throws Exception {
        int secretNumber = getRandomNumber();
        System.out.println("Secret: " + secretNumber);
        repeat(Exercise_11::getRandomNumber, (g) -> {
            System.out.println("Guessed: " + g);
            return g == secretNumber;
        }).thenAccept(g -> System.out.println("Correct!!!"));

        ForkJoinPool.commonPool().awaitQuiescence(3, TimeUnit.MINUTES);
    }

    private static int getRandomNumber() {
        return random.nextInt(500) + 1;
    }

    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        return CompletableFuture.supplyAsync(action)
                .thenApplyAsync(r -> until.test(r) ? r : repeat(action, until).join());
    }
}
