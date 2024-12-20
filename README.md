# sew9-2425-worttrainer-rguptatgm

## Beschreibung

`sew9-2425-worttrainer-rguptatgm` ist eine Java-basierte Anwendung zum Trainieren von Wort-Bild-Paaren. Benutzer können Bilder betrachten und versuchen, das zugehörige Wort zu erraten. Die Anwendung zeigt Rückmeldungen zu den Eingaben an und verfolgt die Anzahl der richtigen und falschen Antworten. Die Session kann gespeichert und wiederhergestellt werden, sodass Benutzer ihre Fortschritte fortsetzen können.

### Hauptfunktionen
- Auswahl von zufälligen oder gezielten Wort-Bild-Paaren.
- Überprüfung der Benutzereingabe (richtige oder falsche Antwort).
- Anzeige von Statistiken (richtige/falsche Antworten).
- Speicherung und Laden von Trainingseinheiten (Persistenz).
- Unterstützt serielle Speicherung (Dateiformat `.ser`).

## Voraussetzungen

- **Java 8** oder höher
- **Gradle** zur Verwaltung von Abhängigkeiten und zum Bauen des Projekts
- **JUnit 5** für Unit Tests

## Verwendete Bibliotheken

1. **Apache Commons Validator**  
   Verwendet für die URL-Validierung, um sicherzustellen, dass die Bild-URLs der Flashcards gültig sind.  
   Dokumentation: [Apache Commons Validator](https://commons-apache-org.translate.goog/proper/commons-validator/dependency-info.html?_x_tr_sl=en&_x_tr_tl=de&_x_tr_hl=de&_x_tr_pto=sc)

## Projektstruktur

Das Projekt folgt einer typischen Gradle-Struktur:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── rgupta
│   │   │           ├── App.java
│   │   │           ├── Coach.java
│   │   │           ├── CoachController.java
│   │   │           ├── CoachView.java
│   │   │           ├── Flashcard.java
│   │   │           ├── PersistenceStrategy.java
│   │   │           ├── SerializationStrategy.java
│   │   │           └── Statistics.java
│   ├── test
│   │   └── java
│   │       └── org
│   │           └── rgupta
│   │               └── CoachTest.java
└── build.gradle
```

### Wichtige Klassen
- **`Coach`**: Verwaltet die Flashcards und überprüft die Antworten.
- **`CoachController`**: Bindet die View und die Logik zusammen. Handelt Benutzeraktionen wie das Erraten von Wörtern und das Speichern/Laden von Sessions.
- **`CoachView`**: Die grafische Benutzeroberfläche (GUI), erstellt mit `Swing`. Sie zeigt die Flashcards, Rückmeldungen und Statistiken an.
- **`Flashcard`**: Repräsentiert ein einzelnes Wort-Bild-Paar.
- **`Statistics`**: Hält die Statistiken für richtige und falsche Antworten.
- **`PersistenceStrategy`**: Schnittstelle für die Speicherstrategie. Die Anwendung unterstützt zurzeit die Java-Serialisierung.
- **`SerializationStrategy`**: Implementiert die Serialisierung, um Trainingseinheiten als `.ser`-Dateien zu speichern.

## Installation und Ausführung

1. **Projekt klonen**
   
   ```bash
   git clone https://github.com/yourusername/sew9-2425-worttrainer-rguptatgm.git
   cd sew9-2425-worttrainer-rguptatgm
   ```

2. **Abhängigkeiten mit Gradle installieren**
   
   Stelle sicher, dass du `Gradle` installiert hast. Führe dann im Projektverzeichnis den Befehl aus:

   ```bash
   gradle build
   ```

3. **Anwendung ausführen**
   
   Um die Anwendung zu starten, kannst du den folgenden Befehl verwenden:

   ```bash
   gradle run
   ```

4. **Unit Tests ausführen**
   
   Um die Unit Tests (basierend auf JUnit 5) auszuführen, verwende den folgenden Befehl:

   ```bash
   gradle test
   ```

## Anwendungshinweise

### Speichern und Laden von Sessions
- Um eine Trainingseinheit zu speichern, klicken Sie auf „Save Session“. Die Sitzung wird in einer `.ser`-Datei gespeichert.
- Um eine Sitzung zu laden, klicken Sie auf „Load Session“ und wählen Sie die gespeicherte `.ser`-Datei aus.

### Fehlerbehandlung
- Sollten beim Laden oder Speichern Probleme auftreten, wird eine Fehlermeldung in einem Dialogfenster angezeigt.
- Nicht verfügbare Bilder werden in der GUI mit einer Fehlermeldung angezeigt.

## Weitere Aufgaben
- **Erweiterbarkeit**: Die Anwendung kann durch die Implementierung weiterer Speicherstrategien (z.B. JSON, XML, SQLite) erweitert werden, indem die `PersistenceStrategy`-Schnittstelle erweitert wird.
- **Neue Flashcards hinzufügen**: Weitere Flashcards können einfach durch Ändern oder Hinzufügen in der `main`-Methode des `App`-Einstiegspunkts hinzugefügt werden.

## Mitwirkende

- **Rahul Gupta** – Hauptentwickler und Projektleitung  
  Kontakt: [rahul@example.com](mailto:rgupta@student.tgm.ac.at)
