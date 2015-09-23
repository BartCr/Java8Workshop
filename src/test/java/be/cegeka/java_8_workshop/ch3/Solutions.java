package be.cegeka.java_8_workshop.ch3;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.function.*;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SuppressWarnings("ALL")
@RunWith(JUnitParamsRunner.class)
public class Solutions {

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
    public void exercise_01() {
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

    @Test
    public void exercise_02() {
        Lock lock = mock(Lock.class);
        Iterable iterable = mock(Iterable.class);

        withLock(lock, iterable::iterator);

        InOrder inOrder = inOrder(lock, iterable);

        inOrder.verify(lock).lock();
        inOrder.verify(iterable).iterator();
        inOrder.verify(lock).unlock();
    }

    public static void withLock(Lock lock, Runnable runnable) {
        lock.lock();
        try {
            runnable.run();
        } finally {
            lock.unlock();
        }
    }

    public void exercise_04() {
        // java.io.FileFilter
        // java.io.FilenameFilter           -> 2 parameters
        // java.nio.DirectoryStream.Filter
        // java.util.logging.Filter
    }

    @Test
    public void exercise_05() {
        Image image = new WritableImage(100, 100);
        transform(image, (x, y, color) -> x < 10 || x > image.getWidth() - 10 || y < 10 || y > image.getHeight() - 10 ? Color.GRAY : color);
    }

    public static Image transform(Image in, ColorTransformer ct) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y,
                        ct.apply(x, y, in.getPixelReader().getColor(x, y)));
        return out;
    }

    @FunctionalInterface
    public interface ColorTransformer {
        Color apply(int x, int y, Color colorAtXY);
    }

    @Test
    @Ignore
    public void exercise_06() {
        // Skipped
    }


    @Test
    public void exercise_07() {
        // default
        Comparator<String> comparator = caseComparator(false, false, false);
        assertThat(comparator.compare("abc", "def")).isLessThan(0);
        assertThat(comparator.compare("abc", "abc")).isEqualTo(0);
        assertThat(comparator.compare("def", "abc")).isGreaterThan(0);

        // case insensitive
        comparator = caseComparator(true, false, false);
        assertThat(comparator.compare("ABC", "def")).isLessThan(0);
        assertThat(comparator.compare("abc", "ABC")).isEqualTo(0);
        assertThat(comparator.compare("def", "ABC")).isGreaterThan(0);

        // space insensitive
        comparator = caseComparator(false, true, false);
        assertThat(comparator.compare("a b  c", "def")).isLessThan(0);
        assertThat(comparator.compare("abc", "a b  c")).isEqualTo(0);
        assertThat(comparator.compare("def", "a b  c")).isGreaterThan(0);

        // reverse
        comparator = caseComparator(false, false, true);
        assertThat(comparator.compare("abc", "def")).isGreaterThan(0);
        assertThat(comparator.compare("abc", "abc")).isEqualTo(0);
        assertThat(comparator.compare("def", "abc")).isLessThan(0);

        // combined
        comparator = caseComparator(true, true, true);
        assertThat(comparator.compare("A B  C", "def")).isGreaterThan(0);
        assertThat(comparator.compare("abc", "A B  C")).isEqualTo(0);
        assertThat(comparator.compare("def", "A B  C")).isLessThan(0);
    }

    public Comparator<String> caseComparator(boolean caseInsensitive, boolean spaceInsensitive, boolean reversed) {
        Comparator<String> comparator = (o1, o2) -> {
            String s1 = o1;
            String s2 = o2;
            if (spaceInsensitive) {
                s1 = s1.replaceAll("\\s", "");
                s2 = s2.replaceAll("\\s", "");
            }
            return caseInsensitive ? s1.compareToIgnoreCase(s2) : s1.compareTo(s2);
        };

        if (reversed) {
            comparator = comparator.reversed();
        }
        return comparator;
    }


    @Test
    @Ignore
    public void exercise_08() {
        // Skipped
    }

    @Test
    public void exercise_09() {
        Person person1 = new Person("name", "firstName", "street", "city");
        Person person2 = new Person("name", "firstName", "street", "otherCity");
        Person person3 = new Person("name", "firstName", "otherStreet", "city");
        Person person4 = new Person("name", "otherFirstName", "street", "city");
        Person person5 = new Person("otherName", "firstName", "street", "city");

        Comparator<Person> comparator = lexicographicComparator("name", "firstName");

        assertThat(comparator.compare(person1, person2)).isEqualTo(0);
        assertThat(comparator.compare(person1, person4)).isLessThan(0);
        assertThat(comparator.compare(person1, person5)).isLessThan(0);
    }

    public static class Person {
        private String name;
        private String firstName;
        private String street;
        private String city;

        public Person(String name, String firstName, String street, String city) {
            this.name = name;
            this.firstName = firstName;
            this.street = street;
            this.city = city;
        }
    }

    public static <T> Comparator<T> lexicographicComparator(String fieldName, String... fieldNames) {
        Comparator<T> comparator = lexicographicComparator(fieldName);
        for (String name : fieldNames) {
            comparator = comparator.thenComparing(lexicographicComparator(name));
        }
        return comparator;
    }

    public static <T> Comparator<T> lexicographicComparator(String fieldName) {
        return (o1, o2) -> {
            try {
                return getFieldValue(o1, fieldName).compareTo(getFieldValue(o2, fieldName));
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static <T> Comparable<T> getFieldValue(Object o, String fieldName) throws ReflectiveOperationException {
        Field field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (Comparable<T>) field.get(o);
    }


    @Test
    public void exercise_10() {
        UnaryOperator<Color> op = Color::brighter;
        // TODO: Discuss
//        Image finalImage = transform(mock(Image.class), op.compose(Color::grayscale));
    }

    @Test
    @Ignore
    public void exercise_11() {
// Skipped
    }

    @Test
    @Ignore
    public void exercise_12() {
// Skipped
    }

    @Test
    @Ignore
    public void exercise_13() {
// Skipped
    }

    @Test
    @Ignore
    public void exercise_14() {
// Skipped
    }

    @Test
    @Ignore
    public void exercise_15() {
// Skipped
    }

    @Test
    public void exercise_16() {
        doInOrderAsync(() -> System.out.print("First"), () -> System.out.println("Second"), Throwable::printStackTrace);
        doInOrderAsync(() -> "First", (s, t) -> {
            if (s != null) {
                System.out.println(s);
            } else {
                t.printStackTrace();
            }
        });

        // TODO: Discuss use case
    }

    public static void doInOrderAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        new Thread(() -> {
            try {
                first.run();
                second.run();
            } catch (Throwable t) {
                handler.accept(t);
            }
        }).start();
    }

    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        new Thread(() -> {
            try {
                T result = first.get();
                second.accept(result, null);
            } catch (Throwable t) {
                second.accept(null, t);
            }
        }).start();
    }

    @Test
    public void exercise_17() {
        doInParallelAsync(() -> System.out.print("First"), () -> System.out.println("Second"), Throwable::printStackTrace);
    }

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        new Thread(() -> {
            try {
                first.run();
            } catch (Throwable e) {
                handler.accept(e);
            }
        }).start();
        new Thread(() -> {
            try {
                second.run();
            } catch (Throwable e) {
                handler.accept(e);
            }
        }).start();
    }

    @Test
    public void exercise_18() {
        unchecked(() -> new String(Files.readAllBytes(Paths.get("/etc/passwd")), StandardCharsets.UTF_8));

        // TODO; Did not understand the question
    }

    public static <T> Supplier<T> unchecked(Callable<T> f) {
        return () -> {
            try {
                return f.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    public void exercise_19() {
        // TODO: Discuss
    }

    @Test
    public void exercise_20() {
        List<String> input = Arrays.asList("1", "2", "3");

        assertThat(map(input, Long::valueOf)).containsOnlyOnce(1L, 2L, 3L);

    }

    private static <T, U> List<U> map(List<T> list, Function<T, U> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }

    @Test
    public void exercise_21() throws Exception {
        Future<String> result = map(CompletableFuture.completedFuture("Test"), s -> {
            return new StringBuilder(s).reverse().toString();
        });

        assertThat(result.get()).isEqualTo("tseT");
    }

    public static <T, U> Future<U> map(Future<T> future, Function<T, U> function) {
        return new Future<U>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return function.apply(future.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return function.apply(future.get(timeout, unit));
            }
        };
    }

    @Test
    public void exercise_22() throws Exception {
        CompletableFuture<String> future = CompletableFuture.completedFuture("Test")
                .thenCompose(s -> CompletableFuture.completedFuture(new StringBuilder(s).reverse().toString()));

        assertThat(future.get()).isEqualTo("tseT");
    }

    @Test
    public void exercise_23() {
        List<Pair<String>> list = Arrays.asList(new Pair<>("a", "b"), new Pair<>("c", "d"));

        List<String> result = list.stream().map((p) -> p.obj1).collect(Collectors.toList());
        assertThat(result).containsExactly("a", "c");
    }

    public static class Pair<T> {
        public T obj1;
        public T obj2;

        public Pair(T obj1, T obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }
    }

    @Test
    public void exercise_24() {
        List<Pair<String>> list = Arrays.asList(new Pair<>("a", "b"), new Pair<>("c", "d"));

        List<String> result = list.stream().flatMap(stringPair -> Stream.of(stringPair.obj1, stringPair.obj2)).collect(Collectors.toList());

        assertThat(result).containsExactly("a", "b", "c", "d");
    }

}
