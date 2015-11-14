package be.cegeka.java_8_workshop.impatient.ch5.solutions;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_08 {

    @Test
    public void solution() throws Exception {
        Instant now = Instant.now();
        List<ZoneOffset> result = ZoneId.getAvailableZoneIds().stream().map(zone -> ZoneId.of(zone).getRules().getOffset(now)).sorted().distinct().collect
                (Collectors.toList());

        assertThat(result)
                .containsExactly(
                        ZoneOffset.ofHours(14), ZoneOffset.ofHoursMinutes(13, 45), ZoneOffset.ofHours(13), ZoneOffset.ofHours(12),
                        ZoneOffset.ofHoursMinutes(11, 30), ZoneOffset.ofHours(11), ZoneOffset.ofHoursMinutes(10, 30), ZoneOffset.ofHours(10),
                        ZoneOffset.ofHoursMinutes(9, 30), ZoneOffset.ofHours(9), ZoneOffset.ofHoursMinutes(8, 45), ZoneOffset.ofHours(8),
                        ZoneOffset.ofHours(7), ZoneOffset.ofHoursMinutes(6, 30), ZoneOffset.ofHours(6), ZoneOffset.ofHoursMinutes(5, 45),
                        ZoneOffset.ofHoursMinutes(5, 30), ZoneOffset.ofHours(5), ZoneOffset.ofHoursMinutes(4, 30), ZoneOffset.ofHours(4),
                        ZoneOffset.ofHoursMinutes(3, 30), ZoneOffset.ofHours(3), ZoneOffset.ofHours(2), ZoneOffset.ofHours(1),
                        ZoneOffset.ofHours(0), ZoneOffset.ofHours(-1), ZoneOffset.ofHours(-2), ZoneOffset.ofHoursMinutes(-2, -30),
                        ZoneOffset.ofHours(-3), ZoneOffset.ofHours(-4), ZoneOffset.ofHoursMinutes(-4, -30), ZoneOffset.ofHours(-5),
                        ZoneOffset.ofHours(-6), ZoneOffset.ofHours(-7), ZoneOffset.ofHours(-8), ZoneOffset.ofHours(-9),
                        ZoneOffset.ofHoursMinutes(-9, -30), ZoneOffset.ofHours(-10), ZoneOffset.ofHours(-11), ZoneOffset.ofHours(-12));
    }
}
