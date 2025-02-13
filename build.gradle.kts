plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "br.com.thecoders"
version = "0.0.1-alpha"

repositories {
    mavenCentral()
}

application {
    mainClass.set("br.com.thecoders.dumpsum.Launch")

}

tasks.withType<JavaExec> {
    jvmArgs = listOf(
        "--module-path", System.getenv("PATH_TO_FX") + "/lib",
        "--add-modules", "javafx.controls,javafx.fxml"
    )
}

javafx {
    version = "21.0.6"
    modules = listOf("javafx.controls", "javafx.fxml")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}