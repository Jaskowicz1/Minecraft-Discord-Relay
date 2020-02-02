package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Objects;

public class CommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        if (StringUtils.containsIgnoreCase("/stop", event.getMessage())) {
            Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                    .sendMessage(":x: Server closing.").queue();
        }
    }
}
