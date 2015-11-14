package be.cegeka.java_8_workshop.impatient.ch1.ex11;

public interface Interface_J {

    String abstract_I_abstract_J();

    default String abstract_I_default_J() {
        return "abstract_I_default_J -> J";
    }

    static String abstract_I_static_J() {
        return "abstract_I_static_J -> J";
    }

    default String default_I_default_J() {
        return "default_I_default_J -> J";
    }

    static String default_I_static_J() {
        return "default_I_static_J -> J";
    }

    static String static_I_static_J() {
        return "static_I_static_J -> J";
    }

}
