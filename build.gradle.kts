group = "project.cuk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

plugins {
    java
    application
    // Use the official JavaFX plugin
    id("org.openjfx.javafxplugin") version "0.1.0"
}

javafx {
    // Set this to your desired JavaFX version (e.g., 17, 21, or 24)
    version = "25.0.2"
    // Specify the modules your project needs
    modules("javafx.controls", "javafx.fxml", "javafx.web")
}
application {
    // Ensure this points to your actual main class
    mainClass.set("project.cuk.Main")
}
tasks.test {
    useJUnitPlatform()
}
tasks.withType<JavaExec> {
    jvmArgs = listOf("--enable-native-access=javafx.web")
}