plugins {
    application

    jacoco
    id ("org.barfuin.gradle.jacocolog") version "3.1.0"

    pmd
    id ("com.github.spotbugs") version "5.1.3"

    id ("com.diffplug.spotless") version "6.21.0"
    checkstyle
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
    archiveBaseName.set("producerconsumer")
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

spotbugs {
    ignoreFailures = true
}

checkstyle {
    toolVersion = "10.12.3"
}

tasks.checkstyleTest {
    enabled = false
}

tasks.withType<Checkstyle>().configureEach {
    ignoreFailures = true
}

/* Formatter Configuration */
spotless {
    java {
        importOrder()
        cleanthat()
        googleJavaFormat()
    }
}

tasks.check {
    dependsOn(tasks.spotlessCheck)
}
