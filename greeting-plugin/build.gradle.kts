plugins {
    `kotlin-dsl`
}

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

dependencies {
    // Use JUnit test framework for unit tests
    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    plugins {
        register("kotlin-userscript-plugin") {
            id = "kotlin-userscript"
            implementationClass = "it.krzeminski.gradleplugins.kotlinuserscript.KotlinUserscriptPlugin"
        }
    }
}
