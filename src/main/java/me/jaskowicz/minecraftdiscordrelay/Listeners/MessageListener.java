package me.jaskowicz.minecraftdiscordrelay.Listeners;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class MessageListener extends ListenerAdapter {

    public Plugin plugin = Minecraftdiscordrelay.getPlugin(Minecraftdiscordrelay.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        //System.out.println(event.getMessage().getContentRaw());

        // Prevent massive loop
        if (event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }

        // Debug line.
        //System.out.println("Hello! I have received a message from " + event.getAuthor().getName() + ": " + event.getMessage().getContentRaw());

        if (event.getChannelType().equals(ChannelType.TEXT)) {
            if(event.getGuild().getId().equals(Minecraftdiscordrelay.guildID)) {
                if(event.getChannel().getId().equals(Minecraftdiscordrelay.consoleChannelID)) {
                    System.out.println(event.getMessage().getContentDisplay());
                    try {

                        if(event.getMessage().getContentRaw().equals("stop")) {
                            Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                                    .sendMessage(":x: Server closing.").queue();
                        } else if(event.getMessage().getContentRaw().equals("help")) {
                            Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                                    .sendMessage(":x: This command is disabled to prevent spam.").queue();

                            return;

                        }

                        boolean success = Bukkit.getScheduler().callSyncMethod( plugin, () -> Bukkit.dispatchCommand( Bukkit.getConsoleSender(), event.getMessage().getContentRaw() ) ).get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if(event.getChannel().getId().equals(Minecraftdiscordrelay.chatChannelID)) {
                    Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Discord" + ChatColor.GRAY + " - " + ChatColor.BLUE + event.getAuthor().getName() + ChatColor.GRAY + "]"
                    + ChatColor.GRAY + ": " + event.getMessage().getContentRaw());
                }
            }
        }
    }
}
