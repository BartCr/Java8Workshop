package be.cegeka.java_8_workshop.ch1.ex6;

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
