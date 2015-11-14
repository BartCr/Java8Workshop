package be.cegeka.java_8_workshop.impatient.ch1.ex06;

@FunctionalInterface
public interface RunnableEx {
    void run() throws Exception;

    static Runnable unCheck(RunnableEx runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
