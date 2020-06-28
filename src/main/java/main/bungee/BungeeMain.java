package main.bungee;

import api.server.BungeeServer;
import lombok.Getter;
import main.bungee.config.ConfigManager;
import main.bungee.mysql.MySQLManager;
import main.bungee.redis.RedisManager;
import main.lib.manager.Manager;
import main.lib.manager.ManagerHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import main.EasyBungee;
import org.bstats.bungeecord.Metrics;

public class BungeeMain extends Plugin {
    public static BungeeMain pl;
    @Getter @ManagerHandler private ConfigManager configManager;
    @Getter @ManagerHandler private MySQLManager mysqlManager;
    @Getter @ManagerHandler private RedisManager redisManager;
    public void onEnable() {
        pl = this;
        Metrics m = new Metrics(this,8011);
        EasyBungee.name = new BungeeServer().getName();
        configManager = new ConfigManager();
        mysqlManager = new MySQLManager();
        redisManager = new RedisManager();
        Manager.getManagers(this).forEach(Manager::enable);
    }
    public void onDisable() {
        Manager.getManagers(this).forEach(Manager::disable);
        pl = null;
        EasyBungee.closeAll();
    }
    public static void sendConsole(String text) {
        pl.getProxy().getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&',EasyBungee.CONSOLE_PLUGIN_NAME)+" "+text);
    }
}
