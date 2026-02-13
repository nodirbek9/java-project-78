plugins {
    application
    checkstyle
    id("org.sonarqube") version "7.1.0.6387"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sonar {
    properties {
        property("sonar.projectKey", "nodirbek9_java-project-78");
        property("sonar.organization", "nodirbek9")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
checkstyle {
    toolVersion = "10.12.4"
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
application { mainClass.set("hexlet.code.App") }

tasks.test {
    useJUnitPlatform()
}
group = "hexlet.code"
version = "1.0-SNAPSHOT"