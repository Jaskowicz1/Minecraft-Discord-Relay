package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerConnections implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Minecraftdiscordrelay.playersOnline += 1;
        Minecraftdiscordrelay.jda.getPresence().setActivity(Activity.playing(Minecraftdiscordrelay.playersOnline + " players | " + Bukkit.getServer().getVersion()));

        if(!Minecraftdiscordrelay.advancedConsole) {

            // Just to make sure this isn't null.
            // Turned out it was the bot API out-of-date.
            // System.out.println(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID));

            Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)
                    .getTextChannelById(Minecraftdiscordrelay.chatChannelID)
                    .sendMessage(":green_circle: " + event.getPlayer().getName() + " joined the game!").queue();

        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Minecraftdiscordrelay.playersOnline -= 1;
        Minecraftdiscordrelay.jda.getPresence().setActivity(Activity.playing(Minecraftdiscordrelay.playersOnline + " players | " + Bukkit.getServer().getVersion()));

        if(!Minecraftdiscordrelay.advancedConsole) {
            Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)
                    .getTextChannelById(Minecraftdiscordrelay.chatChannelID)
                    .sendMessage(":red_circle: " + event.getPlayer().getName() + " left the game!").queue();

        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerKickEvent event) {
        Minecraftdiscordrelay.playersOnline -= 1;
        Minecraftdiscordrelay.jda.getPresence().setActivity(Activity.playing(Minecraftdiscordrelay.playersOnline + " players | " + Bukkit.getServer().getVersion()));

        if(!Minecraftdiscordrelay.advancedConsole) {
            Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)
                    .getTextChannelById(Minecraftdiscordrelay.chatChannelID)
                    .sendMessage(":red_circle: " + event.getPlayer().getName() + " was kicked from the game!").queue();

        }
    }
}
