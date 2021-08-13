package com.github.pybsh.dice

import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.plugin.Plugin
import kotlin.random.Random.Default.nextInt

object DiceKommand {
    private fun getInstance(): Plugin {
        return DiceMain.instance
    }
    
    fun sampleKommand() {
        getInstance().kommand {
            register("dice") { // /dice 명령어
                requires { playerOrNull != null && sender.isOp }
                executes {
                    val num = nextInt(5) +1 // 1 ~ 6 구하기 (+1을 하는 이유는 0부터 시작하기 때문)

                    val task = getInstance().server.scheduler.runTaskTimer(
                        getInstance(), Runnable {
                            for(p in Bukkit.getOnlinePlayers()) {
                                p.sendTitle("${ChatColor.GOLD}${nextInt(5) + 1}", player.name, 0, 50, 0)
                                p.playSound(p.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 70.0F, 1.0F)
                            }
                    }, 0L, 1L)

                    getInstance().server.scheduler.runTaskLater(
                        getInstance(), Runnable {
                            getInstance().server.scheduler.cancelTask(task.taskId)
                            for(p in Bukkit.getOnlinePlayers()) {
                                p.sendTitle("${ChatColor.GOLD}${num}", player.name, 0, 50, 0)
                                p.playSound(p.location, Sound.ENTITY_PLAYER_LEVELUP, 70.0F, 1.0F)
                            }
                    }, 20L)
               }
           }
        }
    }
}