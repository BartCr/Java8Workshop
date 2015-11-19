package be.cegeka.java_8_workshop.lambdas.db;

import be.cegeka.java_8_workshop.lambdas.domain.Album;
import be.cegeka.java_8_workshop.lambdas.domain.Artist;
import be.cegeka.java_8_workshop.lambdas.domain.Track;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MusicDatabase {


    public static final Artist jamesHetfield = new Artist("James Hetfield", "United States");
    public static final Artist kirkHammett = new Artist("Kirk Hammett", "United States");
    public static final Artist jasonNewsted = new Artist("Jason Newsted", "United States");
    public static final Artist larsUlrich = new Artist("Lars Ulrich", "Denmark");

    public static final Artist metallica = new Artist("Metallica", Arrays.asList(jamesHetfield, kirkHammett, jasonNewsted, larsUlrich), "United States");

    public static final Album andJusticeForAll = new Album(
            "...And Justice for All",
            Arrays.asList(
                    new Track("Blackened", 402),
                    new Track("And Justice for All", 584),
                    new Track("Eye Of The Beholder", 385),
                    new Track("One", 445),
                    new Track("The Shortest Straw", 395),
                    new Track("Harvester of Sorrow", 344),
                    new Track("The Frayed Ends of Sanity", 463),
                    new Track("To Live Is to Die", 588),
                    new Track("Dyers Eve", 313)
            ),
            metallica
    );

    public static final Artist adele = new Artist("Adele", "England");

    public static final Album _21 = new Album(
            "21",
            Arrays.asList(
                    new Track("Rolling in the Deep", 228),
                    new Track("Rumour Has It", 223),
                    new Track("Turning Tables", 250),
                    new Track("Don't You Remember", 243),
                    new Track("Set Fire to the Rain", 242),
                    new Track("He Won't Go", 278),
                    new Track("Take It All", 228),
                    new Track("I'll Be Waiting", 241),
                    new Track("One and Only", 348),
                    new Track("Lovesong", 316),
                    new Track("Someone Like You", 285),
                    new Track("I Found a Boy", 217),
                    new Track("Don't You Remember", 258)
            ),
            adele);

    public static final Artist ianAnderson = new Artist("Ian Anderson", "Scotland");
    public static final Artist martinBarre = new Artist("Martin Barre", "England");
    public static final Artist barriemoreBarlow = new Artist("Barriemore Barlow", "England");
    public static final Artist jeffreyHammondHammond = new Artist("Jeffrey Hammond-Hammond", "England");
    public static final Artist johnEvan = new Artist("John Evan", "England");

    public static final Artist jethroTull = new Artist("Jethro Tull", Arrays.asList(ianAnderson, martinBarre, barriemoreBarlow, jeffreyHammondHammond, johnEvan), "England");

    public static final Album thickAsABrick = new Album(
            "Tick as a Brick",
            Arrays.asList(
                    new Track("Thick as a Brick (parth I)", 1360),
                    new Track("Thick as a Brick (parth II)", 1270)
            ),
            jethroTull
    );

    private static Set<Artist> artists = new HashSet<>();
    private static Set<Album> albums = new HashSet<>();

    public static Set<Artist> getArtists() {
        return artists;
    }

    public static Set<Album> getAlbums() {
        return albums;
    }

    static {


        artists.addAll(Arrays.asList(
                jamesHetfield, kirkHammett, jasonNewsted, larsUlrich,
                metallica,
                adele,
                ianAnderson, martinBarre, barriemoreBarlow, jeffreyHammondHammond, johnEvan,
                jethroTull)
        );

        albums.addAll(Arrays.asList(
                andJusticeForAll,
                _21,
                thickAsABrick
        ));

    }
}
