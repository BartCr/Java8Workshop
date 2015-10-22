package be.cegeka.java_8_workshop.ch5.solutions;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.Month.FEBRUARY;
import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_02 {

    @Test
    public void solution() throws Exception {
        LocalDate startDate = LocalDate.of(2000, FEBRUARY, 29);

        assertThat(startDate.plusYears(4)).isEqualTo(LocalDate.of(2004, FEBRUARY, 29));

        assertThat(startDate.plusYears(1)).isEqualTo(LocalDate.of(2001, FEBRUARY, 28));
        assertThat(startDate.plusYears(1).plusYears(1)).isEqualTo(LocalDate.of(2002, FEBRUARY, 28));
        assertThat(startDate.plusYears(1).plusYears(1).plusYears(1)).isEqualTo(LocalDate.of(2003, FEBRUARY, 28));
        assertThat(startDate.plusYears(1).plusYears(1).plusYears(1).plusYears(1)).isEqualTo(LocalDate.of(2004, FEBRUARY, 28));
    }
}
