package main.spigot;

import api.spigot.SpigotServer;
import lombok.Getter;
import main.EasyBungee;
import main.lib.manager.Manager;
import main.spigot.cmd.CommandManager;
import main.spigot.config.ConfigManager;
import main.lib.manager.ManagerHandler;
import main.spigot.redis.RedisManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {
    public static SpigotMain pl;
    @Getter @ManagerHandler private GUIManager GUIManager;
    @Getter @ManagerHandler private CommandManager commandManager;
    @Getter @ManagerHandler private ConfigManager configManager;
    @Getter @ManagerHandler private RedisManager redisManager;
    public void onEnable() {
        pl = this;
        GUIManager = new GUIManager();
        commandManager = new CommandManager();
        configManager = new ConfigManager();
        redisManager = new RedisManager();
        Manager.getManagers(this).forEach(Manager::enable);
        EasyBungee.name = new SpigotServer(SpigotMain.pl.getServer().getIp().isEmpty()?"localhost":SpigotMain.pl.getServer().getIp(), Integer.toString(SpigotMain.pl.getServer().getPort())).getName();
    }
    public void onDisable() {
        Manager.getManagers(this).forEach(Manager::disable);
        pl = null;
        EasyBungee.closeAll();
    }
    public static void sendConsole(String text) {
        pl.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',EasyBungee.CONSOLE_PLUGIN_NAME) + " " + text);
    }
}
