package be.cegeka.java_8_workshop.lambdas.ch3.solutions;

import be.cegeka.java_8_workshop.lambdas.db.MusicDatabase;
import be.cegeka.java_8_workshop.lambdas.domain.Artist;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_08 {

    @Test
    public void solution() throws Exception {
        Optional<Artist> longest = MusicDatabase.getArtists().stream()
                .max(Comparator.comparing(
                        Artist::getName,
                        (o1, o2) -> countLowercaseLetters(o1).compareTo(countLowercaseLetters(o2))
                ));

        assertThat(longest).contains(MusicDatabase.jeffreyHammondHammond);
    }

    public static Integer countLowercaseLetters(String string) {
        return (int) string.chars()
                .filter(Character::isLowerCase)
                .count();
    }
}
