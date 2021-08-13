package com.baehyeonwoo.sample

import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import org.bukkit.plugin.Plugin

object SampleKommand {
    private fun getInstance(): Plugin {
        return SamplePluginMain.instance
    }
    
    fun sampleKommand() {
        getInstance().kommand {
            register("sample") {
                requires { playerOrNull != null && sender.isOp }
                executes {
                    sender.sendMessage(text().content("Hello World!").build())
                }
            }
        }
    }
}