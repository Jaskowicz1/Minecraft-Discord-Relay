# Minecraft-Discord-Relay
Minecraft-Discord-Relay (MCDR) is a plugin for Minecraft (spigot) which allows you to connect your server to discord (via discord bot) and relay information from Minecraft to Discord.

Tested minecraft version: 1.13 - 1.16

Versions it may work for: 1.7.x - 1.16.x
(If these version are inaccurate, inform me and I will do my best to either add a dependency for that version or change the message above depending on how many people want it for x version).


---


# Requirements

- Spigot/PaperSpigot server.
- A discord bot with a valid token.
- one channel for console messages
- one channel for chat messages

(chat and console messages can be the same but may cause conflict).


---


# Installation

Download the latest .jar from the releases tab or head on over to the spigot page: https://www.spigotmc.org/threads/minecraft-discord-relay-mcdr.415845/.

Once downloaded, place it within your plugins folder located within your server's directory (folder) and run the server. You'll get an error stating that it failed to load, this means that it has created its config file. Go into the config file and edit the values.

Make sure your discord bot is in your server and has permissions to see and chat in the channels (ID form) you gave it.

Once those values are edited correctly, save the config and run the server. If everything is setup correctly, you should see the bot come online and start talking!


---


# Usage

When everything is setup, you can talk in the chat channel you specified to talk in-game via discord.
You can also talk in the console channel you specified to execute commands in the console via discord.


---


# Licence


<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.
