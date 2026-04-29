package org.example.project.Card

import io.github.jan.supabase.postgrest.postgrest

class Respository {
    private val taula = SupabaseClient.client.postgrest["Cartas"]

    suspend fun obtenerCard(): List<Card> {
        return taula.select().decodeList<Card>()
    }

}