package main.spigot.redis.channel.cmds.player.misc;

import main.data.redis.channel.references.spigot.player.SetDisplayName;
import org.bukkit.Bukkit;

public class SSetDisplayName extends SetDisplayName {
    @Override
    public Boolean run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return false;
        Bukkit.getPlayer(args[0]).setDisplayName(args[1]);
        return true;
    }
}
