package main.spigot.redis.channel.cmds.player.number;

import main.data.redis.channel.references.spigot.player.GetHealth;
import org.bukkit.Bukkit;

public class SGetHealth extends GetHealth {
    @Override
    public Double run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return 0.0;
        return Bukkit.getPlayer(args[0]).getHealth();
    }
}
