package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnections implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Minecraftdiscordrelay.playersOnline += 1;
        Minecraftdiscordrelay.jda.getPresence().setActivity(Activity.playing(Minecraftdiscordrelay.playersOnline + " players | " + Bukkit.getServer().getVersion()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Minecraftdiscordrelay.playersOnline -= 1;
        Minecraftdiscordrelay.jda.getPresence().setActivity(Activity.playing(Minecraftdiscordrelay.playersOnline + " players | " + Bukkit.getServer().getVersion()));
    }
}
