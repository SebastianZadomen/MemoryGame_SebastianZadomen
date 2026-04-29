package org.example.project.Card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Card (
        val id: Long? = null,
    @SerialName("Name")
        val name: String,
    @SerialName("ImgUrl")
        val Url: String,
    @SerialName("Description")
        val description: String
)

