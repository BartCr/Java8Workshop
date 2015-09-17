package be.cegeka.java_8_workshop.ch1;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

//@SuppressWarnings("Convert2MethodRef")
@RunWith(JUnitParamsRunner.class)
public class Solutions {

    private static final File TEST_DIR = new File("./src/test/resources/");
    private static final File SUB_DIR_ONE = new File("./src/test/resources/subDirOne/");
    private static final File SUB_DIR_TWO = new File("./src/test/resources/subDirTwo/");
    private static final File PIXEL_PNG = new File("./src/test/resources/pixel.png");
    private static final File TEST_TXT = new File("./src/test/resources/test.txt");

    @Test
    public void exerciseOne() {
        Thread mainThread = Thread.currentThread();

        String[] array = {"one", "two"};

        Arrays.sort(array, (o1, o2) -> {
            assertThat(mainThread).isSameAs(Thread.currentThread());
            return o1.compareTo(o2);
        });
    }

    @Test
    public void exerciseTwo() {
        File[] directoriesWithLambda = TEST_DIR.listFiles(f -> f.isDirectory());
        // of
        File[] directoriesWithMethodRef = TEST_DIR.listFiles(File::isDirectory);

        assertThat(directoriesWithLambda).containsExactly(directoriesWithMethodRef);
        assertThat(directoriesWithLambda).hasSize(2);
    }

    @Test
    @Parameters({"exe,0", "png,1"})
    public void exerciseThree(String extension, int count) {
        File[] files = TEST_DIR.listFiles(((dir, name) -> name.endsWith("." + extension)));
        assertThat(files).hasSize(count);
    }

    @Test
    public void exerciseFour() {
        File[] files = TEST_DIR.listFiles();
        Arrays.sort(files, Comparator.comparing(File::isDirectory).reversed().thenComparing(File::getName));

        assertThat(files).containsExactly(SUB_DIR_ONE, SUB_DIR_TWO, PIXEL_PNG, TEST_TXT);
    }

}
