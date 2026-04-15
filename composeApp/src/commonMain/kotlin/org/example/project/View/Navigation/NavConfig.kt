package org.example.project.View.Navigation

import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.modules.polymorphic



    val navConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(Route.Screen1::class, Route.Screen1.serializer())
                subclass(Route.Screen2::class, Route.Screen2.serializer())
                subclass(Route.Screen3::class, Route.Screen3.serializer())
            }
        }
    }
