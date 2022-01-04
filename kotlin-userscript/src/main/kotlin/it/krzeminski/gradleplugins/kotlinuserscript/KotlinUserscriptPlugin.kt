package it.krzeminski.gradleplugins.kotlinuserscript

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.* // ktlint-disable no-wildcard-imports
import java.io.File

open class KotlinUserscriptPluginExtension {
    var name: String? = null
    var namespace: String? = null
    var description: String? = null
    var inputFilePath: String? = null
    var outputFilePath: String? = null
    var matchPatterns: List<String> = emptyList()
    var downloadUrl: String? = null
    var updateUrl: String? = null

    fun match(vararg matchPatterns: String) {
        this.matchPatterns = matchPatterns.toList()
    }
}

class KotlinUserscriptPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        val extension = project.extensions.create<KotlinUserscriptPluginExtension>("userscript")
        tasks {
            register("generateUserscript") {
                group = "other"
                description = "Adds a preamble needed by plugins like Tampermonkey, adds a proper file extension, and other."
                dependsOn("browserWebpack")
                doLast {
                    println("Generating a userscript...")
                    val defaultInputFilePath = "build/distributions/${project.name}.js"
                    val defaultOutputFilePath = "build/userscript/${project.name}.user.js"

                    val userscriptPreamble = """
                        |// ==UserScript==
                        |${userscriptProperty("name", extension.name ?: project.name)}
                        |${userscriptProperty("namespace", extension.namespace ?: project.group.toString())}
                        |${userscriptProperty("version", project.version.toString())}
                        |${(extension.description ?: project.description)?.let { userscriptProperty("description", it) }}
                        |${extension.matchPatterns.map { userscriptProperty("match", it) }.joinToString("\n")}
                        |${extension.downloadUrl?.let { userscriptProperty("downloadURL", it) }}
                        |${extension.updateUrl?.let { userscriptProperty("updateURL", it) }}
                        |// ==/UserScript==
                        |
                        |
                    """.trimMargin()

                    val bundledJavascript = File(extension.inputFilePath ?: defaultInputFilePath).readText()
                    with(File(extension.outputFilePath ?: defaultOutputFilePath)) {
                        parentFile.mkdirs()
                        createNewFile()
                        writeText(userscriptPreamble + bundledJavascript)
                    }
                }
            }
        }
    }

    private fun userscriptProperty(name: String, value: String) =
        "// @$name $value"
}
