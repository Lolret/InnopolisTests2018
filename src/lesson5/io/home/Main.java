package lesson5.io.home;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

public class Main {

    private static int WORD_MAX_LENGHT = 15;
    private static int SENTENCE_MAX_LENGHT = 15;
    private static int SENTENCE_PARAGRAPH_COUNT = 20;
    private static int PROBABILITY = 2;
    private static int WORD_ARRAY_LENGHT = 1_000;

    private static boolean GAUSSIAN_SENTENCE_COUNT = true;
    private static int Sentence_COUNT_MIN = 500;
    private static int Sentence_COUNT_MAX = 1_500;

    private static String PATH_TO_RAVE = "d://";
    private static int FILE_COUNT = 3;

    public static void main(String[] args) {

        String[] words = generateWords(WORD_ARRAY_LENGHT, WORD_MAX_LENGHT);

        raveGenerator(PATH_TO_RAVE, FILE_COUNT, GAUSSIAN_SENTENCE_COUNT, words);
    }

    public static void raveGenerator(String path,
                                      int n, boolean gaussianSentenceCount, String[] words) {
        for (int i = 0; i < n; i++) {
            generateFile(path, gaussianSentenceCount, words);
        }
    }

    private static void generateFile(String path, boolean gaussianSentenceCount, String[] words) {

        String filePathName = path + "raveFile_" + UUID.randomUUID().toString() + ".txt";
        Path p = Paths.get(filePathName);

        try {
            Files.createFile(p);

        } catch (IOException e) {
            e.printStackTrace();
        }
        populateFile(filePathName, gaussianSentenceCount, words);
    }

    private static void populateFile(String filePathName, boolean gaussianSentenceCount, String[] words) {

        try {
            Files.write(Paths.get(filePathName),
                    generateText(Sentence_COUNT_MIN, Sentence_COUNT_MAX, gaussianSentenceCount,
                            SENTENCE_PARAGRAPH_COUNT, SENTENCE_MAX_LENGHT,
                            words,
                            PROBABILITY, WORD_MAX_LENGHT).getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static String generateWord(int wordLenght) {
        Random r = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < r.nextInt(wordLenght) + 1; i++) {
            stringBuffer.append((char) (r.nextInt(26) + 'a'));
        }
        return stringBuffer.toString();
    }

    private static String[] generateWords(int wordArrayLenght, int wordLenght) {
        String[] strings = new String[wordArrayLenght];
        for (int i = 0; i < wordArrayLenght; i++) {
            strings[i] = generateWord(wordLenght);
        }
        return strings;
    }

    private static String generateSentence(int SentenceLenght, String[] words, int probability, int wordLenght) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        String firstWord;

        if (random.nextInt(probability) == probability - 1) firstWord = generateWord(wordLenght);
        else firstWord = words[random.nextInt(words.length)];

        stringBuffer.append((char) (firstWord.charAt(0) - 32));
        for (int i = 1; i < firstWord.length(); i++) {
            stringBuffer.append(firstWord.charAt(i));
        }

        for (int i = 0; i < random.nextInt(SentenceLenght); i++) {
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

    private static String generateText(int SentenceCountMin, int SentenceCountMax, boolean gaussianSentenceCount,
                                       int SentenceParagpaphCount,
                                       int SentenceLenght,
                                       String[] words,
                                       int probability,
                                       int wordLenght) {

        int middleSentenceCount = (SentenceCountMax + SentenceCountMin) / 2;
        Random random = new Random();
        int SentenceCount;

        if (gaussianSentenceCount) {
            SentenceCount = (int) (middleSentenceCount + random.nextGaussian() * middleSentenceCount);
            if (SentenceCount < middleSentenceCount * 0.5) SentenceCount = SentenceCountMin;
            if (SentenceCount > middleSentenceCount * 1.5) SentenceCount = SentenceCountMax;
        } else SentenceCount = (int) (SentenceCountMin + Math.random() * (SentenceCountMax - SentenceCountMin));

        StringBuffer stringBuffer = new StringBuffer();
        int temporarySentenceParagpaphCount = SentenceParagpaphCount;

        do {
            stringBuffer.append(generateSentence(SentenceLenght, words, probability, wordLenght));
            SentenceCount--;
            temporarySentenceParagpaphCount--;

            if (random.nextInt(20) == SentenceParagpaphCount - 1) {
                stringBuffer.append(System.getProperty("line.separator"));
                temporarySentenceParagpaphCount = SentenceParagpaphCount;
            }

            if (temporarySentenceParagpaphCount == 0) {
                stringBuffer.append(System.getProperty("line.separator"));
                temporarySentenceParagpaphCount = SentenceParagpaphCount;
            }
        }
        while (SentenceCount > 0);

        return stringBuffer.toString();
    }
}