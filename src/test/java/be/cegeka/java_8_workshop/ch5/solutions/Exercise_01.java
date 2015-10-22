package be.cegeka.java_8_workshop.ch5.solutions;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static java.time.Month.JANUARY;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_01 {

    @Test
    public void solution() throws Exception {
        assertThat(getProgrammersDay(2014)).isEqualTo(LocalDate.of(2014, SEPTEMBER, 13));
        assertThat(getProgrammersDay(2015)).isEqualTo(LocalDate.of(2015, SEPTEMBER, 13));
        assertThat(getProgrammersDay(2016)).isEqualTo(LocalDate.of(2016, SEPTEMBER, 12));

        assertThat(getProgrammersDayBis(2014)).isEqualTo(LocalDate.of(2014, SEPTEMBER, 13));
        assertThat(getProgrammersDayBis(2015)).isEqualTo(LocalDate.of(2015, SEPTEMBER, 13));
        assertThat(getProgrammersDayBis(2016)).isEqualTo(LocalDate.of(2016, SEPTEMBER, 12));
    }

    public static LocalDate getProgrammersDay(int year) {
        return LocalDate.of(year, JANUARY, 1).plus(Period.ofDays(255));
    }

    public static LocalDate getProgrammersDayBis(int year) {
        return LocalDate.of(year, JANUARY, 1).with(w -> w.plus(255, ChronoUnit.DAYS));
    }

}
