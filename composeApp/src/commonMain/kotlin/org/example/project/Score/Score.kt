package org.example.project.Score

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Score (
    val id: Int? = null,
    @SerialName("name")
    val name: String,

    @SerialName("time")
    val time: Int,

    @SerialName("dificultad")
    val dificultad: String
)


