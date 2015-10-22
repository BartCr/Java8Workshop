package be.cegeka.java_8_workshop.ch5.solutions;

import org.junit.Test;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_10 {

    @Test
    public void solution() throws Exception {
        ZonedDateTime departure = ZonedDateTime.of(2014, 5, 10, 15, 5, 0, 0, ZoneId.of("America/Los_Angeles"));

        assertThat(calculateArrival(departure, Duration.ofHours(10).plusMinutes(50), ZoneId.of("Europe/Berlin")))
                .isEqualTo(ZonedDateTime.of(2014, 5, 11, 10, 55, 0, 0, ZoneId.of("Europe/Berlin")));
    }

    private static ZonedDateTime calculateArrival(ZonedDateTime departure, TemporalAmount duration, ZoneId arrivalZone) {
        return departure.toInstant().atZone(arrivalZone).plus(duration);
    }
}
