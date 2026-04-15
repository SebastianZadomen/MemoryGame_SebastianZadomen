package org.example.project.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Pantalla1ViewModel: ViewModel() {
    var showMessage by mutableStateOf(false)
        private set

    fun modifyShowMessage(){
        showMessage = !showMessage
    }
}
