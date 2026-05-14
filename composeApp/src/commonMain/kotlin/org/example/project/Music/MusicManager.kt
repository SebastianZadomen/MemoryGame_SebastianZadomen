package org.example.project.Music

import korlibs.audio.sound.Sound
import korlibs.audio.sound.SoundChannel
import korlibs.audio.sound.playing
import korlibs.audio.sound.readMusic
import korlibs.io.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
expect fun configurePlatformContext(context: Any)

object MusicManager {
    private var music: Sound? = null
    private var channel: SoundChannel? = null
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun initContext(context: Any) {
        configurePlatformContext(context)
    }

    fun toggleMusic(play: Boolean) {
        scope.launch {
            providePlatformContext {
                if (play) {
                    if (music == null) {
                        runCatching {
                            music = resourcesVfs["files/lolmusic.mp3"].readMusic()
                        }.onFailure {
                            println("ERROR: No se encontró lolmusic.mp3 en ninguna plataforma")
                            it.printStackTrace()
                        }
                    }

                    if (music != null && (channel == null || channel?.playing == false)) {
                        channel = music?.playForever()
                    }
                } else {
                    channel?.stop()
                    channel = null
                }
            }
        }
    }
}