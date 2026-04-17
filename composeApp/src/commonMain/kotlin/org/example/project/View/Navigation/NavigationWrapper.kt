package org.example.project.View.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.example.project.View.Screen.MainMenu
import org.example.project.View.Screen.Game
import org.example.project.View.Screen.Score
import org.example.project.View.Screen.Settings

@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(navConfig, Route.MainMenu)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.MainMenu> {
                MainMenu(
                    navigateTo2 = { backStack.add(Route.Game) },
                    navigateTo3 = { backStack.add(Route.Score(userId = "user_42")) },
                    navigateTo4 = { backStack.add(Route.Settings) }
                )
            }
            entry<Route.Game> {
                Game(navigateBack = { backStack.removeLastOrNull() })
            }
            entry<Route.Score> { key ->
                Score(userId = key.userId, navigateBack = { backStack.removeLastOrNull() })
            }
            entry<Route.Settings> {
                Settings(navigateBack = { backStack.removeLastOrNull() })
            }

        }
    )
}



