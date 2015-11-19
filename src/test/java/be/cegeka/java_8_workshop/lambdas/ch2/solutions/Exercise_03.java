package be.cegeka.java_8_workshop.lambdas.ch2.solutions;

import org.mockito.Mockito;

import javax.swing.*;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class Exercise_03 {

    public void a() throws Exception {
        Runnable helloWorld = () -> System.out.println("hello world");
    }

    public void b() throws Exception {
        JButton button = new JButton();

        button.addActionListener(event ->
                System.out.println(event.getActionCommand()));
    }

    public void c() throws Exception {
        AnInterface anInterface = Mockito.mock(AnInterface.class);

        // WRONG:
        //   Compile error 'Ambiguous Method Call'
        // anInterface.check(x -> x > 5);
    }

    private interface AnInterface {
        boolean check(Predicate<Integer> predicate);

        boolean check(IntPred predicate);
    }

    @FunctionalInterface
    private interface IntPred {
        boolean test(Integer value);
    }
}
