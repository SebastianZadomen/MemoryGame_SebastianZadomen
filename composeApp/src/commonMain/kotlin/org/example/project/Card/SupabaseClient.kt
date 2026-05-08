package org.example.project.Card
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://tibceikovebgferyemkz.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRpYmNlaWtvdmViZ2ZlcnllbWt6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzYyMTI3NDUsImV4cCI6MjA5MTc4ODc0NX0.hhQQS7h-NPWIcUoMHSHGFvVStb7eJPvtfw2nhWIGYSY"
    ) {
        install(Postgrest)
    }
}