package be.cegeka.java_8_workshop.ch1.solutions;

import be.cegeka.java_8_workshop.ch1.ex06.RunnableEx;
import org.junit.Test;

@SuppressWarnings("ALL")
public class Exercise_06 {

    @Test
    public void solution() {
        new Thread(() -> {
            try {
                exceptionThrowing();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        new Thread(RunnableEx.unCheck(() -> exceptionThrowing()));
        new Thread(RunnableEx.unCheck(Exercise_06::exceptionThrowing));
    }

    public static void exceptionThrowing() throws Exception {
        throw new Exception();
    }
}
