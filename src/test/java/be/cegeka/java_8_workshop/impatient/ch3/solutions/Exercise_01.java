package be.cegeka.java_8_workshop.impatient.ch3.solutions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.function.Supplier;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Exercise_01 {

    private static final Logger LOGGER = Logger.getLogger("my junit-test logger");
    private static final Handler HANDLER = mock(Handler.class);

    @Before
    public void setupLogger() {
        when(HANDLER.getLevel()).thenReturn(Level.INFO);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(HANDLER);
        LOGGER.setLevel(Level.INFO);
    }

    @Test
    public void solution() {
        logIf(Level.INFO, () -> false, () -> "notLogged");
        logIf(Level.FINE, () -> true, () -> "notLogged");
        logIf(Level.INFO, () -> true, () -> "logged");

        ArgumentCaptor<LogRecord> captor = ArgumentCaptor.forClass(LogRecord.class);
        verify(HANDLER).publish(captor.capture());

        LogRecord logRecord = captor.getValue();
        assertThat(logRecord.getLevel()).isEqualTo(Level.INFO);
        assertThat(logRecord.getMessage()).isEqualTo("logged");
    }

    public static void logIf(Level level, Supplier<Boolean> condition, Supplier<String> message) {
        if (LOGGER.isLoggable(level) && condition.get()) {
            LOGGER.log(level, message.get());
        }
    }
}
