package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class OnDeathEvent implements Listener {

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {
        if(!Minecraftdiscordrelay.advancedConsole) {
            Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                    .sendMessage(":skull_crossbones: " + event.getDeathMessage()).queue();

        }
    }
}
