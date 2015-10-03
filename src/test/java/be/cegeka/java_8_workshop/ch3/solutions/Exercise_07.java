package be.cegeka.java_8_workshop.ch3.solutions;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_07 {

    @Test
    public void solution() {
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
}
