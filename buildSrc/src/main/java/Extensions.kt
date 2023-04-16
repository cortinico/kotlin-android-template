fun String.isStableVersion(): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA").any { uppercase().contains(it) }
    return stableKeyword || Regex("^[0-9,.v-]+(-r)?$").matches(this)
}