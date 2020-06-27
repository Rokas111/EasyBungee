package main.spigot.redis;

import api.spigot.SpigotServer;
import lombok.Getter;
import main.EasyBungee;
import main.data.exception.ConnectionException;
import main.data.redis.RedisConnection;
import main.data.redis.value.IRedisValue;
import main.lib.config.Config;
import main.lib.manager.Manager;
import main.spigot.SpigotMain;
import main.spigot.api.SpigotManager;
import main.spigot.config.data.RedisConfig;
import main.spigot.redis.channel.SpigotChannel;
import main.spigot.redis.sections.ServerStatusList;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RedisManager extends SpigotManager {
    public static final List<IRedisValue> VALUES = new LinkedList<>(Arrays.asList(new ServerStatusList((SpigotMain.pl.getServer().getIp().isEmpty()?"localhost":SpigotMain.pl.getServer().getIp()) + ":" + SpigotMain.pl.getServer().getPort())));
    @Getter private RedisConfig config;
    private int task;
    public void onEnable() {
        this.config = new RedisConfig();
        try {
            EasyBungee.redisConnection = new RedisConnection(getConfig().read());
            SpigotMain.sendConsole(ChatColor.BLUE + "Successfully connected to "+ ChatColor.DARK_RED + "Redis");
            SpigotMain.pl.getServer().getScheduler().runTaskLater(SpigotMain.pl, () -> {
                EasyBungee.serverChannel = new SpigotChannel();
                SpigotMain.pl.getServer().getScheduler().runTaskAsynchronously(SpigotMain.pl,() -> EasyBungee.serverChannel.message());
            },20);
            this.task = SpigotMain.pl.getServer().getScheduler().scheduleSyncRepeatingTask(SpigotMain.pl, this::update,1,10);
        } catch (ConnectionException e) {
            SpigotMain.sendConsole(ChatColor.BLUE + "Failed to connect to "+ ChatColor.DARK_RED + "Redis" + ChatColor.BLUE + "." + ChatColor.DARK_RED + " Redis " + ChatColor.BLUE + "features"+ChatColor.RED +" unavailable");
        }
    }
    public void onDisable() {
        if (EasyBungee.redisConnection != null) {
            SpigotMain.pl.getServer().getScheduler().cancelTask(task);
            ServerStatusList section = ((ServerStatusList)VALUES.stream().filter(value -> value instanceof ServerStatusList).findFirst().orElse(null));
            section.remove((SpigotMain.pl.getServer().getIp().isEmpty()?"localhost":SpigotMain.pl.getServer().getIp()) + ":" + SpigotMain.pl.getServer().getPort());
            section.update();
        }
    }
    private void update() {
        VALUES.forEach(IRedisValue::update);
    }
    public List<Manager> getSubManagers() {
        return new ArrayList<>();
    }
    public List<Config> getConfigs() {
        return Arrays.asList(config);
    }
}
