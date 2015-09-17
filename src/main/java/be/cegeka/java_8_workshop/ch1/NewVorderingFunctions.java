package be.cegeka.java_8_workshop.ch1;

import be.cegeka.java_8_workshop.ch1.domain.*;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

@SuppressWarnings("ALL")
public class NewVorderingFunctions {

    private NewVorderingFunctions() {}

    public static final Function<Vordering, Date> getUitersteBetalingsdatum = Vordering::getUitersteBetalingsDatum;

    public static final Function<Vordering, Date> getVorderingsDatum = Vordering::getVorderingsDatum;

    public static final Function<Vordering, VentourisNumber> getBedrag = Vordering::getBedrag;

    public static final Function<Vordering, KwartaalPeriode> getPeriode = Vordering::getPeriode;

    public static final Function<Vordering, VorderingType> getVorderingType = Vordering::getVorderingType;

    public static final Function<VerhogingArtikel44, VerhogingType> getVerhogingType = VerhogingArtikel44::getType;

    public static final Function<VerhogingArtikel44, VerhogingSoort> getVerhogingSoort = VerhogingArtikel44::getSoort;

    public static final Function<BijdrageSS, BijdrageSSType> getBijdrageType = BijdrageSS::getTypeBijdrage;

    public static final Predicate<BijdrageSS> isBijdrageVoorIngevoerdeVerhogingen = BijdrageSS::isBijdrageVoorIngevoerdeVerhogingen;

    public static final Predicate<Vordering> isBedragPositief = input -> input.getBedrag().isPositief();

    public static Predicate<BijdrageSS> isBedragPositief(final BijdrageStadium tijdstip) {
        return input -> tijdstip.voor(input).getTotaal().isPositief();
    }

    public static final Predicate<VerhogingArtikel44> hangtNietAanVerwijderdeBijdrage = input -> !input.getBijdrage().isVerwijderd();

    public static final Predicate<Vordering> isInLopendeAanvraagVrijstellingWaarvanAanvragerGelijkIsAan(final Schuldenaar schuldenaar) {
        return vordering -> {
            for (AbstractAanvraagVrijstelling aanvraagVrijstelling : vordering.getDossier().getAanvragenVrijstelling()) {
                if (aanvraagVrijstelling.isLopendeAanvraag() &&
                        aanvraagVrijstelling.getAanvrager().equals(schuldenaar) &&
                        aanvraagVrijstelling.getSortedKwartaalPeriodes().contains(vordering.getPeriode())) {
                    return true;
                }
            }
            return false;
        };
    };

    public static Predicate<Vordering> isInProcedureOpSchuldniveau(final SchuldNiveau niveau) {
        return niveau::isVorderingInGerechtelijkeProcedure;
    }

    public static final Predicate<Vordering> isGeraamdeReserve = Vordering::isGeraamdeReserve;

    public static final Predicate<Vordering> isInAfbetalingsplan = Vordering::isInAfbetalingsplan;

    public static Predicate<Vordering> isNaOfOpDatum(final Date datum) {
        return input -> DateUtil.afterOrEqual(input.getVorderingsDatum(), datum);
    }

    public static Predicate<Vordering> isInPeriode(final KwartaalPeriode periodeVan, final KwartaalPeriode periodeTot) {
        return vordering -> vordering.getPeriode().isInRange(periodeVan, periodeTot);
    }

    public static Predicate<Vordering> isVanVorderingsType(final VorderingType... vorderingTypes) {
        return input -> asList(vorderingTypes).contains(input.getVorderingType());
    }

    public static Predicate<Vordering> isKost =  Vordering::isKost;

    public static Predicate<Vordering> vervallenSchuldVoor(final Schuldenaar schuldenaar) {
        return vordering -> vordering.isVervallenSchuld(schuldenaar);
    }

    public static Predicate<Vordering> isVerantwoordelijkheidVan(final Schuldenaar schuldenaar) {
        return schuldenaar::isVerantwoordelijkVoor;
    }

    public static Predicate<Vordering> isAssignableFrom(final Class<? extends Vordering>... classes) {
        return input -> {
            for (Class<? extends Vordering> aClass : classes) {
                if (aClass.isAssignableFrom(input.getClass())) {
                    return true;
                }
            }
            return false;
        };
    }
}
