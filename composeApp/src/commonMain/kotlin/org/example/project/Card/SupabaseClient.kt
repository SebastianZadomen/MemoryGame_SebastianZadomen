package org.example.project.Card
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://tibceikovebgferyemkz.supabase.co",
        supabaseKey = "TU_ANON_KEY"
    ) {
        install(Postgrest)
    }
}