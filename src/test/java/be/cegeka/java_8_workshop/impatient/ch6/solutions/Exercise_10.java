package be.cegeka.java_8_workshop.impatient.ch6.solutions;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise_10 {

    private static final Pattern URL_PATTERN = Pattern.compile("(?i)href=\"(http[s]?://.*?)\"");

    @Test
    public void solution() throws Exception {
        CompletableFuture.supplyAsync(() -> readPage("http://www.horstmann.com/"))
                .thenApply(this::getLinks)
                .handle((l, e) -> {
                    if (e != null) {
                        System.out.println(e.getMessage());
                        return new ArrayList<>();
                    } else {
                        return l;
                    }
                })
                .thenAccept(l -> l.stream().forEach(System.out::println));
        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

    public String readPage(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            StringBuilder content = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            return content.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getLinks(String content) {
        List<String> links = new ArrayList<>();
        Matcher m = URL_PATTERN.matcher(content);
        while (m.find()) {
            links.add(m.group(1));
        }
        return links;
    }
}
