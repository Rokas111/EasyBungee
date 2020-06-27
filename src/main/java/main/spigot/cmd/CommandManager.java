package main.spigot.cmd;

import main.lib.config.Config;
import main.lib.manager.Manager;
import main.spigot.SpigotMain;
import main.spigot.api.SpigotManager;
import main.spigot.api.cmd.Command;
import main.spigot.api.cmd.ICommand;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandManager extends SpigotManager {
    private final List<Command> commands = new ArrayList<>();
    public void runCommand(Player p, ICommand cmd, String enteredCmd, String[] args) {
        if (!p.getPlayer().hasPermission(cmd.getPermission())) {
            p.sendMessage("");
            return;
        }
        cmd.run(p,enteredCmd, args);
    }
    public void runCommand(ICommand cmd, String enteredCmd, String[] args) {
        cmd.runConsole(enteredCmd, args);
    }
    public void registerCommand(Command cmd) {
        commands.add(cmd);
        try {
            final Field f = SpigotMain.pl.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            ((CommandMap) f.get(SpigotMain.pl.getServer())).register(cmd.getAliases().get(0),cmd);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public List<Command> getCommands() {
        return Collections.unmodifiableList(this.commands);
    }
    public void onEnable() {
    }
    public void onDisable() {
    }
    public List<Manager> getSubManagers() {
        return new ArrayList<>();
    }
    public List<Config> getConfigs() {
        return new ArrayList<>();
    }
}
