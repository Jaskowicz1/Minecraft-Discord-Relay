package me.jaskowicz.minecraftdiscordrelay.Utils;

import org.bukkit.command.CommandExecutor;

public interface CommandExec extends CommandExecutor {

    String name();

    String[] otherNames();

}
