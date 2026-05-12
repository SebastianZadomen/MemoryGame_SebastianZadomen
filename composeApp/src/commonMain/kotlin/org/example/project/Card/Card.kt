package org.example.project.Card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Card (
    val id: Long? = null,
    @SerialName("Name")
        val name: String,
    @SerialName("ImgUrl")
        val Url: String,
    @SerialName("Description")
        val description: String,

    @Transient var isFlipped: Boolean = false,
    @Transient var isMatched: Boolean = false

)

