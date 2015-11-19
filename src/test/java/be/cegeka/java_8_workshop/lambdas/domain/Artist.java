package be.cegeka.java_8_workshop.lambdas.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class Artist {

    private String name;

    private List<Artist> members;

    private String nationality;


    public Artist(String name, String nationality) {
        this(name, emptyList(), nationality);
    }

    public Artist(String name, List<Artist> members, String nationality) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(members);
        Objects.requireNonNull(nationality);

        this.name = name;
        this.members = new ArrayList<>(members);
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public Stream<Artist> getMembers() {
        return members.stream();
    }

    public String getNationality() {
        return nationality;
    }
}
