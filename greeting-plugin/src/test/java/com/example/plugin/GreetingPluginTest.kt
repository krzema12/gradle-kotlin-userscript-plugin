package com.example.plugin

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Test

class GreetingPluginTest {
    @Test
    fun pluginRegistersATask() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("kotlin-userscript")

        // Verify the result
        Assert.assertNotNull(project.tasks.findByName("generateUserscript"))
    }
}
