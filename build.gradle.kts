val seleniumJavaVersion = "4.14.1"
val seleniumJupiterVersion = "5.0.1"
val webdrivermanagerVersion = "5.6.3"
val junitJupiterVersion = "5.9.1"

plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "6.0.1.5171"
}

group = "id.ac.ui.cs.advprog"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot dependencies
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Lombok for reducing boilerplate code
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Development tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Testing dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Selenium and WebDriverManager for functional tests
    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
    testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
    testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")

    // JUnit dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

tasks.register<Test>("unitTest") {
    description = "Runs unit tests"
    group = "verification"

    filter {
        excludeTestsMatching("*FunctionalTest")
    }
}

tasks.register<Test>("functionalTest") {
    description = "Runs unit tests"
    group = "verification"

    filter {
        includeTestsMatching("*FunctionalTest")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.test {
    filter {
        excludeTestsMatching("*FunctionalTest")
    }

    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "nsrnannaisha_eshop-advprog25")
        property("sonar.organization", "nsrnannaisha")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}