package be.cegeka.java_8_workshop.ch5.solutions;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_05 {

    @Test
    public void solution() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate aWeekAgo = today.minusWeeks(1);

        assertThat(countDaysSinceBirthday(yesterday)).isEqualTo(1);
        assertThat(countDaysSinceBirthday(aWeekAgo)).isEqualTo(7);

    }

    public static long countDaysSinceBirthday(LocalDate birthday) {
        return birthday.until(LocalDate.now(), ChronoUnit.DAYS);
    }
}
