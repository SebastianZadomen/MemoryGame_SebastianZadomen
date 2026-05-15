package org.example.project.ViewModelTest
import org.example.project.Card.Card
import org.example.project.ViewModel.CardBDViewModel
import org.example.project.ViewModel.Screen1ViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertEquals


class ViewModelTest1 {
    @Test
    fun check_that_the_menu_is_displayed() {
        val vm = Screen1ViewModel()

        assertEquals(false, vm.showMessage)

        vm.modifyShowMessage()

        assertEquals(true, vm.showMessage)
    }
}
class ScoreLogicTest {

    @Test
    fun check_best_time_logic() {
        val timeOld = 50
        val timeNew = 30

        val isBetter = timeNew < timeOld

        assertEquals(true, isBetter, "El tiempo de 30 debería ser mejor que el de 50")
    }
}
class CardTest {

    @Test
    fun verify_that_the_matrix_is_correctly_divided() {
        val viewModel = CardBDViewModel()


        val cardsTest = List(6) { i ->
            Card(id = i.toLong(), name = "Carta $i", Url = "", description = "",isFlipped = false, isMatched = false)
        }

        val resultMatriz = cardsTest.chunked(2)

        assertEquals(3, resultMatriz.size)
        assertEquals(2, resultMatriz[0].size)
    }
    @Test
    fun verify_that_the_state_is_loading() {
        val viewModel = CardBDViewModel()
        assertEquals(true, viewModel.isLoading.value)
    }
}
