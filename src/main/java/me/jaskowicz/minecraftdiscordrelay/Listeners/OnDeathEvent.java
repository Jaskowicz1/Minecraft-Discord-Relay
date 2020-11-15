package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class OnDeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        if(!Minecraftdiscordrelay.advancedConsole) {
            Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)
                    .getTextChannelById(Minecraftdiscordrelay.chatChannelID)
                    .sendMessage(":skull_crossbones: " + event.getDeathMessage()).queue();

        }
    }
}
