package be.cegeka.java_8_workshop.lambdas.ch3.solutions;

import be.cegeka.java_8_workshop.lambdas.db.MusicDatabase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_07 {

    @Test
    public void solution() throws Exception {
        long count = MusicDatabase.jeffreyHammondHammond.getName().chars()
                .filter(Character::isLowerCase)
                .count();

        assertThat(count).isEqualTo(18);
    }
}
