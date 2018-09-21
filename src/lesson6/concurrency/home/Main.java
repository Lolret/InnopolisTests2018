package lesson6.concurrency.home;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter anykey to start:");
        scanner.nextLine();
        long time = System.currentTimeMillis();

        WordsFinder.getOccurrences(new String[]{"d://2.txt"}
                                 , new String[]{"Pu"}
                                 , "d://res.txt");
        System.out.println("It takes " + (System.currentTimeMillis() - time) / 1_000 + " sec");
    }
}