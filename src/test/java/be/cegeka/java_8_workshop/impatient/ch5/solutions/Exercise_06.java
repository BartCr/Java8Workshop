package be.cegeka.java_8_workshop.impatient.ch5.solutions;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_06 {

    @Test
    public void solution() throws Exception {
        assertThat(getFriday13th().collect(Collectors.toList())).containsExactly(
                LocalDate.parse("1901-09-13"), LocalDate.parse("1901-12-13"), LocalDate.parse("1902-06-13"), LocalDate.parse("1903-02-13"),
                LocalDate.parse("1903-03-13"), LocalDate.parse("1903-11-13"), LocalDate.parse("1904-05-13"), LocalDate.parse("1905-01-13"),
                LocalDate.parse("1905-10-13"), LocalDate.parse("1906-04-13"), LocalDate.parse("1906-07-13"), LocalDate.parse("1907-09-13"),
                LocalDate.parse("1907-12-13"), LocalDate.parse("1908-03-13"), LocalDate.parse("1908-11-13"), LocalDate.parse("1909-08-13"),
                LocalDate.parse("1910-05-13"), LocalDate.parse("1911-01-13"), LocalDate.parse("1911-10-13"), LocalDate.parse("1912-09-13"),
                LocalDate.parse("1912-12-13"), LocalDate.parse("1913-06-13"), LocalDate.parse("1914-02-13"), LocalDate.parse("1914-03-13"),
                LocalDate.parse("1914-11-13"), LocalDate.parse("1915-08-13"), LocalDate.parse("1916-10-13"), LocalDate.parse("1917-04-13"),
                LocalDate.parse("1917-07-13"), LocalDate.parse("1918-09-13"), LocalDate.parse("1918-12-13"), LocalDate.parse("1919-06-13"),
                LocalDate.parse("1920-02-13"), LocalDate.parse("1920-08-13"), LocalDate.parse("1921-05-13"), LocalDate.parse("1922-01-13"),
                LocalDate.parse("1922-10-13"), LocalDate.parse("1923-04-13"), LocalDate.parse("1923-07-13"), LocalDate.parse("1924-06-13"),
                LocalDate.parse("1925-02-13"), LocalDate.parse("1925-03-13"), LocalDate.parse("1925-11-13"), LocalDate.parse("1926-08-13"),
                LocalDate.parse("1927-05-13"), LocalDate.parse("1928-01-13"), LocalDate.parse("1928-04-13"), LocalDate.parse("1928-07-13"),
                LocalDate.parse("1929-09-13"), LocalDate.parse("1929-12-13"), LocalDate.parse("1930-06-13"), LocalDate.parse("1931-02-13"),
                LocalDate.parse("1931-03-13"), LocalDate.parse("1931-11-13"), LocalDate.parse("1932-05-13"), LocalDate.parse("1933-01-13"),
                LocalDate.parse("1933-10-13"), LocalDate.parse("1934-04-13"), LocalDate.parse("1934-07-13"), LocalDate.parse("1935-09-13"),
                LocalDate.parse("1935-12-13"), LocalDate.parse("1936-03-13"), LocalDate.parse("1936-11-13"), LocalDate.parse("1937-08-13"),
                LocalDate.parse("1938-05-13"), LocalDate.parse("1939-01-13"), LocalDate.parse("1939-10-13"), LocalDate.parse("1940-09-13"),
                LocalDate.parse("1940-12-13"), LocalDate.parse("1941-06-13"), LocalDate.parse("1942-02-13"), LocalDate.parse("1942-03-13"),
                LocalDate.parse("1942-11-13"), LocalDate.parse("1943-08-13"), LocalDate.parse("1944-10-13"), LocalDate.parse("1945-04-13"),
                LocalDate.parse("1945-07-13"), LocalDate.parse("1946-09-13"), LocalDate.parse("1946-12-13"), LocalDate.parse("1947-06-13"),
                LocalDate.parse("1948-02-13"), LocalDate.parse("1948-08-13"), LocalDate.parse("1949-05-13"), LocalDate.parse("1950-01-13"),
                LocalDate.parse("1950-10-13"), LocalDate.parse("1951-04-13"), LocalDate.parse("1951-07-13"), LocalDate.parse("1952-06-13"),
                LocalDate.parse("1953-02-13"), LocalDate.parse("1953-03-13"), LocalDate.parse("1953-11-13"), LocalDate.parse("1954-08-13"),
                LocalDate.parse("1955-05-13"), LocalDate.parse("1956-01-13"), LocalDate.parse("1956-04-13"), LocalDate.parse("1956-07-13"),
                LocalDate.parse("1957-09-13"), LocalDate.parse("1957-12-13"), LocalDate.parse("1958-06-13"), LocalDate.parse("1959-02-13"),
                LocalDate.parse("1959-03-13"), LocalDate.parse("1959-11-13"), LocalDate.parse("1960-05-13"), LocalDate.parse("1961-01-13"),
                LocalDate.parse("1961-10-13"), LocalDate.parse("1962-04-13"), LocalDate.parse("1962-07-13"), LocalDate.parse("1963-09-13"),
                LocalDate.parse("1963-12-13"), LocalDate.parse("1964-03-13"), LocalDate.parse("1964-11-13"), LocalDate.parse("1965-08-13"),
                LocalDate.parse("1966-05-13"), LocalDate.parse("1967-01-13"), LocalDate.parse("1967-10-13"), LocalDate.parse("1968-09-13"),
                LocalDate.parse("1968-12-13"), LocalDate.parse("1969-06-13"), LocalDate.parse("1970-02-13"), LocalDate.parse("1970-03-13"),
                LocalDate.parse("1970-11-13"), LocalDate.parse("1971-08-13"), LocalDate.parse("1972-10-13"), LocalDate.parse("1973-04-13"),
                LocalDate.parse("1973-07-13"), LocalDate.parse("1974-09-13"), LocalDate.parse("1974-12-13"), LocalDate.parse("1975-06-13"),
                LocalDate.parse("1976-02-13"), LocalDate.parse("1976-08-13"), LocalDate.parse("1977-05-13"), LocalDate.parse("1978-01-13"),
                LocalDate.parse("1978-10-13"), LocalDate.parse("1979-04-13"), LocalDate.parse("1979-07-13"), LocalDate.parse("1980-06-13"),
                LocalDate.parse("1981-02-13"), LocalDate.parse("1981-03-13"), LocalDate.parse("1981-11-13"), LocalDate.parse("1982-08-13"),
                LocalDate.parse("1983-05-13"), LocalDate.parse("1984-01-13"), LocalDate.parse("1984-04-13"), LocalDate.parse("1984-07-13"),
                LocalDate.parse("1985-09-13"), LocalDate.parse("1985-12-13"), LocalDate.parse("1986-06-13"), LocalDate.parse("1987-02-13"),
                LocalDate.parse("1987-03-13"), LocalDate.parse("1987-11-13"), LocalDate.parse("1988-05-13"), LocalDate.parse("1989-01-13"),
                LocalDate.parse("1989-10-13"), LocalDate.parse("1990-04-13"), LocalDate.parse("1990-07-13"), LocalDate.parse("1991-09-13"),
                LocalDate.parse("1991-12-13"), LocalDate.parse("1992-03-13"), LocalDate.parse("1992-11-13"), LocalDate.parse("1993-08-13"),
                LocalDate.parse("1994-05-13"), LocalDate.parse("1995-01-13"), LocalDate.parse("1995-10-13"), LocalDate.parse("1996-09-13"),
                LocalDate.parse("1996-12-13"), LocalDate.parse("1997-06-13"), LocalDate.parse("1998-02-13"), LocalDate.parse("1998-03-13"),
                LocalDate.parse("1998-11-13"), LocalDate.parse("1999-08-13"), LocalDate.parse("2000-10-13"));
    }

    public static Stream<LocalDate> getFriday13th() {
        return StreamSupport.stream(
                takeWhile(
                        Stream.iterate(LocalDate.of(1901, Month.JANUARY, 13), (d) -> d.plusMonths(1)).filter((d) -> d.getDayOfWeek() == DayOfWeek.FRIDAY)
                                .spliterator(),
                        (d) -> d.getYear() < 2001),
                false);
    }

    static <T> Spliterator<T> takeWhile(Spliterator<T> splitr, Predicate<? super T> predicate) {
        return new Spliterators.AbstractSpliterator<T>(splitr.estimateSize(), 0) {
            boolean stillGoing = true;

            @Override
            public boolean tryAdvance(Consumer<? super T> consumer) {
                if (stillGoing) {
                    boolean hadNext = splitr.tryAdvance(elem -> {
                        if (predicate.test(elem)) {
                            consumer.accept(elem);
                        } else {
                            stillGoing = false;
                        }
                    });
                    return hadNext && stillGoing;
                }
                return false;
            }
        };
    }
}
