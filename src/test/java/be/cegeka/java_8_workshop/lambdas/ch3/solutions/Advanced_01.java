package be.cegeka.java_8_workshop.lambdas.ch3.solutions;

import be.cegeka.java_8_workshop.lambdas.db.MusicDatabase;
import be.cegeka.java_8_workshop.lambdas.domain.Artist;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Advanced_01 {

    @Test
    public void testUsingMap() {
        List<String> result = MusicDatabase.getArtists().stream()
                .map(Artist::getNationality)
                .distinct()
                .collect(Collectors.toList());

        assertThat(result).containsOnly("Denmark", "United States", "England", "Scotland");
    }

    @Test
    public void testSolution() {
        List<String> result =
                map(MusicDatabase.getArtists().stream(), Artist::getNationality)
                        .collect(Collectors.toList());

        assertThat(result).containsOnly("Denmark", "United States", "England", "Scotland");
    }

    public static <I, O> Stream<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream
                .reduce(new ArrayList<>(), (acc, x) -> {
                    List<O> newAcc = new ArrayList<>(acc);
                    newAcc.add(mapper.apply(x));
                    return newAcc;
                }, (List<O> left, List<O> right) -> {
                    List<O> newLeft = new ArrayList<>(left);
                    newLeft.addAll(right);
                    return newLeft;
                }).stream();
    }
}
