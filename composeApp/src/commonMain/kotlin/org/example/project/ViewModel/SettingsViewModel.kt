package org.example.project.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import com.russhwolf.settings.Settings
import org.example.project.SharedPreferences.SettingsRepository

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {


    private val settings = Settings()

    val itemsDificultad = listOf(
        "Fácil",
        "Normal",
        "Difícil"
    )


    private var _nombreUsuario by mutableStateOf(repository.getString("nombre_usuario", ""))

    var nombreUsuarioGuardado: String
        get() = _nombreUsuario
        set(value) {
            _nombreUsuario = value
            repository.saveSettingValue("nombre_usuario", value)
        }

    private var _selectedDificultad by mutableStateOf(repository.getInt("dificultad_index", 1))

    var selectedDificultad: Int
        get() = _selectedDificultad
        set(value) {
            _selectedDificultad = value
            repository.saveSettingValue("dificultad_index", value)
        }

    var gridDificultad by mutableStateOf(calcularGrid(selectedDificultad))

    var switchSettings by mutableStateOf(true)

    fun changeDificultad(index: Int) {
        selectedDificultad = index
        gridDificultad = calcularGrid(index)
    }

    fun restoreSettings() {
        changeDificultad(1)
        switchSettings = true
    }

    private fun calcularGrid(index: Int): Int {
        return when (index) {
            0 -> 2
            1 -> 3
            2 -> 4
            else -> 3
        }
    }
}