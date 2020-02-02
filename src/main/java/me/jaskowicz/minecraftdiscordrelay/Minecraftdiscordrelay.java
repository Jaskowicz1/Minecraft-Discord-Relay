package me.jaskowicz.minecraftdiscordrelay;

import me.jaskowicz.minecraftdiscordrelay.Listeners.ChatListener;
import me.jaskowicz.minecraftdiscordrelay.Listeners.MessageListener;
import me.jaskowicz.minecraftdiscordrelay.Listeners.PlayerConnections;
import me.jaskowicz.minecraftdiscordrelay.Utils.LogAppender;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public final class Minecraftdiscordrelay extends JavaPlugin {

    private static final org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();

    public static String discordBotToken = "";
    public static JDA jda;
    public static String guildID = "";
    public static String consoleChannelID = "";
    public static String chatChannelID = "";

    public static boolean serverStarted;
    public static boolean serverClosing;
    public static int playersOnline = 0;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // In-case of reload.
        serverClosing = false;

        getLogger().info("Loading config...");

        if (!this.getDataFolder().exists()) {
            this.getLogger().info("Error: No config.yml found. Creating...");
            this.getDataFolder().mkdir();
            getConfig().options().copyDefaults(true);
            saveConfig();
            this.getLogger().info("config.yml was created successfully!");
            getLogger().info("Plugin will disable as there are no values set for information regarding the bot. Restart the server with correct information.");
            this.setEnabled(false);
        } else {
            discordBotToken = this.getConfig().getString("main.botToken");
            consoleChannelID = this.getConfig().getString("main.consoleChannelID");
            chatChannelID = this.getConfig().getString("main.chatChannelID");
            guildID = this.getConfig().getString("main.guildID");

            getLogger().info("Config loaded successfully!");
        }

        getLogger().info("Config checks finished!");

        getLogger().info("Loading bot...");

        JDABuilder builder = new JDABuilder(AccountType.BOT);

        for(Player player : Bukkit.getOnlinePlayers()) {
            playersOnline += 1;
        }

        builder.setToken(discordBotToken);
        builder.addEventListeners(new MessageListener());
        builder.addEventListeners(new ListenerAdapter() {
            @Override
            public void onReady(@NotNull ReadyEvent event) {
                if(serverStarted) {
                    event.getJDA().getPresence().setActivity(Activity.playing(playersOnline + " players | " + Bukkit.getServer().getVersion()));
                } else {
                    event.getJDA().getPresence().setActivity(Activity.playing("Server starting... | " + Bukkit.getServer().getVersion()));
                }
            }
        });

        try {
            jda = builder.build();
            getLogger().info("Bot loaded successfully!");
        } catch (LoginException e) {
            e.printStackTrace();
            getLogger().info("Bot failed to load. Error is shown in console and plugin has been disabled!");
            this.setEnabled(false);
        }

        getLogger().info("Starting logger listener...");

        LogAppender appender = new LogAppender();
        logger.addAppender(appender);

        getLogger().info("Loaded console logger!");

        getLogger().info("Registering Listeners...");

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnections(), this);

        getLogger().info("Registered Listeners!");

        getLogger().info("Finished loading Minecraft Discord Relay (MCDR)!");



    }

    @Override
    public void onDisable() {

        serverClosing = true;

        //getLogger().info("Disabling Minecraft Discord Relay (MCDR)!");

        jda.getPresence().setActivity(Activity.playing("Server closing... | " + Bukkit.getServer().getVersion()));

        Objects.requireNonNull(Objects.requireNonNull(Minecraftdiscordrelay.jda.getGuildById(Minecraftdiscordrelay.guildID)).getTextChannelById(Minecraftdiscordrelay.consoleChannelID))
                .sendMessage(":x: Server closing.").queue();



        jda.shutdown();

        getLogger().info("Disabled Minecraft Discord Relay (MCDR)!");
    }
}
