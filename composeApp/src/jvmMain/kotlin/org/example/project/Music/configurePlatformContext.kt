package org.example.project.Music

actual fun configurePlatformContext(context: Any) {}
actual suspend fun <T> providePlatformContext(block: suspend () -> T): T = block()