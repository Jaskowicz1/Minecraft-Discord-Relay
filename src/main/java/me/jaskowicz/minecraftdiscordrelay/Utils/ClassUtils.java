package me.jaskowicz.minecraftdiscordrelay.Utils;

import com.google.common.reflect.ClassPath;
import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import org.bukkit.Bukkit;

import java.io.IOException;

public class ClassUtils {

    public static void registerAllCommands() throws IOException {

        ClassPath cp = ClassPath.from(ClassUtils.class.getClassLoader());
        cp.getTopLevelClassesRecursive("me.jaskowicz.minecraftdiscordrelay.Commands").forEach(classInfo -> {
            try {
                Class c = Class.forName(classInfo.getName());
                Object obj = c.newInstance();
                if(obj instanceof CommandExec) {
                    CommandExec commandExecutor = (CommandExec) obj;
                    Minecraftdiscordrelay.getPlugin(Minecraftdiscordrelay.class).getCommand(commandExecutor.name()).setExecutor(commandExecutor);
                    if(commandExecutor.otherNames() != null) {
                        for (String name : commandExecutor.otherNames()) {
                            Minecraftdiscordrelay.getPlugin(Minecraftdiscordrelay.class).getCommand(name).setExecutor(commandExecutor);
                        }
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }
}
