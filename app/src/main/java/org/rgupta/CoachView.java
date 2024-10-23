/**
 * The CoachView class is responsible for displaying the UI to the user. It includes
 * input fields, buttons, and feedback elements. This class provides methods for
 * updating the display and interacting with the user.
 */
package org.rgupta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener; // Dieser Import fehlt
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class CoachView extends JFrame {

    private JLabel correctGuessesLabel;
    private JLabel incorrectGuessesLabel;
    private JLabel totalGuessesLabel;
    private JTextField guessInputField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private JLabel imageLabel;
    private JTextField indexInputField;
    private JButton randomButton;
    private JButton indexButton;
    private JButton saveButton;
    private JButton loadButton;

    public CoachView() {
        // Frame Einstellungen
        this.setTitle("Coach View");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Statistik Panel (links)
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 1, 10, 10));
        correctGuessesLabel = new JLabel("Correct Guesses: 0");
        incorrectGuessesLabel = new JLabel("Incorrect Guesses: 0");
        totalGuessesLabel = new JLabel("Total Guesses: 0");

        // Persistenz Buttons im unteren Panel hinzufügen
        JPanel persistPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveButton = new JButton("Save Session");
        loadButton = new JButton("Load Session");
        persistPanel.add(saveButton);
        persistPanel.add(loadButton);

        statsPanel.add(correctGuessesLabel);
        statsPanel.add(incorrectGuessesLabel);
        statsPanel.add(totalGuessesLabel);

        // Eingabe- und Rückmeldungspanel (unten)
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        inputPanel.add(persistPanel); // persistPanel hinzufügen
        this.add(inputPanel, BorderLayout.SOUTH);

        // Textfeld für Rateeingabe und Button zum Abschicken
        JPanel guessPanel = new JPanel();
        guessPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        guessInputField = new JTextField(15);
        submitButton = new JButton("Submit");

        guessPanel.add(new JLabel("Your Guess:"));
        guessPanel.add(guessInputField);
        guessPanel.add(submitButton);

        // Rückmeldung Label
        feedbackLabel = new JLabel("Feedback: ");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(guessPanel);
        inputPanel.add(feedbackLabel);

        // Bild und obere Eingabe (Mitte)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Obere Auswahlfelder (Zufällig oder Index)
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        indexInputField = new JTextField(5);
        randomButton = new JButton("Choose Random");
        indexButton = new JButton("Choose by Index");

        selectionPanel.add(new JLabel("Index:"));
        selectionPanel.add(indexInputField);
        selectionPanel.add(indexButton);
        selectionPanel.add(randomButton);

        centerPanel.add(selectionPanel, BorderLayout.NORTH);

        // Bildanzeige
        imageLabel = new JLabel("No Image Available", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 250));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        this.add(statsPanel, BorderLayout.WEST);
        this.add(inputPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);

        // Zeige die GUI
        this.setVisible(true);
    }

    // Methode zur Aktualisierung der Statistik
    public void updateStatistics(int correct, int incorrect) {
        correctGuessesLabel.setText("Correct Guesses: " + correct);
        incorrectGuessesLabel.setText("Incorrect Guesses: " + incorrect);
        totalGuessesLabel.setText("Total Guesses: " + (correct + incorrect));
    }

    public void setImageIconFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            Image image = ImageIO.read(url); // Bild von der URL lesen
            if (image != null) { // Prüfen, ob das Bild erfolgreich geladen wurde
                ImageIcon imageIcon = new ImageIcon(image);
                imageLabel.setIcon(imageIcon); // Icon in JLabel setzen
                imageLabel.setText(null); // Text entfernen, wenn das Bild geladen ist
            } else {
                imageLabel.setText("Image not available"); // Wenn das Bild nicht geladen werden konnte
                System.out.println("Image could not be loaded from URL: " + imageUrl);
            }
        } catch (IOException e) {
            imageLabel.setText("Image not available"); // Falls Fehler beim Laden
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    // Methode zur Anzeige von Feedback
    public void setFeedback(String feedback) {
        feedbackLabel.setText("Feedback: " + feedback);
    }

    // Methode zur Anzeige von Fehlermeldungen mit JOptionPane (nur bei Problemen)
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Getter für Benutzerinteraktionen
    public String getGuessInput() {
        return guessInputField.getText();
    }

    public String getIndexInput() {
        return indexInputField.getText();
    }

    // Methode, um Eingabefelder zu sperren/entsperren
    public void setWordSelectionEnabled(boolean enabled) {
        randomButton.setEnabled(enabled);
        indexButton.setEnabled(enabled);
        indexInputField.setEnabled(enabled);
    }

    public void setGuessInputEnabled(boolean enabled) {
        guessInputField.setEnabled(enabled);
        submitButton.setEnabled(enabled);
    }

    // Methoden, um ActionListener zu setzen
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addRandomButtonListener(ActionListener listener) {
        randomButton.addActionListener(listener);
    }

    public void addIndexButtonListener(ActionListener listener) {
        indexButton.addActionListener(listener);
    }

    // Getter für Persistenz Button Listener
    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addLoadButtonListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }
}
