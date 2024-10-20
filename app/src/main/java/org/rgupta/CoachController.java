package org.rgupta;

import javax.swing.*;
import java.util.ArrayList;

public class CoachController {

    private CoachView coachView;
    private Coach coach;

    public CoachController(CoachView view, Coach coach) {
        this.coachView = view;
        this.coach = coach;

        // Setze ActionListener für die Buttons in der View
        coachView.addSubmitButtonListener(e -> handleGuess());
        coachView.addRandomButtonListener(e -> handleRandomChoice());
        coachView.addIndexButtonListener(e -> handleIndexChoice());

        // Lade initial ein zufälliges Wort-Bild-Paar
        handleRandomChoice();
    }

    // Methode zur Behandlung des Rateversuchs
    private void handleGuess() {
        String guess = coachView.getGuessInput();
        if (guess.isEmpty()) {
            coachView.showError("The guess cannot be empty.");
            return;
        }

        // Überprüfen der Antwort
        if (coach.answer(guess)) {
            coachView.setFeedback("Correct!");
            JOptionPane.showMessageDialog(coachView, "Correct! You guessed the word.");
            coachView.setWordSelectionEnabled(true);
            coachView.setGuessInputEnabled(false);
        } else {
            coachView.setFeedback("Incorrect!");
            JOptionPane.showMessageDialog(coachView, "Incorrect! Try again.");
        }

        // Statistiken aktualisieren
        coachView.updateStatistics(coach.getStatistics().getCorrect(), coach.getStatistics().getIncorrect());
    }

    // Methode zur Behandlung der zufälligen Auswahl
    private void handleRandomChoice() {
        coachView.setGuessInputEnabled(true);
        coachView.setWordSelectionEnabled(false);
        coach.chooseRandom();
        coachView.setImageIconFromUrl(coach.getCurrentFlashcard().getURL()); // Bild anzeigen
        coachView.setFeedback("Waiting for input...");
    }

    // Methode zur Auswahl nach Index
    private void handleIndexChoice() {
        coachView.setGuessInputEnabled(true);
        try {
            int index = Integer.parseInt(coachView.getIndexInput());
            if (index >= 0 && index < coach.getFlashcards().size()) {
                coach.choose(index);
                coachView.setImageIconFromUrl(coach.getCurrentFlashcard().getURL()); // Bild anzeigen
                coachView.setFeedback("Waiting for input...");
            } else {
                coachView.showError("Invalid index. Please enter a valid index between 0 and "
                        + (coach.getFlashcards().size() - 1));
            }
        } catch (NumberFormatException ex) {
            coachView.showError("Invalid input. Please enter a valid number.");
        }
        coachView.setWordSelectionEnabled(false);
    }

    public static void main(String[] args) {
        // Einige Beispiel-Wort-Bild-Paare erstellen
        ArrayList<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("Dog", "https://upload.wikimedia.org/wikipedia/commons/6/65/Hund_Bosnien.jpg"));
        flashcards.add(new Flashcard("Cat", "https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg"));
        flashcards.add(new Flashcard("Bird", "https://upload.wikimedia.org/wikipedia/commons/7/70/Bird.jpg"));

        // Statistiken initialisieren
        Statistics statistics = new Statistics();

        // Coach und View erstellen
        Coach coach = new Coach(statistics, flashcards);
        CoachView view = new CoachView();

        // Controller starten
        new CoachController(view, coach);
    }
}
