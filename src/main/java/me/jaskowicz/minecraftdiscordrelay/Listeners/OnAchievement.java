package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import me.jaskowicz.minecraftdiscordrelay.Utils.FormatUtils;
import net.dv8tion.jda.api.JDA;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.Date;
import java.util.Objects;

public class OnAchievement implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAchievementGet(PlayerAdvancementDoneEvent event) {

        Player player = event.getPlayer();
        Advancement advancement = event.getAdvancement();

        String message = ":loudspeaker: " + player.getName() + " just completed **" + advancement.getKey().getKey() + "**!";

        try {
            // A LOT of objects.requireNonNull but oh well.
            Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.chatChannelID))
                    .sendMessage(message).queue();
        } catch (Exception ex) {
            // Make sure that it doesn't show errors when JDA isn't connected.
            if (Minecraftdiscordrelay.jda.getStatus() == JDA.Status.CONNECTED) {
                ex.printStackTrace();
            }
        }
    }
}
