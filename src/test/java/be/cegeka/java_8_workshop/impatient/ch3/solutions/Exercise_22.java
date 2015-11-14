package be.cegeka.java_8_workshop.impatient.ch3.solutions;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_22 {
    @Test
    public void exercise_22() throws Exception {
        CompletableFuture<String> future = CompletableFuture.completedFuture("Test")
                .thenCompose(s -> CompletableFuture.completedFuture(new StringBuilder(s).reverse().toString()));

        assertThat(future.get()).isEqualTo("tseT");
    }
}
