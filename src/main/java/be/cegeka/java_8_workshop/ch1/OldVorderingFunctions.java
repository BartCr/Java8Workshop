package be.cegeka.java_8_workshop.ch1;

import be.cegeka.java_8_workshop.ch1.domain.*;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

@SuppressWarnings("ALL")
public class OldVorderingFunctions {

    private OldVorderingFunctions() {}

    public static final Function<Vordering, Date> getUitersteBetalingsdatum = new Function<Vordering, Date>() {
        @Override
        public Date apply(Vordering input) {
            return input.getUitersteBetalingsDatum();
        }
    };

    public static final Function<Vordering, Date> getVorderingsDatum = new Function<Vordering, Date>() {
        @Override
        public Date apply(Vordering input) {
            return input.getVorderingsDatum();
        }
    };

    public static final Function<Vordering, VentourisNumber> getBedrag = new Function<Vordering, VentourisNumber>() {
        @Override
        public VentourisNumber apply(Vordering input) {
            return input.getBedrag();
        }
    };

    public static final Function<Vordering, KwartaalPeriode> getPeriode = new Function<Vordering, KwartaalPeriode>() {
        @Override
        public KwartaalPeriode apply(Vordering input) {
            return input.getPeriode();
        }
    };

    public static final Function<Vordering, VorderingType> getVorderingType = new Function<Vordering, VorderingType>() {
        @Override
        public VorderingType apply(Vordering input) {
            return input.getVorderingType();
        }
    };

    public static final Function<VerhogingArtikel44, VerhogingType> getVerhogingType = new Function<VerhogingArtikel44, VerhogingType>() {
        @Override
        public VerhogingType apply(VerhogingArtikel44 input) {
            return input.getType();
        }
    };

    public static final Function<VerhogingArtikel44, VerhogingSoort> getVerhogingSoort = new Function<VerhogingArtikel44, VerhogingSoort>() {
        @Override
        public VerhogingSoort apply(VerhogingArtikel44 input) {
            return input.getSoort();
        }
    };

    public static final Function<BijdrageSS, BijdrageSSType> getBijdrageType = new Function<BijdrageSS, BijdrageSSType>() {
        @Override
        public BijdrageSSType apply(BijdrageSS input) {
            return input.getTypeBijdrage();
        }
    };

    public static final Predicate<BijdrageSS> isBijdrageVoorIngevoerdeVerhogingen = new Predicate<BijdrageSS>() {
        @Override
        public boolean test(BijdrageSS input) {
            return input.isBijdrageVoorIngevoerdeVerhogingen();
        }
    };

    public static final Predicate<Vordering> isBedragPositief = new Predicate<Vordering>() {
        @Override
        public boolean test(Vordering input) {
            return input.getBedrag().isPositief();
        }
    };

    public static Predicate<BijdrageSS> isBedragPositief(final BijdrageStadium tijdstip) {
        return new Predicate<BijdrageSS>() {
            @Override
            public boolean test(BijdrageSS input) {
                return tijdstip.voor(input).getTotaal().isPositief();
            }
        };
    }

    public static final Predicate<VerhogingArtikel44> hangtNietAanVerwijderdeBijdrage = new Predicate<VerhogingArtikel44>() {
        @Override
        public boolean test(VerhogingArtikel44 input) {
            return !input.getBijdrage().isVerwijderd();
        }
    };

    public static final Predicate<Vordering> isInLopendeAanvraagVrijstellingWaarvanAanvragerGelijkIsAan(final Schuldenaar schuldenaar) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering vordering) {
                for (AbstractAanvraagVrijstelling aanvraagVrijstelling : vordering.getDossier().getAanvragenVrijstelling()) {
                    if (aanvraagVrijstelling.isLopendeAanvraag() &&
                            aanvraagVrijstelling.getAanvrager().equals(schuldenaar) &&
                            aanvraagVrijstelling.getSortedKwartaalPeriodes().contains(vordering.getPeriode())) {
                        return true;
                    }
                }
                return false;
            }
        };
    };

    public static Predicate<Vordering> isInProcedureOpSchuldniveau(final SchuldNiveau niveau) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering vordering) {
                return niveau.isVorderingInGerechtelijkeProcedure(vordering);
            }

        };
    }

    public static final Predicate<Vordering> isGeraamdeReserve = new Predicate<Vordering>() {
        @Override
        public boolean test(Vordering input) {
            return input.isGeraamdeReserve();
        }
    };

    public static final Predicate<Vordering> isInAfbetalingsplan = new Predicate<Vordering>() {
        @Override
        public boolean test(Vordering input) {
            return input.isInAfbetalingsplan();
        }
    };

    public static Predicate<Vordering> isNaOfOpDatum(final Date datum) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering input) {
                return DateUtil.afterOrEqual(input.getVorderingsDatum(), datum);
            }
        };
    }

    public static Predicate<Vordering> isInPeriode(final KwartaalPeriode periodeVan, final KwartaalPeriode periodeTot) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering vordering) {
                return vordering.getPeriode().isInRange(periodeVan, periodeTot);
            }
        };
    }

    public static Predicate<Vordering> isVanVorderingsType(final VorderingType... vorderingTypes) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering input) {
                return asList(vorderingTypes).contains(input.getVorderingType());
            }
        };
    }

    public static Predicate<Vordering> isKost() {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering vordering) {
                return vordering.isKost();
            }
        };
    }

    public static Predicate<Vordering> vervallenSchuldVoor(final Schuldenaar schuldenaar) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering vordering) {
                return vordering.isVervallenSchuld(schuldenaar);
            }
        };
    }

    public static Predicate<Vordering> isVerantwoordelijkheidVan(final Schuldenaar schuldenaar) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering vordering) {
                return schuldenaar.isVerantwoordelijkVoor(vordering);
            }
        };
    }

    public static Predicate<Vordering> isAssignableFrom(final Class<? extends Vordering>... classes) {
        return new Predicate<Vordering>() {
            @Override
            public boolean test(Vordering input) {
                for (Class<? extends Vordering> aClass : classes) {
                    if (aClass.isAssignableFrom(input.getClass())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
