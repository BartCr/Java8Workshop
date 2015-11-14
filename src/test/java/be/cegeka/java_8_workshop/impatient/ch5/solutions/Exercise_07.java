package be.cegeka.java_8_workshop.impatient.ch5.solutions;

import org.junit.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_07 {

    @Test
    public void solution() throws Exception {

        /*

          10:00  +-------+-------+
                 |  One  |       |
          11:00  +-------+  Two  |
                 |       |       |
          12:00  | Three +-------+
                 |       |       |
          13:00  +-------+  Four |
                 |  Five |       |
          14:00  +---------------+
         */
        TimeInterval intOne = new TimeInterval(LocalTime.of(10, 0), LocalTime.of(11, 0));
        TimeInterval intTwo = new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0));
        TimeInterval intThree = new TimeInterval(LocalTime.of(11, 0), LocalTime.of(13, 0));
        TimeInterval intFour = new TimeInterval(LocalTime.of(12, 0), LocalTime.of(14, 0));
        TimeInterval intFive = new TimeInterval(LocalTime.of(13, 0), LocalTime.of(14, 0));

        assertThat(intOne.overlaps(intTwo)).isTrue();
        assertThat(intOne.overlaps(intThree)).isFalse();
        assertThat(intOne.overlaps(intFour)).isFalse();
        assertThat(intOne.overlaps(intFive)).isFalse();

        assertThat(intTwo.overlaps(intOne)).isTrue();
        assertThat(intTwo.overlaps(intThree)).isTrue();
        assertThat(intTwo.overlaps(intFour)).isFalse();
        assertThat(intTwo.overlaps(intFive)).isFalse();

        assertThat(intThree.overlaps(intOne)).isFalse();
        assertThat(intThree.overlaps(intTwo)).isTrue();
        assertThat(intThree.overlaps(intFour)).isTrue();
        assertThat(intThree.overlaps(intFive)).isFalse();

        assertThat(intFour.overlaps(intOne)).isFalse();
        assertThat(intFour.overlaps(intTwo)).isFalse();
        assertThat(intFour.overlaps(intThree)).isTrue();
        assertThat(intFour.overlaps(intFive)).isTrue();

        assertThat(intFive.overlaps(intOne)).isFalse();
        assertThat(intFive.overlaps(intTwo)).isFalse();
        assertThat(intFive.overlaps(intThree)).isFalse();
        assertThat(intFive.overlaps(intFour)).isTrue();
    }

    public static class TimeInterval {

        private final LocalTime startTime;
        private final LocalTime endTime;

        public TimeInterval(LocalTime startTime, LocalTime endTime) {
            if (!endTime.isAfter(startTime)) throw new IllegalArgumentException("endTime should be after beforeTime");
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean overlaps(TimeInterval other) {
            if (startTime.isBefore(other.startTime)) {
                return other.startTime.isBefore(endTime);
            } else {
                return startTime.isBefore(other.endTime);
            }
        }
    }
}
