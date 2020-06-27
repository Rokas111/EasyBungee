package main.spigot.redis.channel.cmds.player.misc;

import main.data.redis.channel.references.spigot.player.HasPermission;
import org.bukkit.Bukkit;

public class SHasPermission extends HasPermission {
    @Override
    public Boolean run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return false;
        return Bukkit.getPlayer(args[0]).hasPermission(args[1]);
    }
}
