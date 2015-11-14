package be.cegeka.java_8_workshop.impatient.ch1.ex12;

public interface Interface {
    String abstract_I_abstract_S();

    String abstract_I_default_S();

    String abstract_I_static_S();

    default String default_I_abstract_S() {
        return "default_I_abstract_S -> Interface";
    }

    default String default_I_static_S() {
        return "default_I_static_S -> Interface";
    }

    static String static_I_static_S() {
        return "static_I_static_S -> Interface";
    }
}
