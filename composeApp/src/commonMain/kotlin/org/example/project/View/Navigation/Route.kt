package org.example.project.View.Navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed class Route: NavKey {
    @Serializable
    data object Screen1 : Route()
    @Serializable
    data object Screen2 : Route()
    @Serializable
    data class Screen3(val userId: String) : Route()
}
