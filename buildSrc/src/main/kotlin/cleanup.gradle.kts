check(rootProject.name == name) {
    "The cleanup plugin should be applied to the root project and not $name"
}

tasks.register("templateCleanup") {
    val repository = System.getenv("GITHUB_REPOSITORY")
        ?: error("No GITHUB_REPOSITORY environment variable. Are you running from Github Actions?")

    val (owner, name) = repository.split("/").let {
        it[0].sanitized() to it[1].sanitized()
    }

    file("settings.gradle.kts").replace(
        "rootProject.name = (\"kotlin-android-template\")",
        "rootProject.name = (\"$name\")"
    )
    file("buildSrc/src/main/java/Coordinates.kt").replace(
        "com.ncorti.kotlin.template",
        "com.github.$owner.$name"
    )
    /**
     * This fails with: 'refusing to allow a GitHub App to create or update workflow
     * `.github/workflows/publish-release.yaml` without `workflows` permission'
     */
    /*
    file(".github/workflows/publish-release.yaml").replace(
        "cortinico/kotlin-android-template",
        "$owner/$name"
    )
    file(".github/workflows/publish-snapshot.yaml").replace(
        "cortinico/kotlin-android-template",
        "$owner/$name"
    )
     */
    file("buildSrc/src/main/kotlin/publish.gradle.kts").apply {
        replace("cortinico/kotlin-android-template", "$owner/$name")
        replace("cortinico/kotlin-android-template", "$owner/$name")
        replace("cortinico", owner)
        replace("Nicola Corti", owner)
        // Keep the link to the original script
        replace(
            "* https://github.com/$owner/$name/blob/master/buildSrc/src/main/kotlin/publish.gradle.kts",
        "* https://github.com/cortinico/kotlin-android-template/blob/master/buildSrc/src/main/kotlin/publish.gradle.kts"
        )
    }

    copyTemplateFiles(repository, name)
    changePackageName(owner, name)

    // cleanup the cleanup :)
    file(".github/template-cleanup").deleteRecursively()
    file(".github/workflows/cleanup.yaml").delete()
    file("build.gradle.kts").replace(
        "    cleanup\n",
        ""
    )
    file("buildSrc/src/main/kotlin/cleanup.gradle.kts").delete()
}

fun String.sanitized() = replace(Regex("[^A-Za-z0-9]"), "").toLowerCase()

fun File.replace(regex: Regex, replacement: String) {
    writeText(readText().replace(regex, replacement))
}

fun File.replace(oldValue: String, newValue: String) {
    writeText(readText().replace(oldValue, newValue))
}

fun copyTemplateFiles(repository: String, name: String) {
    file(".github/template-cleanup")
        .listFiles()!!
        .filter { it.isFile }
        .forEach {
            it.copyTo(target = file(it.name), overwrite = true)
            file(it.name).apply {
                replace("%NAME%", name)
                replace("%REPOSITORY%", repository)
            }
        }
}

fun srcDirectories() = projectDir.listFiles()!!
    .filter { it.isDirectory && !(it.name == "buildSrc") }
    .flatMap { it.listFiles()!!.filter { it.isDirectory && it.name == "src" } }

fun changePackageName(owner: String, name: String) {
    srcDirectories().forEach {
        it.walk().filter {
            it.isFile && (it.extension == "kt" || it.extension == "kts"  || it.extension == "xml")
        }.forEach {
            it.replace("com.ncorti.kotlin.template", "com.github.$owner.$name")
        }
    }
    srcDirectories().forEach {
        it.listFiles()!!.filter { it.isDirectory } // down to src/main
            .flatMap { it.listFiles()!!.filter { it.isDirectory } } // down to src/main/java
            .forEach {
                val newDir = File(it, "com/github/$owner/$name")
                newDir.parentFile.mkdirs()
                File(it, "com/ncorti/kotlin/template").renameTo(newDir)
                File(it, "com/ncorti").deleteRecursively()
            }
    }
}
