package main.spigot.redis.channel.cmds.player.misc;

import main.data.redis.channel.references.spigot.player.Kill;
import org.bukkit.Bukkit;

public class SKill extends Kill {
    @Override
    public Boolean run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return false;
        Bukkit.getPlayer(args[0]).setHealth(0);
        return true;
    }
}
