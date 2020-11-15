package me.jaskowicz.minecraftdiscordrelay.Commands;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import me.jaskowicz.minecraftdiscordrelay.Utils.CommandExec;
import me.jaskowicz.minecraftdiscordrelay.Utils.SimpleEmbedCreation;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TestChannelsCommand implements CommandExec {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        boolean sentChat = false;
        boolean sentConsole = false;

        // -----------------------------------------------

        SimpleEmbedCreation simpleEmbedCreation = new SimpleEmbedCreation();

        simpleEmbedCreation.setChannelToSendTo(Minecraftdiscordrelay.chatChannelID);
        simpleEmbedCreation.setEmbedTitle("This is a test message.");
        simpleEmbedCreation.setEmbedDescription("This message has been forcefully sent by " + sender.getName() + ".\n\nIf this message can be seen, it means this channel is working as intended.");

        sentChat = simpleEmbedCreation.sendMessage();

        // -----------------------------------------------

        SimpleEmbedCreation simpleEmbedCreation2 = new SimpleEmbedCreation();

        simpleEmbedCreation2.setChannelToSendTo(Minecraftdiscordrelay.consoleChannelID);
        simpleEmbedCreation2.setEmbedTitle("This is a test message.");
        simpleEmbedCreation2.setEmbedDescription("This message has been forcefully sent by " + sender.getName() + ".\n\nIf this message can be seen, it means this channel is working as intended.");

        sentConsole = simpleEmbedCreation2.sendMessage();

        // -----------------------------------------------

        StringBuilder testResult = new StringBuilder();
        testResult.append("Test Results: \n");
        testResult.append(ChatColor.GRAY).append("------------------------------------\n");

        if(sentChat) {
            testResult.append(ChatColor.GREEN).append("Message sent successfully to the Chat Channel.\n");
        } else {
            testResult.append(ChatColor.RED).append("Message failed to send to the Chat Channel.\n");
        }

        if(sentConsole) {
            testResult.append(ChatColor.GREEN).append("Message sent successfully to the Console Channel.\n");
        } else {
            testResult.append(ChatColor.RED).append("Message failed to send to the Console Channel.\n");
        }

        testResult.append(ChatColor.GRAY).append("------------------------------------");

        sender.sendMessage(testResult.toString());

        return true;
    }

    @Override
    public String name() {
        return "testchannels";
    }

    @Override
    public String[] otherNames() {
        return null;
    }
}
