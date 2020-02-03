package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import me.jaskowicz.minecraftdiscordrelay.Utils.FormatUtils;
import net.dv8tion.jda.api.JDA;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Date;
import java.util.Objects;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        String message = ":speech_balloon: [" + FormatUtils.formatTimeFromDate(new Date(System.currentTimeMillis())) + "] " + event.getPlayer().getName() + " » " + event.getMessage();

        try {
            // A LOT of objects.requireNonNull but oh well.
            Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.chatChannelID))
                    .sendMessage(message).queue();
        } catch (Exception ex) {
            // Make sure that it doesn't show errors when JDA isn't connected.
            if(Minecraftdiscordrelay.jda.getStatus() == JDA.Status.CONNECTED) {
                ex.printStackTrace();
            }
        }
    }
}
