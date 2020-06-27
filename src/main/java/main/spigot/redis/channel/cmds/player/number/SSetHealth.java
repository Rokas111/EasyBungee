package main.spigot.redis.channel.cmds.player.number;

import main.data.redis.channel.references.spigot.player.SetHealth;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;

public class SSetHealth extends SetHealth {
    @Override
    public Boolean run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null || !NumberUtils.isNumber(args[1])) return false;
        Bukkit.getPlayer(args[0]).setHealth(Double.parseDouble(args[1]));
        return true;
    }
}
