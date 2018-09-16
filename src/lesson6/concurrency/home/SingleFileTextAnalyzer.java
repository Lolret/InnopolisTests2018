package lesson6.concurrency.home;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class SingleFileTextAnalyzer implements Callable<String> {

    private String source;
    private String[] words;
    private String res;

    public SingleFileTextAnalyzer(String source, String[] words) {
        this.source = source;
        this.words = words;

    }

    @Override
    public String call() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(source))) {
            Pattern pattern = Pattern.compile("[?.!]");
            scanner.useDelimiter(pattern);
            while (scanner.hasNext()) {
                String sentence = scanner.next();
//                scanner.findInLine(pattern);
                for (String word : words) {
                    if (checkWordInSentence(sentence, word)) {
                        sb.append(sentence.trim() + ".\r\n");
                    }
                }
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static boolean checkWordInSentence(String sentence, String word) {
        sentence = sentence.toLowerCase();
        return sentence.contains(word);
    }
}