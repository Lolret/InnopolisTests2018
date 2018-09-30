package concurrency.home;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class SingleFileTextAnalyzer implements Callable<Set<String>> {

    private final String source;
    private final String[] words;

    SingleFileTextAnalyzer(String source, String[] words) {
        this.source = source;
        this.words = words;

    }

    @Override
    public Set<String> call() throws FileNotFoundException {
        Set<String> sentences = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(source))) {
            Pattern pattern = Pattern.compile("[?.!]");
            scanner.useDelimiter(pattern);

            while (scanner.hasNext()) {
                StringBuilder sb = new StringBuilder();
                String sentence = scanner.next().replace(System.getProperty("line.separator"), "");

                Arrays.stream(words)
                        .filter(word -> sentence.toLowerCase().contains(word.toLowerCase()))
                        .forEach(x -> {
                            sb.append(sentence.trim())
                                    .append(scanner.findInLine("[?.!]"))
                            /*.append("\r\n")*/;
                            sentences.add(sb.toString());
                        });
            }
        }
        return sentences;
    }
}