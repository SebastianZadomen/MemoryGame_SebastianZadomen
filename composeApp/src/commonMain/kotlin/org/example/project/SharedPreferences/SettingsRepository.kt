package org.example.project.SharedPreferences
import com.russhwolf.settings.Settings


open class SettingsRepository() {
    private val settings = Settings()

    open fun <T> saveSettingValue(key: String, value: T) {
        when (value) {
            is String -> settings.putString(key, value)
            is Int -> settings.putInt(key, value)
            is Boolean -> settings.putBoolean(key, value)
            is Float -> settings.putFloat(key, value)
            is Long -> settings.putLong(key, value)
            else -> throw IllegalArgumentException("Tipo no soportado")
        }
    }

    open fun getString(key: String, defaultValue: String): String = settings.getString(key, defaultValue)
    open fun getInt(key: String, defaultValue: Int): Int = settings.getInt(key, defaultValue)
}