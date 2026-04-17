package org.example.project.View.Navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed class Route: NavKey {
    @Serializable
    data object MainMenu : Route()
    @Serializable
    data object Game : Route()
    @Serializable
    data class Score(val userId: String) : Route()

    @Serializable
    data object Settings : Route()
}
