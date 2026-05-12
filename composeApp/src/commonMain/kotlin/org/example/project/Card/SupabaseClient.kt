package org.example.project.Card
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.client.plugins.HttpTimeout

object SupabaseClient {
    @OptIn(SupabaseInternal::class)
    val client = createSupabaseClient(
        supabaseUrl = "https://tibceikovebgferyemkz.supabase.co",
        supabaseKey = "sb_publishable_ZVZ047eBWvqYy2v32jN7IQ_Zu43-6Ln"
    ) {
        install(Postgrest)
        httpConfig {
            install(HttpTimeout) {
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
                socketTimeoutMillis = 60000
            }
        }
    }
}