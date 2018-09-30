package unitTests.inClass.realExample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WordFinderUserTest {

    public static final String FILE_ANY = "file://any";
    public static final String WORD = "one";
    private WordFinderUser wordFinderUser;
    private WordFinder wordFinder = Mockito.mock(WordFinder.class);
    private final static String FIRST_SENTENCE = "Doing test one";
    private final static String SECOND_SENTENCE = "Doing test";



    @BeforeEach
    void beforeEach() {
        wordFinderUser = new WordFinderUser(wordFinder);
    }


    @Test()
    void doWordNullTest() {
        when(wordFinder.getSentences(any())).thenReturn(null);
        assertDoesNotThrow(() -> wordFinderUser.doWord("file://any", ""));
    }

    @Test
    void doWordEmptySet() {
        when(wordFinder.getSentences(any())).thenReturn(new HashSet<>());
        try {
            wordFinderUser.doWord("file://any", "");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        verify(wordFinder,
                times(0)).checkIfWordInSentence(any(), any());
        verify(wordFinder,
                times(0)).writeSentenceToResult(any());
    }

    @Test
    void writeSentenceIfTrue() {
        when(wordFinder.getSentences(any()))
                .thenReturn(new HashSet<>(Arrays.asList(FIRST_SENTENCE, SECOND_SENTENCE)));

        when(wordFinder.checkIfWordInSentence(FIRST_SENTENCE, WORD)).thenReturn(true);
        when(wordFinder.checkIfWordInSentence(SECOND_SENTENCE, WORD)).thenReturn(false);
        try {
            wordFinderUser.doWord(FILE_ANY, WORD);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        verify(wordFinder, times(1)).writeSentenceToResult(FIRST_SENTENCE);
        verify(wordFinder, times(0)).writeSentenceToResult(SECOND_SENTENCE);
    }


    @Test
    void doWordURLForming() {
        final ArgumentCaptor<URL> arfumentCaptor = ArgumentCaptor.forClass(URL.class);
        try {
            wordFinderUser.doWord(FILE_ANY, WORD);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        verify(wordFinder).getSentences(arfumentCaptor.capture());
        assertEquals(FILE_ANY,arfumentCaptor.getValue().toString());
    }

    @Test
    void doWordBadURL() {
        assertThrows(MalformedURLException.class, () -> wordFinderUser.doWord("", ""));
    }
}