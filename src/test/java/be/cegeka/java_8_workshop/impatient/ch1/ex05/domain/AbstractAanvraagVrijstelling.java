package be.cegeka.java_8_workshop.impatient.ch1.ex05.domain;

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
