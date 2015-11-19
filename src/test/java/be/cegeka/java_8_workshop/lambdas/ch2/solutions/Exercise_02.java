package be.cegeka.java_8_workshop.lambdas.ch2.solutions;

import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_02 {

    /**
     * @see ThreadLocal#withInitial(Supplier)
     */
    public void a() throws Exception {
    }

    @Test
    public void b() throws Exception {
        ThreadLocal<? extends DateFormat> formatter =
                ThreadLocal.withInitial(() -> DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK));

        LocalDate localDate = LocalDate.of(1970, Month.JANUARY, 1);
        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        assertThat(formatter.get().format(date)).isEqualTo("01-Jan-1970");

        /** In Java 8 better to us {@link java.time.format.DateTimeFormatter}, as it is thread-safe */
    }

}
