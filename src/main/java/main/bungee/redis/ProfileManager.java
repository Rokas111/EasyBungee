package main.bungee.redis;

import api.player.BungeePlayer;
import api.server.BungeeServer;
import main.EasyBungee;
import main.bungee.BungeeMain;
import main.bungee.api.BungeeManager;
import main.bungee.mysql.MySQLManager;
import main.bungee.mysql.MySQLProcessor;
import main.bungee.redis.sections.profile.Profile;
import main.bungee.redis.sections.profile.attribute.defaults.ProfileLastOnline;
import main.bungee.redis.sections.profile.attribute.defaults.ProfileLastServer;
import main.lib.config.Config;
import main.lib.manager.Manager;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ProfileManager extends BungeeManager implements Listener {
    public ProfileManager() {
        super();
        BungeeMain.pl.getProxy().getPluginManager().registerListener(BungeeMain.pl,this);
    }
    @EventHandler
    public void join(final ServerConnectEvent e) {
            if (!Profile.hasProfile(e.getPlayer().getUniqueId())) {
                RedisManager.VALUES.add(new Profile(e.getPlayer().getUniqueId()));
            }
    }
    @EventHandler
    public void leave(final ServerDisconnectEvent e) {
        Profile pf = getProfiles().stream().filter(profile -> profile.getId().equals(e.getPlayer().getUniqueId())).findFirst().orElse(null);
        pf.getValues().stream().filter(value -> value instanceof ProfileLastOnline).findFirst().orElse(null).setValue(Calendar.getInstance().getTimeInMillis());
        pf.getValues().stream().filter(value -> value instanceof ProfileLastServer).findFirst().orElse(null).setValue(new BungeePlayer(e.getPlayer().getUniqueId()).getCurrentServer().getName());
    }
    public void onEnable() {
        BungeeMain.pl.getProxy().getScheduler().schedule(BungeeMain.pl, () -> {
            if (EasyBungee.mySQLConnection != null && MySQLProcessor.contains(Profile.class)) {
                RedisManager.VALUES.addAll(MySQLProcessor.getObjects(Profile.class));
            }
        },1000,TimeUnit.MILLISECONDS);
    }
    public void onDisable() {
        if (MySQLProcessor.contains(Profile.class)) {
            MySQLProcessor.clear(Profile.class);
        }
        getProfiles().forEach(MySQLProcessor::store);
    }
    public List<Profile> getProfiles() {
        return RedisManager.VALUES.stream().filter(value -> value instanceof Profile).map(value -> (Profile) value).collect(Collectors.toList());
    }
    public List<Config> getConfigs() {
        return new ArrayList<>();
    }
    public List<Manager> getSubManagers() {
        return new ArrayList<>();
    }
}
