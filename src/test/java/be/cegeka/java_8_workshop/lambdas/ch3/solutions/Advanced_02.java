package be.cegeka.java_8_workshop.lambdas.ch3.solutions;

import be.cegeka.java_8_workshop.lambdas.db.MusicDatabase;
import be.cegeka.java_8_workshop.lambdas.domain.Artist;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Advanced_02 {

    @Test
    public void testUsingFilter() {
        List<Artist> result = MusicDatabase.getArtists().stream()
                .filter(artist -> artist.getNationality().equals("Scotland"))
                .collect(Collectors.toList());

        assertThat(result).containsOnly(MusicDatabase.ianAnderson);
    }

    @Test
    public void testSolution() {
        List<Artist> result =
                filter(MusicDatabase.getArtists().stream(), artist -> artist.getNationality().equals("Scotland"))
                        .collect(Collectors.toList());

        assertThat(result).containsOnly(MusicDatabase.ianAnderson);
    }

    public static <I> Stream<I> filter(Stream<I> stream, Predicate<I> predicate) {
        return stream
                .reduce(new ArrayList<>(), (acc, x) -> {
                    if (predicate.test(x)) {
                        List<I> newAcc = new ArrayList<>(acc);
                        newAcc.add(x);
                        return newAcc;
                    } else {
                        return acc;
                    }
                }, (List<I> left, List<I> right) -> {
                    List<I> newLeft = new ArrayList<>(left);
                    newLeft.addAll(right);
                    return newLeft;
                }).stream();
    }
}
