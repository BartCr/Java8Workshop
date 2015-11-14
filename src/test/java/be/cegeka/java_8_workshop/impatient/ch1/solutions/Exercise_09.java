package be.cegeka.java_8_workshop.impatient.ch1.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

@SuppressWarnings("ALL")
public class Exercise_09 {
    @Test
    public void solution() {
        Collection2<String> c = new ArrayList2<>("a", "b", "cd", "e", "fg");
        c.forEachIf(System.out::println, (s) -> s.length() == 2);
        c.forEachIfStreams(System.out::println, (s) -> s.length() == 1);
    }


    public interface Collection2<E> extends Collection<E> {
        default void forEachIf(Consumer<E> action, Predicate<E> filter) {
            for (E e : this) {
                if (filter.test(e)) {
                    action.accept(e);
                }
            }
        }

        default void forEachIfStreams(Consumer<E> action, Predicate<E> filter) {
            this.stream().filter(filter::test).forEach(action::accept);
        }
    }

    private static class ArrayList2<E> extends ArrayList<E> implements Collection2<E> {

        @SafeVarargs
        public ArrayList2(E... elements) {
            super(Arrays.asList(elements));
        }
    }
}
