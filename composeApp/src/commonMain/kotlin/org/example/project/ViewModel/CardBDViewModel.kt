package org.example.project.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import org.example.project.Card.Card
import org.example.project.Card.Repository

class CardBDViewModel : ViewModel() {
    private val repository = Repository()

    private val _allCards = MutableStateFlow<List<Card>>(emptyList())
    val allCards: StateFlow<List<Card>> = _allCards

    private val _gameCards = MutableStateFlow<List<Card>>(emptyList())
    val gameCards: StateFlow<List<Card>> = _gameCards

    init {
        cargarCards()
    }

    private fun cargarCards() {
        viewModelScope.launch {
            try {
                _allCards.value = repository.obtenerCard()
            } catch (e: Exception) { /* Error */ }
        }
    }


    fun prepararEscenario(columnas: Int) {
        val filas = 3
        val totalHuecos = columnas * filas

        val cantidadCartasUnicas = totalHuecos / 2

        val todas = _allCards.value
        if (todas.isEmpty()) return

        val seleccionadas = todas.shuffled().take(cantidadCartasUnicas)

        val listaParejas = (seleccionadas + seleccionadas).shuffled()

        _gameCards.value = listaParejas.mapIndexed { index, card ->
            card.copy(id = index.toLong())
        }
    }

    fun obtenerMatriz(columnas: Int): List<List<Card>> {
        return _gameCards.value.chunked(columnas)
    }
}
