package be.cegeka.java_8_workshop.lambdas.ch3.solutions;

import be.cegeka.java_8_workshop.lambdas.db.MusicDatabase;
import be.cegeka.java_8_workshop.lambdas.domain.Artist;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_02 {

    @Test
    public void testRefactoring() throws Exception {
        assertThat(oldCode()).isEqualTo(9);

        assertThat(newCode()).isEqualTo(9);
    }

    public int newCode() throws Exception {
        Set<Artist> artists = MusicDatabase.getArtists();
        return artists.stream()
                .map(artist -> artist.getMembers().count())
                .collect(Collectors.summingInt(Long::intValue));
    }

    public int oldCode() throws Exception {
        int totalMembers = 0;
        for (Artist artist : MusicDatabase.getArtists()) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }
        return totalMembers;
    }
}
