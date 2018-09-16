package lesson6.concurrency.home;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WordsFinder {

    public void getOccurrences(String[] sources, String[] words, String res)
            throws InterruptedException, ExecutionException, IOException {

        ExecutorService executor;
        if ((sources.length) > 10) executor = Executors.newFixedThreadPool(4);
        else executor = Executors.newFixedThreadPool(sources.length);

        List<Callable<String>> tasks = new ArrayList<>();

        Arrays.stream(sources).forEach(x -> tasks.add(new SingleFileTextAnalyzer(x, words)));

        for (Future<String> f : executor.invokeAll(tasks)) {
            Files.write(Paths.get(res), f.get().getBytes());
        }
        executor.shutdown();
    }
}