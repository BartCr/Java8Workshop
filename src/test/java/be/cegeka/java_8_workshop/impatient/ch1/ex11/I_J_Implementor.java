package be.cegeka.java_8_workshop.impatient.ch1.ex11;

@SuppressWarnings("unused")
public class I_J_Implementor implements Interface_I, Interface_J {

    @Override
    public String abstract_I_static_J() {
        return "abstract_I_static_J -> Implementor";
    }

    @Override
    public String default_I_static_J() {
        return "default_I_static_J -> Implementor";
    }

//    @Override -> No Override
    public static String static_I_static_J() {
        return "static_I_static_J -> Implementor";
    }

    @Override
    public String abstract_I_abstract_J() {
        return "abstract_I_abstract_J -> Implementor";
    }

    @Override
    public String abstract_I_default_J() {
        return "abstract_I_default_J -> Implementor";
    }

    @Override
    public String default_I_default_J() {
        return "default_I_default_J -> Implementor";
    }
}
