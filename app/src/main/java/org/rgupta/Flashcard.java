/**
 * The Flashcard class represents an individual flashcard, containing a word and
 * an associated image URL. It includes methods for URL validation to ensure
 * that the flashcard's image can be properly retrieved.
 */
package org.rgupta;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;

public class Flashcard implements Serializable {
    private String word;
    private String imageURL;

    public Flashcard() {
    }

    public Flashcard(String word, String imageURL) {
        if (word != null)
            this.word = word;

        if (imageURL != null && checkURL(imageURL))
            this.imageURL = imageURL;
    }

    public String getURL() {
        return this.imageURL;
    }

    public String getWord() {
        return this.word;
    }

    public boolean checkURL(String urlString) {
        // Apache Commons UrlValidator zur Syntaxprüfung verwenden
        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

        // Zuerst die Syntax der URL prüfen
        if (!urlValidator.isValid(urlString)) {
            System.out.println("Invalid URL syntax.");
            return false;
        }

        // Wenn die URL-Syntax gültig ist, prüfen wir die Erreichbarkeit
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD"); // HEAD-Anfrage für schnellere Überprüfung
            connection.setConnectTimeout(5000); // Timeout für die Verbindung
            connection.setReadTimeout(5000); // Timeout für das Lesen der Antwort
            int responseCode = connection.getResponseCode();

            // Prüfen, ob der HTTP-Statuscode 2xx oder 3xx ist (d.h. Erfolg oder Umleitung)
            if (responseCode >= 200 && responseCode < 400) {
                System.out.println("URL is reachable.");
                return true;
            } else {
                System.out.println("URL is not reachable. Response code: " + responseCode);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error reaching the URL: " + e.getMessage());
            return false;
        }
    }
}
