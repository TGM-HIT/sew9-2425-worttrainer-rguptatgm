package org.rgupta;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CoachTest {

    private Coach coach;
    private Statistics statistics;
    private ArrayList<Flashcard> flashcards;

    @BeforeEach
    public void setUp() {
        // Initialisiere die Beispiel-Daten vor jedem Test
        statistics = new Statistics();
        flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("Dog", "https://example.com/dog.jpg"));
        flashcards.add(new Flashcard("Cat", "https://example.com/cat.jpg"));
        flashcards.add(new Flashcard("Bird", "https://example.com/bird.jpg"));
        coach = new Coach(statistics, flashcards);
    }

    @Test
    public void testAnswerCorrect() {
        // Setze das aktuelle Flashcard
        coach.setCurrentFlashcard(0); // Flashcard mit dem Wort "Dog"

        // Gebe die richtige Antwort
        boolean result = coach.answer("Dog");

        assertTrue(result, "The answer should be correct.");
        assertEquals(1, statistics.getCorrect(), "Correct count should be 1.");
        assertEquals(0, statistics.getIncorrect(), "Incorrect count should be 0.");
    }

    @Test
    public void testAnswerIncorrect() {
        // Setze das aktuelle Flashcard
        coach.setCurrentFlashcard(1); // Flashcard mit dem Wort "Cat"

        // Gebe eine falsche Antwort
        boolean result = coach.answer("Dog");

        assertFalse(result, "The answer should be incorrect.");
        assertEquals(0, statistics.getCorrect(), "Correct count should be 0.");
        assertEquals(1, statistics.getIncorrect(), "Incorrect count should be 1.");
    }

    @Test
    public void testChooseRandomFlashcard() {
        // Setze ein Flashcard und überprüfe, dass ein zufälliges gewählt wird
        Flashcard initialFlashcard = coach.getCurrentFlashcard();
        coach.chooseRandom();
        Flashcard newFlashcard = coach.getCurrentFlashcard();

        assertNotNull(newFlashcard, "New flashcard should not be null.");
        assertNotSame(initialFlashcard, newFlashcard, "New flashcard should be different from the initial one.");
    }

    @Test
    public void testSetCurrentFlashcard() {
        // Setze ein Flashcard explizit
        coach.setCurrentFlashcard(2); // Setze auf "Bird"

        Flashcard currentFlashcard = coach.getCurrentFlashcard();

        assertEquals("Bird", currentFlashcard.getWord(), "Current flashcard should be 'Bird'.");
    }

    @Test
    public void testChooseFlashcardByIndex() {
        // Wähle das Flashcard mit dem Index 1 ("Cat")
        coach.choose(1);

        Flashcard chosenFlashcard = coach.getCurrentFlashcard();

        assertEquals("Cat", chosenFlashcard.getWord(), "Chosen flashcard should be 'Cat'.");
    }

    @Test
    public void testStatisticsCorrectAnswerIncrementsCorrect() {
        coach.setCurrentFlashcard(0); // "Dog"
        coach.answer("Dog");

        assertEquals(1, statistics.getCorrect(), "Correct answers should increment.");
        assertEquals(0, statistics.getIncorrect(), "Incorrect answers should remain the same.");
    }

    @Test
    public void testStatisticsIncorrectAnswerIncrementsIncorrect() {
        coach.setCurrentFlashcard(1); // "Cat"
        coach.answer("Dog");

        assertEquals(0, statistics.getCorrect(), "Correct answers should remain the same.");
        assertEquals(1, statistics.getIncorrect(), "Incorrect answers should increment.");
    }
}
