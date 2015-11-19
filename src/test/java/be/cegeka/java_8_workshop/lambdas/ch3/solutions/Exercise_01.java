package be.cegeka.java_8_workshop.lambdas.ch3.solutions;

import be.cegeka.java_8_workshop.lambdas.db.MusicDatabase;
import be.cegeka.java_8_workshop.lambdas.domain.Album;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_01 {

    @Test
    public void a() throws Exception {
        assertThat(addUp(Stream.of(1, 2, 3, 4))).isEqualTo(10);
    }

    private static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, num) -> acc + num);
    }

    @Test
    public void b() throws Exception {
        List<String> namesAndOrigins = MusicDatabase.getArtists().stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(toList());

        assertThat(namesAndOrigins)
                .containsSequence("James Hetfield", "United States")
                .containsSequence("Adele", "England")
                .containsSequence("Lars Ulrich", "Denmark");
    }

    @Test
    public void c() throws Exception {
        List<Album> albumsWithFewTracks = MusicDatabase.getAlbums(). stream()
                .filter(album -> album.getTrackList().size() <= 3)
                .collect(toList());

        assertThat(albumsWithFewTracks)
                .containsExactly(MusicDatabase.thickAsABrick);
    }

}
