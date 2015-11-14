package be.cegeka.java_8_workshop.impatient.ch1.ex12;

public abstract class SuperClass {
    public abstract String abstract_I_abstract_S();

    public String abstract_I_default_S() {
        return "abstract_I_default_S -> SuperClass";
    }

    public static String abstract_I_static_S() {
        return "abstract_I_static_S -> SuperClass";
    }

    public abstract String default_I_abstract_S();

    public static String default_I_static_S() {
        return "default_I_static_S -> SuperClass";
    }

    public static String static_I_static_S() {
        return "static_I_static_J -> SuperClass";
    }
}
