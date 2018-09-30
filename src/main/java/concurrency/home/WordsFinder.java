package concurrency.home;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordsFinder {

    private static List<Callable<Set<String>>> tasks;
    static ExecutorService executor;

    public static void getOccurrences(String[] sources, String[] words, String res) throws InterruptedException {

        tasks = new ArrayList<>();

        executor = ((sources.length) > 10 ?
                Executors.newFixedThreadPool(4) :
                Executors.newFixedThreadPool(sources.length));

        Arrays.stream(sources).forEach(x -> tasks.add(new SingleFileTextAnalyzer(x, words)));
        executor.invokeAll(tasks).forEach(f -> {
            try {
                synchronized (res) {
                    Files.write(Paths.get(res), f.get());
                }

            } catch (InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }
}