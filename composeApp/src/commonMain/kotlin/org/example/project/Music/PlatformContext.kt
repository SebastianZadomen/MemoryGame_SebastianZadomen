package org.example.project.Music

expect suspend fun <T> providePlatformContext(block: suspend () -> T): T