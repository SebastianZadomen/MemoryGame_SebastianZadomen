package org.example.project.ViewModelTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.example.project.Card.Card
import org.example.project.SharedPreferences.SettingsRepository
import org.example.project.ViewModel.CardBDViewModel
import org.example.project.ViewModel.Screen1ViewModel
import org.example.project.ViewModel.SettingsViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class ViewModelTest1 {
    @Test
    fun comprobar_que_el_menu_se_despliega() {
        val vm = Screen1ViewModel()

        assertEquals(false, vm.showMessage)

        vm.modifyShowMessage()

        assertEquals(true, vm.showMessage)
    }
}
class ScoreLogicTest {

    @Test
    fun comprobar_logica_de_mejor_tiempo() {
        val tiempoAntiguo = 50
        val tiempoNuevo = 30

        val esMejor = tiempoNuevo < tiempoAntiguo

        assertEquals(true, esMejor, "El tiempo de 30 debería ser mejor que el de 50")
    }
}
class CardTest {

    @Test
    fun verificar_que_la_matriz_se_divide_correctamente_segun_las_columnas() {
        val viewModel = CardBDViewModel()


        val cartasDePrueba = List(6) { i ->
            Card(id = i.toLong(), name = "Carta $i", Url = "", description = "",isFlipped = false, isMatched = false)
        }

        val resultadoMatriz = cartasDePrueba.chunked(2)

        assertEquals(3, resultadoMatriz.size)
        assertEquals(2, resultadoMatriz[0].size)
    }
    @Test
    fun verificar_que_al_iniciar_el_estado_es_cargando() {
        val viewModel = CardBDViewModel()
        assertEquals(true, viewModel.isLoading.value)
    }
}
