package main.spigot.redis.channel.cmds.player.number;

import main.data.redis.channel.references.spigot.player.SetExpLevel;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;

public class SSetExpLevel extends SetExpLevel {
    @Override
    public Boolean run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null || !NumberUtils.isNumber(args[1])) return false;
        Bukkit.getPlayer(args[0]).setLevel(Integer.parseInt(args[1]));
        return true;
    }

}
