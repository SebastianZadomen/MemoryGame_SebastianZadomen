package org.example.project.Music

import android.content.Context
import korlibs.io.android.withAndroidContext
import kotlinx.coroutines.runBlocking

private var staticAndroidContext: Context? = null

actual fun configurePlatformContext(context: Any) {
    if (context is Context) {
        staticAndroidContext = context.applicationContext
    }
}

actual suspend fun <T> providePlatformContext(block: suspend () -> T): T {
    return runWithAndroidContext(block)
}

suspend fun <T> runWithAndroidContext(block: suspend () -> T): T {
    val ctx = staticAndroidContext ?: error("Contexto no inicializado. Llama a initContext en MainActivity")
    // Esto inyecta el contexto en la corrutina actual para que Korio lo vea
    return withAndroidContext(ctx) {
        block()
    }
}