plugins {
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")
    implementation("com.google.guava:guava:32.1.2-jre")
    implementation("commons-validator:commons-validator:1.9.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8)) // Set Java version for compatibility
    }
}

application {
    mainClass.set("org.rgupta.App")
}

tasks.test {
    useJUnitPlatform()
}

// Define the generateJavadoc task
tasks.register<Javadoc>("generateJavadoc") {
    group = "Documentation"
    description = "Generates Javadoc"

    source = sourceSets["main"].allJava
    classpath = sourceSets["main"].compileClasspath
    destinationDir = file("$buildDir/docs/javadoc")

    options {
        encoding = "UTF-8"
        (this as StandardJavadocDocletOptions).apply {
            addBooleanOption("author", true)   // Use addBooleanOption for author
            addBooleanOption("version", true)  // Use addBooleanOption for version
            links("https://docs.oracle.com/javase/8/docs/api/")  // Use links method without null check
        }
    }
}
