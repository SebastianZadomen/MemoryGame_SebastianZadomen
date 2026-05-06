package org.example.project.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.Card.Card
import org.example.project.Card.Repository



class CardBDViewModel : ViewModel() {

    private val repository = Repository()

    private val _cards = MutableStateFlow<List<Card>>(emptyList())

    val cards: StateFlow<List<Card>> = _cards

    init {
        cargarCards()
    }

    private fun cargarCards() {
        viewModelScope.launch {
            try {
                _cards.value = repository.obtenerCard()
            } catch (e: Exception) {
               //Log.e("SUPABASE_ERROR", "Error al cargar marcadores: ${e.message}")
            }
        }
    }
}