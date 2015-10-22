package be.cegeka.java_8_workshop.ch5.solutions;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_03 {

    @Test
    public void solution() throws Exception {
        LocalDate nextWorkDay = LocalDate.of(2015, Month.OCTOBER, 9).with(next(w -> w.getDayOfWeek().getValue() < 6));

        assertThat(nextWorkDay).isEqualTo(LocalDate.of(2015, Month.OCTOBER, 12));
    }

    public static TemporalAdjuster next(Predicate<LocalDate> predicate) {
        return TemporalAdjusters.ofDateAdjuster(d -> {
            LocalDate date = d;
            do {
                date = date.plusDays(1);
            } while (!predicate.test(date));
            return date; 
        });
    }
}
