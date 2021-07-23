package it.krzeminski.gradleplugins.kotlinuserscript

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Test

class KotlinUserscriptPluginTest {
    @Test
    fun pluginRegistersATask() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("it.krzeminski.kotlin-userscript")

        // Verify the result
        Assert.assertNotNull(project.tasks.findByName("generateUserscript"))
    }
}
