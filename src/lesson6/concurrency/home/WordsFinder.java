package lesson6.concurrency.home;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

public class WordsFinder {

    private static List<Callable<Set<String>>> tasks;

    public static void getOccurrences(String[] sources, String[] words, String res) throws InterruptedException {

        ExecutorService executor;
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