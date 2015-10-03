package be.cegeka.java_8_workshop.ch3.solutions;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
public class Exercise_09 {

    @Test
    public void solution() {
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

}
