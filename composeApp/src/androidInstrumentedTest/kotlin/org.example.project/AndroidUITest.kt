package org.example.project.ui
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.example.project.View.Screen.MainMenu
import org.example.project.View.Screen.Settings
import org.junit.Rule
import org.junit.Test

class AndroidUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingTitleShouldShowPlayButton() {
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        org.example.project.Music.MusicInitializer.init(context)
        composeTestRule.setContent {
            MainMenu(
                navigateTo2 = {},
                navigateTo3 = {},
                navigateTo4 = {}
            )
        }

        composeTestRule
            .onNodeWithTag("boton_jugar")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag("titulo_principal")
            .performClick()

        composeTestRule
            .onNodeWithTag("boton_jugar")
            .assertIsDisplayed()
    }

    @Test
    fun verifyTutorialDialogAppears() {
        composeTestRule.setContent { Settings(navigateBack = {}) }

        composeTestRule.onNodeWithTag("dialogo_ayuda").assertDoesNotExist()

        composeTestRule.onNodeWithTag("boton_ver_reglas").performClick()

        composeTestRule.onNodeWithTag("dialogo_ayuda").assertIsDisplayed()

        composeTestRule.onNodeWithTag("titulo_ayuda").assertIsDisplayed()
    }

    @Test
    fun testMusicSwitchToggle() {
        composeTestRule.setContent {
            Settings(navigateBack = {})
        }

        composeTestRule.onNodeWithTag("switch_musica").assertIsOn()

        composeTestRule.onNodeWithTag("switch_musica").performClick()

        composeTestRule.onNodeWithTag("switch_musica").assertIsOff()
    }

    @Test
    fun testOpenAndCloseRules() {
        composeTestRule.setContent {
            Settings(navigateBack = {})
        }

        composeTestRule.onNodeWithTag("dialogo_ayuda").assertDoesNotExist()

        composeTestRule.onNodeWithTag("boton_ver_reglas").performClick()

        composeTestRule.onNodeWithTag("dialogo_ayuda").assertIsDisplayed()

        composeTestRule.onNodeWithTag("boton_entendido").performClick()

        composeTestRule.onNodeWithTag("dialogo_ayuda").assertDoesNotExist()
    }
}