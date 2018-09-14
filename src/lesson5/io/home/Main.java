package lesson5.io.home;

public class Main {

    public static void main(String[] args) {
        String PATH_TO_RAVE = "d://";
        int FILE_COUNT = 3;
        boolean GAUSSIAN_SENTENCE_COUNT = true;

        int WORD_ARRAY_LENGHT = 1_000;
        int WORD_MAX_LENGHT = 15;

        String[] words = RaveGenerator.generateWordsArray(WORD_ARRAY_LENGHT, WORD_MAX_LENGHT);

        RaveGenerator.getFiles(PATH_TO_RAVE, FILE_COUNT, GAUSSIAN_SENTENCE_COUNT, words);
    }
}