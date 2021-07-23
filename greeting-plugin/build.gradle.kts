plugins {
    `kotlin-dsl`
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.15.0"
}

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

group = "it.krzeminski"
version = "0.1.0"

dependencies {
    // Use JUnit test framework for unit tests
    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    plugins {
        register("kotlin-userscript-plugin") {
            id = "it.krzeminski.kotlin-userscript"
            displayName = "Kotlin Userscript"
            description = "Allows creating browser userscripts in Kotlin/JS."
            implementationClass = "it.krzeminski.gradleplugins.kotlinuserscript.KotlinUserscriptPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/krzema12/gradle-kotlin-userscript-plugin"
    vcsUrl = "https://github.com/krzema12/gradle-kotlin-userscript-plugin.git"
    tags = listOf("kotlin", "userscript", "tampermonkey", "greasemonkey")
}
