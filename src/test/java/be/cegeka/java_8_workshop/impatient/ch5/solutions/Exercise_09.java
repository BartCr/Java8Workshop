package be.cegeka.java_8_workshop.impatient.ch5.solutions;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_09 {

    @Test
    public void solution() throws Exception {
        Instant now = Instant.now();
        List<ZoneOffset> result = ZoneId.getAvailableZoneIds().stream().map(zone -> ZoneId.of(zone).getRules().getOffset(now)).filter(offset -> offset
                .getTotalSeconds() % 3600 != 0)
                .sorted().distinct().collect(Collectors.toList());

        assertThat(result)
                .containsExactly(
                        ZoneOffset.ofHoursMinutes(13, 45), ZoneOffset.ofHoursMinutes(11, 30), ZoneOffset.ofHoursMinutes(10, 30),
                        ZoneOffset.ofHoursMinutes(9, 30), ZoneOffset.ofHoursMinutes(8, 45), ZoneOffset.ofHoursMinutes(6, 30),
                        ZoneOffset.ofHoursMinutes(5, 45), ZoneOffset.ofHoursMinutes(5, 30), ZoneOffset.ofHoursMinutes(4, 30),
                        ZoneOffset.ofHoursMinutes(3, 30), ZoneOffset.ofHoursMinutes(-2, -30), ZoneOffset.ofHoursMinutes(-4, -30),
                        ZoneOffset.ofHoursMinutes(-9, -30));
    }
}
