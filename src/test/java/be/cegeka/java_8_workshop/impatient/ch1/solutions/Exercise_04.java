package be.cegeka.java_8_workshop.impatient.ch1.solutions;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
public class Exercise_04 {

    private static final File TEST_DIR = new File("./src/test/resources/");
    private static final File SUB_DIR_ONE = new File("./src/test/resources/subDirOne/");
    private static final File SUB_DIR_TWO = new File("./src/test/resources/subDirTwo/");
    private static final File PIXEL_PNG = new File("./src/test/resources/pixel.png");
    private static final File TEST_TXT = new File("./src/test/resources/test.txt");

    @Test
    public void solution() {
        File[] files = TEST_DIR.listFiles();
        Arrays.sort(files, Comparator.comparing(File::isDirectory).reversed().thenComparing(File::getName));

        assertThat(files).containsExactly(SUB_DIR_ONE, SUB_DIR_TWO, PIXEL_PNG, TEST_TXT);
    }
}
