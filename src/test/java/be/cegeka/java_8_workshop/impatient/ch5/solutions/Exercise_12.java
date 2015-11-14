package be.cegeka.java_8_workshop.impatient.ch5.solutions;

import org.junit.Test;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_12 {

    public static final Appointment APPOINTMENT_OCT_13_10_00_AM = new Appointment("appointment-one", 2015, 10, 13, 10, 0, ZoneId.of("Europe/Brussels"));
    public static final Appointment APPOINTMENT_OCT_13_10_30_AM = new Appointment("appointment-two", 2015, 10, 13, 10, 30, ZoneId.of("Europe/Brussels"));
    public static final Appointment APPOINTMENT_OCT_13_11_00_AM = new Appointment("appointment-three", 2015, 10, 13, 11, 0, ZoneId.of("Europe/Brussels"));

    private List<Appointment> appointments = Arrays.asList(APPOINTMENT_OCT_13_10_00_AM, APPOINTMENT_OCT_13_10_30_AM, APPOINTMENT_OCT_13_11_00_AM);;


    @Test
    public void solution() throws Exception {
        assertThat(getAppointmentDueNextHour(appointments, ZonedDateTime.of(2015, 10, 12, 23, 30, 0, 0, ZoneId.of("America/Los_Angeles"))))
                .containsEntry(APPOINTMENT_OCT_13_10_00_AM, true)
                .containsEntry(APPOINTMENT_OCT_13_10_30_AM, false)
                .containsEntry(APPOINTMENT_OCT_13_11_00_AM, false);
    }

    public static Map<Appointment, Boolean> getAppointmentDueNextHour(List<Appointment> appointments, ZonedDateTime currentTime) {
        return appointments.stream().collect(Collectors.toMap(a -> a, a -> Duration.between(a.dateTime.toInstant(), currentTime.toInstant()).abs().toHours() <= 1));
    }

    private static class Appointment {

        private final String title;
        private final ZonedDateTime dateTime;

        public Appointment(String title, int year, int month, int dayOfMonth, int hour, int minute, ZoneId zoneId) {
            this.title = title;
            this.dateTime = ZonedDateTime.of(year, month, dayOfMonth, hour, minute, 0, 0, zoneId);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Appointment that = (Appointment) o;
            return Objects.equals(title, that.title) && Objects.equals(dateTime, that.dateTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, dateTime);
        }
    }

}
