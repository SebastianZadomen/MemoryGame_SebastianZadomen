package org.example.project.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel()  {

    val itemsDificultad = listOf(
        "Facil",
        "Normal",
        "Dificil"
    )
    var selectedDificultad by mutableStateOf(0)


    var showDificultad by mutableStateOf(false)
        private set

    fun modifyShowDificultad(){
        showDificultad = !showDificultad
    }

    var switchSettings by mutableStateOf(true)

    var gridDificultad by mutableStateOf(if(selectedDificultad==0) 2 else if(selectedDificultad == 1) 3 else 4)


    fun restoreSettings() {
        selectedDificultad = 0
        switchSettings = true
    }

}
