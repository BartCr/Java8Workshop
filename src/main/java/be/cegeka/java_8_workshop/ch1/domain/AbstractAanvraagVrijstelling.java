package be.cegeka.java_8_workshop.ch1.domain;

import java.util.Collection;

public class AbstractAanvraagVrijstelling {
    public boolean isLopendeAanvraag() {
        return false;
    }

    public Schuldenaar getAanvrager() {
        return null;
    }

    public Collection<KwartaalPeriode> getSortedKwartaalPeriodes() {
        return null;
    }
}
