package main.spigot.redis.channel.cmds.player.number;

import main.data.redis.channel.references.spigot.player.GetExpLevel;
import org.bukkit.Bukkit;

public class SGetExpLevel extends GetExpLevel {
    @Override
    public Integer run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return 0;
        return Bukkit.getPlayer(args[0]).getLevel();
    }
}
