package org.example.project.View.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.example.project.View.Screen.MainMenu
import org.example.project.View.Screen.Screen2
import org.example.project.View.Screen.Screen3

@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(navConfig, Route.MainMenu)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.MainMenu> {
                MainMenu(
                    navigateTo2 = { backStack.add(Route.Screen2) },
                    navigateTo3 = { backStack.add(Route.Screen3(userId = "user_42")) }
                )
            }
            entry<Route.Screen2> {
                Screen2(navigateBack = { backStack.removeLastOrNull() })
            }
            entry<Route.Screen3> { key ->
                Screen3(userId = key.userId, navigateBack = { backStack.removeLastOrNull() })
            }

        }
    )
}


