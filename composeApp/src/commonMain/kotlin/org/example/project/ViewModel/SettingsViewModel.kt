package org.example.project.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel()  {

    val itemsDificultad = listOf(
        "Fácil",
        "Normal",
        "Difícil"
    )

    var selectedDificultad by mutableStateOf(1)
    var gridDificultad by mutableStateOf(3)

    var switchSettings by mutableStateOf(true)

    fun changeDificultad(index: Int) {
        selectedDificultad = index
        gridDificultad = when (index) {
            0 -> 2
            1 -> 3
            2 -> 4
            else -> 3
        }
    }

    fun restoreSettings() {
        changeDificultad(1)
        switchSettings = true
    }
}