# Gradle Kotlin userscript plugin

The goal of this Gradle plugin is to be able to create
[userscripts](https://en.wikipedia.org/wiki/Wikipedia:User_scripts) in Kotlin/JS.

## Usage

See [an example repo](https://github.com/krzema12/kotlin-userscript-template) or follow the steps below.

In your `build.gradle.kts`, add this plugin:

```
plugins {
    id("it.krzeminski.kotlin-userscript") version "0.1.0"
}
```

Use Gradle DSL to configure the generated userscript, for example:

```
userscript {
    name = "Kotlin userscript test"
    match("https://www.example.com/*", "http://*.example.com/*")
}
```

Version is inferred from Gradle module's version. Other supported properties: `description`. A detailed documentation of
supported properties will follow.

The output is then generated using `generateUserscript` Gradle task and is placed in
`build/userscript/some-name.user.js`. It's ready to be installed in the browser using e.g. Tampermonkey plugin.
