package be.cegeka.java_8_workshop.impatient.ch2.solutions;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_08 {

    @Test
    public void solution() throws Exception {
        Stream<Integer> odds = Stream.of(1, 3, 5, 7, 9, 11, 13, 15);
        Stream<Integer> evens = Stream.of(2, 4, 6, 8, 10, 12);

        List<Integer> result = zip(odds, evens).collect(Collectors.toList());

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    }

    @Test
    public void testZip_OneinfiniteStreams() throws Exception {
        Stream<String> s1 = Stream.generate(() -> "A");
        Stream<String> s2 = Stream.of("Z", "X");

        Stream<String> zipped = zip(s1, s2);

        assertThat(zipped.toArray()).containsExactly("A", "Z", "A", "X");
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> secondIter = second.iterator();
        return StreamSupport.stream(takeUntil(first.flatMap(new Function<T, Stream<? extends T>>() {
            @Override
            public Stream<? extends T> apply(T t) {
                return Stream.of(t, secondIter.next());
            }
        }).spliterator(), new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return secondIter.hasNext();
            }
        }), false);
    }


    static <T> Spliterator<T> takeUntil(Spliterator<T> splitr, Predicate<? super T> predicate) {
        return new Spliterators.AbstractSpliterator<T>(splitr.estimateSize(), 0) {
            boolean stillGoing = true;
            @Override public boolean tryAdvance(Consumer<? super T> consumer) {
                return stillGoing && splitr.tryAdvance(elem -> {
                    consumer.accept(elem);
                    if (!predicate.test(elem)) {
                        stillGoing = false;
                    }
                });
            }
        };
    }
}
