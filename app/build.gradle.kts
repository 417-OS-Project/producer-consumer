plugins {
    application

    jacoco
    id ("org.barfuin.gradle.jacocolog") version "3.1.0"

    pmd
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.google.guava:guava:32.1.1-jre")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("producerconsumer.App")
}

tasks.jar {
    manifest.attributes["Main-Class"] = application.mainClass
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

/* Test Configuration */

jacoco {
    toolVersion = "0.8.10"
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

/* Check Configurations */

pmd {
    toolVersion = "6.55.0"
    isConsoleOutput = true
}

tasks.pmdTest {
    enabled = false
}

tasks.withType<Pmd>().configureEach {
    ignoreFailures = true
}
