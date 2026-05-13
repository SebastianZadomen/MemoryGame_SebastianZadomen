package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.View.Navigation.NavigationWrapper

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Memory Game") {
        NavigationWrapper()
    }
}