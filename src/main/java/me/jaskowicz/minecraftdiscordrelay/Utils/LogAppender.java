package me.jaskowicz.minecraftdiscordrelay.Utils;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Objects;

public class LogAppender extends AbstractAppender {

    private Plugin plugin = Minecraftdiscordrelay.getPlugin(Minecraftdiscordrelay.class);

    public LogAppender() {
        // Says it's deprecated but oh well.
        super("MinecraftDiscordRelayLog", null, null);
        start();
    }

    @Override
    public void append(LogEvent event) {
        LogEvent log = event.toImmutable();

        String message = "[" + FormatUtils.formatTimeFromDate(new Date(event.getTimeMillis())) + "] [" + event.getLevel().toString() + "] " + log.getMessage().getFormattedMessage();

        // Not using Discord Webhooks for this as this may cause too many issues in terms of messages getting blocked due to spam.
        // DiscordUtils.sendMessage(Minecraftdiscordrelay.discordWebhook, message);

        try {
            // A LOT of objects.requireNonNull but oh well.

            // \" means " but doesn't close the string.
            if(!Minecraftdiscordrelay.serverStarted) {

                if(!Minecraftdiscordrelay.sentStartupMessage) {
                    if (Minecraftdiscordrelay.jda.getStatus() == JDA.Status.CONNECTED) {
                        Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                                .sendMessage(":warning: Server starting...").queue();
                        Minecraftdiscordrelay.sentStartupMessage = true;
                    }
                }

                if (message.contains("Done (") && message.contains(")! For help, type \"help\"")) {
                    Minecraftdiscordrelay.serverStarted = true;
                    Minecraftdiscordrelay.jda.getPresence().setActivity(Activity.playing(Minecraftdiscordrelay.playersOnline + " players | " + Bukkit.getServer().getVersion()));
                    Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                            .sendMessage(":white_check_mark: Server started!").queue();

                }
            }

            if(Minecraftdiscordrelay.serverStarted) {

                if(!Minecraftdiscordrelay.serverClosing) {
                    if(Minecraftdiscordrelay.advancedConsole) {
                        Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                                .sendMessage(message).queue();
                    } else {
                        if (message.contains("Unknown command. Type \"/help\" for help.")) {
                            Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                                    .sendMessage("::warning: Unknown command. Type \"/help\" for help.").queue();

                        }
                    }
                }
            }
        } catch (Exception ex) {
            // Make sure that it doesn't show errors when JDA isn't connected because otherwise we'll get like 5 errors before it properly works.
            if(Minecraftdiscordrelay.jda.getStatus() == JDA.Status.CONNECTED) {
                ex.printStackTrace();
            }
        }
    }
}
