package org.example.project.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
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

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val cartasVolteadas = mutableListOf<Card>()
    private var tableroBloqueado = false

    init {
        cargarCards()
    }

    private fun cargarCards() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val resultado = repository.obtenerCard()
                if (resultado.isEmpty()) {
                    _errorMessage.value = "La base de datos se conectó, pero la tabla 'Cartas' está vacía."
                } else {
                    _allCards.value = resultado
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: e.toString()
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun prepararEscenario(columnas: Int) {
        try {
            val filas = 3
            val totalHuecos = columnas * filas
            val cantidadCartasUnicas = totalHuecos / 2

            val todas = _allCards.value
            if (todas.isEmpty()) return

            val cartasAUsar = if (todas.size >= cantidadCartasUnicas) {
                todas.shuffled().take(cantidadCartasUnicas)
            } else {
                todas
            }

            val listaParejas = (cartasAUsar + cartasAUsar).shuffled()

            _gameCards.value = listaParejas.mapIndexed { index, card ->
                card.copy(id = index.toLong(), isFlipped = false, isMatched = false)
            }

            cartasVolteadas.clear()
            tableroBloqueado = false
        } catch (e: Exception) {
            _errorMessage.value = "Error al armar la matriz: ${e.message}"
        }
    }

    fun obtenerMatriz(columnas: Int): List<List<Card>> {
        return _gameCards.value.chunked(columnas)
    }

    fun voltearCarta(cartaSeleccionada: Card) {
        if (tableroBloqueado || cartaSeleccionada.isFlipped || cartaSeleccionada.isMatched) return

        actualizarEstadoCarta(cartaSeleccionada.id, isFlipped = true, isMatched = cartaSeleccionada.isMatched)
        val cartaActualizada = _gameCards.value.find { it.id == cartaSeleccionada.id } ?: return
        cartasVolteadas.add(cartaActualizada)

        if (cartasVolteadas.size == 2) {
            tableroBloqueado = true
            viewModelScope.launch {
                delay(1000)
                val carta1 = cartasVolteadas[0]
                val carta2 = cartasVolteadas[1]

                if (carta1.name == carta2.name) {
                    actualizarEstadoCarta(carta1.id, isFlipped = true, isMatched = true)
                    actualizarEstadoCarta(carta2.id, isFlipped = true, isMatched = true)
                } else {
                    actualizarEstadoCarta(carta1.id, isFlipped = false, isMatched = false)
                    actualizarEstadoCarta(carta2.id, isFlipped = false, isMatched = false)
                }
                cartasVolteadas.clear()
                tableroBloqueado = false
            }
        }
    }

    private fun actualizarEstadoCarta(id: Long?, isFlipped: Boolean, isMatched: Boolean) {
        _gameCards.value = _gameCards.value.map { card ->
            if (card.id == id) {
                card.copy(isFlipped = isFlipped, isMatched = isMatched)
            } else {
                card
            }
        }
    }
}
