package be.cegeka.java_8_workshop.ch2;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class TextReader {
    public static List<String> getWordsFromFile(String filename) throws IOException, URISyntaxException {
        String contents = new String(Files.readAllBytes(new File(TextReader.class.getResource(filename).toURI()).toPath()), StandardCharsets.UTF_8);
        return Arrays.asList(contents.split("[\\P{L}]+"));
    }
}
