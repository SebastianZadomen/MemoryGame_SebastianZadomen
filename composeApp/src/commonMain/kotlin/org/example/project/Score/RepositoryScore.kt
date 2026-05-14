package org.example.project.Score

import io.github.jan.supabase.postgrest.postgrest
import org.example.project.Card.SupabaseClient
import kotlin.text.insert


class RepositoryScore {
    private val taula = SupabaseClient.client.postgrest["Score"]

    suspend fun obtenerScore(): List<Score> {
        return taula.select().decodeList<Score>()
    }

    suspend fun insertarScore(nuevoScore: Score) {
        taula.insert(nuevoScore)
    }


    suspend fun actualizarScore(scoreActualizado: Score) {
        taula.update(scoreActualizado) {
            filter {

                eq("id", scoreActualizado.id!!)
            }
        }
    }
}

