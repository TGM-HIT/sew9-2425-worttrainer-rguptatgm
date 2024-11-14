plugins {
    application
    java
}

repositories {
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
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    // Hauptklasse der Anwendung definieren
    mainClass.set("org.rgupta.App")
}

tasks.test {
    useJUnitPlatform()
}

// Definiere den generateJavadoc Task
tasks.register<Javadoc>("generateJavadoc") {
    group = "Documentation"
    description = "Generates Javadoc"

    source = sourceSets["main"].allJava
    classpath = sourceSets["main"].compileClasspath
    destinationDirectory.set(file("$buildDir/docs/javadoc"))

    options {
        encoding = "UTF-8"
    }

    val standardOptions = options as StandardJavadocDocletOptions

    standardOptions.charSet = "UTF-8"
    standardOptions.author = true
    standardOptions.version = true
    standardOptions.links("https://docs.oracle.com/javase/8/docs/api/")
}
