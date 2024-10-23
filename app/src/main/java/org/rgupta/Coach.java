/**
 * The Coach class is responsible for managing a collection of flashcards and interacting with the user.
 * It keeps track of the user's performance statistics and handles user answers to flashcard questions.
 * The class allows selecting a specific flashcard or randomly choosing one from the list.
 *
 * @author Rahul Gupta
 * @date 23 October 2024
 */

package org.rgupta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Coach implements Serializable {
    private Statistics statistics;
    private ArrayList<Flashcard> flashcards;
    private Flashcard currentFlashcard;

    public Coach(Statistics statistics, ArrayList<Flashcard> flashcards) {
        if (!flashcards.isEmpty() && flashcards != null)
            this.flashcards = flashcards;

        if (statistics != null)
            this.statistics = statistics;

        this.chooseRandom();
    }

    public ArrayList<Flashcard> getFlashcards() {
        return this.flashcards;
    }

    public Statistics getStatistics() {
        return this.statistics;
    }

    public Flashcard getCurrentFlashcard() {
        return this.currentFlashcard;
    }

    public void setCurrentFlashcard(int index) {
        this.currentFlashcard = this.flashcards.get(index);
    }

    public boolean answer(String word) {
        if (word.equals(this.currentFlashcard.getWord())) {
            this.statistics.addCorrect();
            return true;
        }
        this.statistics.addIncorrect();
        return false;
    }

    public void choose(int index) {
        this.currentFlashcard = this.flashcards.get(index);
    }

    public void chooseRandom() {
        // Zufälliger Index zwischen 0 und flashcards.size() - 1
        Random random = new Random();
        int randomIndex = random.nextInt(flashcards.size()); // Gibt eine Zahl zwischen 0 (inklusive) und der Größe der
                                                             // flashcards-Liste (exklusive) zurück.
        while (this.flashcards.get(randomIndex).equals(this.currentFlashcard)) {
            randomIndex = random.nextInt(flashcards.size());
        }
        this.currentFlashcard = this.flashcards.get(randomIndex);
    }
}
