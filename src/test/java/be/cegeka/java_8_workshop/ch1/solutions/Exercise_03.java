package be.cegeka.java_8_workshop.ch1.solutions;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class Exercise_03 {
    private static final File TEST_DIR = new File("./src/test/resources/");

    @Test
    @Parameters({"exe,0", "png,1"})
    public void solution(String extension, int count) {
        File[] files = TEST_DIR.listFiles(((dir, name) -> name.endsWith("." + extension)));
        assertThat(files).hasSize(count);
    }
}
