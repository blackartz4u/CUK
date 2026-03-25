import org.apache.tools.ant.taskdefs.optional.jlink.jlink

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
    id("application")
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
tasks.register<Jar>("fatJar") {
    archiveClassifier.set("all")
    manifest {
        attributes["Main-Class"] = "project.cuk.Launcher" // Update this line!
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}