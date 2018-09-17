package lesson6.concurrency.home;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        WordsFinder wf = new WordsFinder();
        try {

            wf.getOccurrences(new String[]{"d://raveFile1.txt"}, new String[]{"ghrl"}, "d://res.txt");

        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("It takes " + (System.currentTimeMillis() - time) / 1_000 + " sec");
    }
}