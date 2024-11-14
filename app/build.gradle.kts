/*
 * Buildskript für das Unterprojekt 'app'.
 */

plugins {
    // Anwendung und Java-Plugins hinzufügen
    application
    java
}

repositories {
    // Maven Central für die Auflösung von Abhängigkeiten verwenden
    mavenCentral()
}

dependencies {
    // JUnit Jupiter für Tests verwenden
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    // Laufzeitabhängigkeit für JUnit Plattform
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")

    // Abhängigkeiten der Anwendung
    implementation("com.google.guava:guava:32.1.2-jre")
    implementation("commons-validator:commons-validator:1.9.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

application {
    // Hauptklasse der Anwendung definieren
    mainClass.set("org.rgupta.App")
}

tasks.test {
    // JUnit Plattform für Unit Tests verwenden
    useJUnitPlatform()
}

// Definiere den generateJavadoc Task
tasks.register<Javadoc>("generateJavadoc") {
    group = "Documentation"
    description = "Generiert die Javadoc Dokumentation"

    source = sourceSets["main"].allJava
    classpath = sourceSets["main"].compileClasspath
    destinationDir = file("$buildDir/docs/javadoc")

    options {
        this as StandardJavadocDocletOptions
        encoding = "UTF-8"
        charSet = "UTF-8"
        author = true
        version = true
        links("https://docs.oracle.com/javase/8/docs/api/")
    }
}
