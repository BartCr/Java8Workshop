package be.cegeka.java_8_workshop.ch1.ex11;

public interface Interface_I {
    String abstract_I_abstract_J();

    String abstract_I_default_J();

    String abstract_I_static_J();

    default String default_I_default_J() {
        return "default_I_default_J -> I";
    }

    default String default_I_static_J() {
        return "default_I_static_J -> I";
    }

    static String static_I_static_J() {
        return "static_I_static_J -> I";
    }
}
