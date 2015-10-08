package be.cegeka.java_8_workshop.ch2.solutions;

import be.cegeka.java_8_workshop.ch2.TextReader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise_01 {
    @Test
    public void solution() throws Exception {
        List<String> words = TextReader.getWordsFromFile("/alice.txt");

        int nThreads = Runtime.getRuntime().availableProcessors();

        int segmentSize = words.size() / (nThreads - 1);

        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        List<Future<Integer>> results = new ArrayList<>();

        for (int i = 0; i < words.size(); i += segmentSize) {
            results.add(executorService.submit(new WordCounter(words, i, Math.min(words.size(), i + segmentSize))));
        }


        int count = 0;
        for (Future<Integer> result : results) {
            count += result.get();
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        assertThat(count).isEqualTo(33);
    }

    private static class WordCounter implements Callable<Integer> {

        private List<String> words;
        private int startIndex;
        private int endIndex;

        public WordCounter(List<String> words, int startIndex, int endIndex) {
            this.words = words;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread() + " - " + startIndex + " -> " + endIndex);

            int count = 0;
            for (int i = startIndex; i < endIndex; i++) {
                if (words.get(i).length() > 12) count++;
            }
            return count;
        }
    }
}
