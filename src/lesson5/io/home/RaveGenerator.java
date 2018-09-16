package lesson5.io.home;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

public class RaveGenerator {

    private static final int SENTENCE_MAX_LENGHT = 15;
    private static final int SENTENCE_PARAGRAPH_COUNT = 20;
    private static final int PROBABILITY = 1000;

    private static final int Sentence_COUNT_MIN = 10_000;
    /*3_810_685;*/
    private static final int Sentence_COUNT_MAX = 10_000;
    /*3_810_685;*/


    public static void getFiles(String path,
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
                            PROBABILITY).getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static String generateWord(int wordLenght) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < random.nextInt(wordLenght) + 1; i++) {
            stringBuffer.append((char) (random.nextInt(26) + 'a'));
        }
        return stringBuffer.toString();
    }

    public static String[] generateWordsArray(int wordArrayLenght, int wordLenght) {
        String[] strings = new String[wordArrayLenght];
        for (int i = 0; i < wordArrayLenght; i++) {
            strings[i] = generateWord(wordLenght);
        }
        return strings;
    }

    private static String generateSentence(int SentenceLenght, String[] words, int probability) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        String firstWord;

        if (random.nextInt(probability) == probability - 1) firstWord = generateWord(random.nextInt(10) + 1);
        else firstWord = words[random.nextInt(words.length)];

        stringBuffer.append((char) (firstWord.charAt(0) - 32));
        for (int i = 1; i < firstWord.length(); i++) {
            stringBuffer.append(firstWord.charAt(i));
        }

        for (int i = 0; i < random.nextInt(SentenceLenght); i++) {
            if (random.nextInt(10) == 9) stringBuffer.append(",");
            stringBuffer.append(" ");
            if (random.nextInt(probability) == probability - 1)
                stringBuffer.append(generateWord(10));
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
                                       int probability) {

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
            stringBuffer.append(generateSentence(SentenceLenght, words, probability));
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