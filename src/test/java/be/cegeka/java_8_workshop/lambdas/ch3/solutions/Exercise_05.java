package be.cegeka.java_8_workshop.lambdas.ch3.solutions;

import be.cegeka.java_8_workshop.lambdas.db.MusicDatabase;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class Exercise_05 {

    @Test
    public void solution() throws Exception {

        // ??
//        List<String> origins = MusicDatabase.andJusticeForAll.getMusicians()
//                .forEach(x -> x + 1);


        AtomicInteger count = new AtomicInteger(0);
        MusicDatabase.andJusticeForAll.getMusicians()
                .forEach(musician -> count.incrementAndGet());
    }
}
