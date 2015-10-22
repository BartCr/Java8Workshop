package be.cegeka.java_8_workshop.ch5.solutions;

import org.junit.Test;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_11 {

    @Test
    public void solution() throws Exception {
        ZonedDateTime departure = ZonedDateTime.of(2014, 5, 10, 14, 5, 0, 0, ZoneId.of("Europe/Berlin"));
        ZonedDateTime arrival = ZonedDateTime.of(2014, 5, 10, 16, 40, 0, 0, ZoneId.of("America/Los_Angeles"));

        assertThat(calculateDuration(departure, arrival)).isEqualTo(Duration.ofHours(11).plusMinutes(35));
    }

    public static Duration calculateDuration(ZonedDateTime departure, ZonedDateTime arrival) {
        return Duration.between(departure.toInstant(), arrival.toInstant());
    }
}
