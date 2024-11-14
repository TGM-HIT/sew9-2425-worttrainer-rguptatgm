plugins {
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")
    // This dependency is used by the application.
    implementation("com.google.guava:guava:32.1.2-jre")
    implementation("commons-validator:commons-validator:1.9.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    // Ensure compatibility with Java 8
    withJavac {
        options.release.set(8)
    }
}

application {
    // Define the main class for the application.
    mainClass.set("org.rgupta.App")
}

tasks.test {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

// Define the generateJavadoc task
tasks.register<Javadoc>("generateJavadoc") {
    group = "Documentation"
    description = "Generates Javadoc"

    source = sourceSets["main"].allJava
    classpath = sourceSets["main"].compileClasspath
    @Suppress("DEPRECATION")
    destinationDir = file("$buildDir/docs/javadoc")

    options {
        encoding = "UTF-8"
        // Cast options to StandardJavadocDocletOptions to access advanced options
        (this as StandardJavadocDocletOptions).apply {
            charset = "UTF-8" // Use 'charset' instead of 'charSet'
            author.set(true)   // Use 'set' method for Property<Boolean>
            version.set(true)  // Use 'set' method for Property<Boolean>
            links.add("https://docs.oracle.com/javase/8/docs/api/") // Use 'links.add'
        }
    }
}
