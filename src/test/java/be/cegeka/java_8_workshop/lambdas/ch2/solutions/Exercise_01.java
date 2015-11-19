package be.cegeka.java_8_workshop.lambdas.ch2.solutions;

import java.util.function.Function;

@SuppressWarnings("unused")
public class Exercise_01 {

    public void a() throws Exception {
        // See Function.png
    }


    public void b() throws Exception {
        // Unary operator: eg. NOT
    }

    public void c() throws Exception {
        Function<Long, Long> funcA = x -> x + 1;

        // WRONG:
        //   Function only expect a single argument
        // Function<Long, Long> funcB = (x, y) -> x + 1;

        // WRONG:
        //   Boolean expression can not be converted to Long
//        Function<Long, Long> funcC = x -> x == 1;

    }
}
