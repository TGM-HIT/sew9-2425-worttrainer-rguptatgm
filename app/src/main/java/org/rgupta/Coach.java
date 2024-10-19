package org.rgupta;

import java.util.ArrayList;
import java.util.Random;

public class Coach {
    private Statistics statistics;
    private ArrayList<Flashcard> flashcards;
    private Flashcard currentFlashcard;

    public Coach(Statistics statistics, ArrayList<Flashcard> flashcards) {
        if (!flashcards.isEmpty() && flashcards != null)
            this.flashcards = flashcards;

        if (statistics != null)
            this.statistics = statistics;

        this.currentFlashcard = flashcards.get(0);
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
        return word.equals(this.currentFlashcard.getWord());
    }

    public void choose(int index) {
        this.currentFlashcard = this.flashcards.get(index);
    }

    public void chooseRandom() {
        // Zufälliger Index zwischen 0 und flashcards.size() - 1
        Random random = new Random();
        int randomIndex = random.nextInt(flashcards.size()); // Gibt eine Zahl zwischen 0 (inklusive) und der Größe der
                                                             // flashcards-Liste (exklusive) zurück.
        this.currentFlashcard = this.flashcards.get(randomIndex);
    }
}
