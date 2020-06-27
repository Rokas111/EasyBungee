package main.bungee.redis;

import lombok.Getter;
import main.EasyBungee;
import main.bungee.BungeeMain;
import main.bungee.api.BungeeManager;
import main.bungee.config.data.RedisConfig;
import main.bungee.redis.channel.BungeeChannel;
import main.bungee.redis.sections.ip.ServerIpSection;
import main.bungee.redis.sections.players.PlayerServerSection;
import main.bungee.redis.sections.profile.Profile;
import main.bungee.redis.sections.uuid.PlayerUUIDSection;
import main.data.exception.ConnectionException;
import main.data.mysql.adapter.MySQLSaveable;
import main.data.redis.RedisConnection;
import main.data.redis.value.IRedisValue;
import main.lib.config.Config;
import main.lib.manager.Manager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisManager extends BungeeManager {
    public static final List<IRedisValue> VALUES = new LinkedList<>(Arrays.asList(new PlayerUUIDSection(),new ServerIpSection(),new PlayerServerSection()));
    @Getter private RedisConfig config;
    private ScheduledTask task;
    public void onEnable() {
        this.config = new RedisConfig();
        try {
            EasyBungee.redisConnection = new RedisConnection(getConfig().read());
            BungeeMain.sendConsole(ChatColor.BLUE + "Successfully connected to "+ ChatColor.DARK_RED + "Redis");
            EasyBungee.serverChannel = new BungeeChannel();
            BungeeMain.pl.getProxy().getScheduler().runAsync(BungeeMain.pl, () -> EasyBungee.serverChannel.message());
            this.task = BungeeMain.pl.getProxy().getScheduler().schedule(BungeeMain.pl, this::update,500,500, TimeUnit.MILLISECONDS);
        } catch (ConnectionException e) {
            BungeeMain.sendConsole(ChatColor.BLUE + "Failed to connect to "+ ChatColor.DARK_RED + "Redis" + ChatColor.BLUE + "." + ChatColor.DARK_RED + " Redis " + ChatColor.BLUE + "features"+ChatColor.RED +" unavailable");
        }
    }
    public void onDisable() {
        if (EasyBungee.redisConnection != null) {
            task.cancel();
            save();
            clear();
        }
    }
    private void clear() {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!this.getConfig().read().getAuth().isEmpty()) {jedis.auth(this.getConfig().read().getAuth());}
            Transaction t = jedis.multi();
            t.flushAll();
            t.exec();
        }
    }
    private void save() {
        VALUES.stream().filter(value -> value instanceof MySQLSaveable && !(value instanceof Profile)).forEach(value -> ((MySQLSaveable) value).save());
    }
    private void update() {
        VALUES.forEach(IRedisValue::update);
    }
    public List<Manager> getSubManagers() {
        return Arrays.asList(new ProfileManager());
    }
    public List<Config> getConfigs() {
        return Arrays.asList(config);
    }
}
