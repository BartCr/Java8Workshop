package be.cegeka.java_8_workshop.impatient.ch1.solutions;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
public class Exercise_02 {

    private static final File TEST_DIR = new File("./src/test/resources/");

    @Test
    public void solution() {
        File[] directoriesWithLambda = TEST_DIR.listFiles(f -> f.isDirectory());
        // of
        File[] directoriesWithMethodRef = TEST_DIR.listFiles(File::isDirectory);

        assertThat(directoriesWithLambda).containsExactly(directoriesWithMethodRef);
        assertThat(directoriesWithLambda).hasSize(2);
    }
}
