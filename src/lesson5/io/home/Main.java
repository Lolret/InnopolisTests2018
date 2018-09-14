package lesson5.io.home;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

public class Main {

    static int WORD_MAX_LENGHT = 15;
    static int SENTENCE_MAX_LENGHT = 15;
    static int SENTENCE_COUNT = 1_000;
    static int SENTENCE_PARAGRAPH_COUNT = 20;
    static int WORD_ARRAY_LENGHT = 1_000;
    static int PROBABILITY = 2;


    static boolean GAUSSIAN_SENTENCE_COUNT = true;
    static int WORD_ARRAY_LENGHT_min = 500;
    static int WORD_ARRAY_LENGHT_max = 1_500;

    static String PATH_TO_RAVE = "d://";
    static int FILE_COUNT = 3;

    public static void main(String[] args) {

        raveGenerator(PATH_TO_RAVE, FILE_COUNT, GAUSSIAN_SENTENCE_COUNT);
    }

    public static void raveGenerator(String path,
                                     int n, boolean gaussianSentanceCount) {
        for (int i = 0; i < n; i++) {
            generateFile(path, gaussianSentanceCount);
        }
    }

    public static void generateFile(String path, boolean gaussianSentanceCount) {

        String filePathName = path + "raveFile_" + UUID.randomUUID().toString() + ".txt";
        Path p = Paths.get(filePathName);

        try {
            Files.createFile(p);

        } catch (IOException e) {
            e.printStackTrace();
        }
        populateFile(filePathName, gaussianSentanceCount);
    }

    static void populateFile(String filePathName, boolean gaussianSentanceCount) {

        try {
            if (gaussianSentanceCount) {
                Files.write(Paths.get(filePathName), generateText(WORD_ARRAY_LENGHT_min, WORD_ARRAY_LENGHT_max,
                        SENTENCE_PARAGRAPH_COUNT, SENTENCE_MAX_LENGHT,
                        generateWords(WORD_ARRAY_LENGHT, WORD_MAX_LENGHT),
                        PROBABILITY, WORD_MAX_LENGHT).getBytes());
            } else Files.write(Paths.get(filePathName), generateText(
                    SENTENCE_COUNT, SENTENCE_PARAGRAPH_COUNT, SENTENCE_MAX_LENGHT,
                    generateWords(WORD_ARRAY_LENGHT, WORD_MAX_LENGHT),
                    PROBABILITY, WORD_MAX_LENGHT).getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    static String generateWord(int wordLenght) {
        Random r = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < r.nextInt(wordLenght) + 1; i++) {
            stringBuffer.append((char) (r.nextInt(26) + 'a'));
        }
        return stringBuffer.toString();
    }

    static String[] generateWords(int wordArrayLenght, int wordLenght) {
        String[] strings = new String[wordArrayLenght];
        for (int i = 0; i < wordArrayLenght; i++) {
            strings[i] = generateWord(wordLenght);
        }
        return strings;
    }

    static String generateSentance(int sentanceLenght, String[] words, int probability, int wordLenght) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        String firstWord;

        if (random.nextInt(probability) == probability - 1) firstWord = generateWord(wordLenght);
        else firstWord = words[random.nextInt(words.length)];

        stringBuffer.append((char) (firstWord.charAt(0) - 32));
        for (int i = 1; i < firstWord.length(); i++) {
            stringBuffer.append(firstWord.charAt(i));
        }

        for (int i = 0; i < random.nextInt(sentanceLenght); i++) {
            if (random.nextInt(10) == 9) stringBuffer.append(",");
            stringBuffer.append(" ");
            if (random.nextInt(probability) == probability - 1)
                stringBuffer.append(generateWord(wordLenght));
            else stringBuffer.append(words[random.nextInt(words.length)]);
        }
        char punctuationMark;
        int rndChar = random.nextInt(3);
        if (rndChar == 0) punctuationMark = '.';
        else if (rndChar == 1) punctuationMark = '!';
        else punctuationMark = '?';

        stringBuffer.append(punctuationMark).append(" ");

        return stringBuffer.toString();
    }

    static String generateText(int sentanceCount,
                               int sentanceParagpaphCount,
                               int sentanceLenght,
                               String[] words,
                               int probability,
                               int wordLenght) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        int temporarySentanceParagpaphCount = sentanceParagpaphCount;
        do {
            stringBuffer.append(generateSentance(sentanceLenght, words, probability, wordLenght));
            sentanceCount--;
            temporarySentanceParagpaphCount--;

            if (random.nextInt(20) == sentanceParagpaphCount - 1) {
                stringBuffer.append(System.getProperty("line.separator"));
                temporarySentanceParagpaphCount = sentanceParagpaphCount;
            }

            if (temporarySentanceParagpaphCount == 0) {
                stringBuffer.append(System.getProperty("line.separator"));
                temporarySentanceParagpaphCount = sentanceParagpaphCount;
            }
        }
        while (sentanceCount > 0);

        return stringBuffer.toString();
    }

    static String generateText(int sentanceCountMin, int sentanceCountMax,
                               int sentanceParagpaphCount,
                               int sentanceLenght, String[] words,
                               int probability,
                               int wordLenght) {
        int middleSentanceCount = (sentanceCountMax + sentanceCountMin) / 2;
        Random random = new Random();

        int sentanceCount = (int) (middleSentanceCount + random.nextGaussian() * middleSentanceCount);
        if (sentanceCount < middleSentanceCount * 0.5) sentanceCount = sentanceCountMin;
        if (sentanceCount > middleSentanceCount * 1.5) sentanceCount = sentanceCountMax;

        return generateText(sentanceCount, sentanceParagpaphCount, sentanceLenght, words, probability, wordLenght);
    }
}