package org.example.project.View.Navigation

import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.modules.polymorphic



    val navConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(Route.MainMenu::class, Route.MainMenu.serializer())
                subclass(Route.Game::class, Route.Game.serializer())
                subclass(Route.Score::class, Route.Score.serializer())
                subclass(Route.Settings::class, Route.Settings.serializer())

            }
        }
    }
