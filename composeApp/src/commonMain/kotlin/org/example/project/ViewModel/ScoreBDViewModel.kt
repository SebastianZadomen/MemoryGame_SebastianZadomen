package org.example.project.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.Score.RepositoryScore
import org.example.project.Score.Score



class ScoreBDViewModel : ViewModel(){
    private val repository = RepositoryScore()

    private val _allScore = MutableStateFlow<List<Score>>(emptyList())
    val allScore: StateFlow<List<Score>> = _allScore

    init {
        cargarScore()
    }

    fun cargarScore() {
        viewModelScope.launch {
            try {
                val resultado = repository.obtenerScore()
                _allScore.value = resultado
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun guardarNuevoScore(nombre: String, tiempo: Int, dificultad: String) {
        viewModelScope.launch {
            try {
                val nuevo = Score(name = nombre, time = tiempo, dificultad = dificultad)
                repository.insertarScore(nuevo)
                cargarScore()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}