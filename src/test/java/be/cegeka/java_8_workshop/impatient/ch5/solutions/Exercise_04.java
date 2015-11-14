package be.cegeka.java_8_workshop.impatient.ch5.solutions;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_04 {

    @Test
    public void solution() throws Exception {
        System.out.println(cal(10, 2015));
        assertThat(cal(10, 2015)).isEqualTo(
                " M  T  W  T  F  S  S\n" +
                "          1  2  3  4 \n" +
                " 5  6  7  8  9 10 11 \n" +
                "12 13 14 15 16 17 18 \n" +
                "19 20 21 22 23 24 25 \n" +
                "26 27 28 29 30 31 ");
    }

    private String cal(int month, int year) {
        LocalDate date = LocalDate.of(year, month, 1);
        StringBuilder sb = new StringBuilder(" M  T  W  T  F  S  S\n");
        Stream.of(DayOfWeek.values()).filter((d) -> d.compareTo(date.getDayOfWeek()) > 0).forEach((d) -> sb.append("   "));

        LocalDate tempDate = date;
        while (tempDate.getMonth().getValue() == month) {
            sb.append(String.format("%2d ", tempDate.getDayOfMonth()));
            if (tempDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sb.append('\n');
            }
            tempDate = tempDate.plusDays(1);
        }
        return sb.toString();
    }
}
