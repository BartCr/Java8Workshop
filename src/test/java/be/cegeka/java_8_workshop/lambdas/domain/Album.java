package be.cegeka.java_8_workshop.lambdas.domain;


import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

public class Album {

    private String name;
    private List<Track> tracks;
    private List<Artist> musicians;

    public Album(String name, List<Track> tracks, Artist musicians) {
        this(name, tracks, singletonList(musicians));
    }

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(tracks);
        Objects.requireNonNull(musicians);

        this.name = name;
        this.tracks = tracks;
        this.musicians = musicians;
    }

    public String getName() {
        return name;
    }

    public List<Track> getTrackList() {
        return unmodifiableList(tracks);
    }

    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }

    public List<Artist> getMusicianList() {
        return unmodifiableList(musicians);
    }
}
