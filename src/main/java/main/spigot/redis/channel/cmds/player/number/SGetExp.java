package main.spigot.redis.channel.cmds.player.number;

import main.data.redis.channel.references.spigot.player.GetExp;
import org.bukkit.Bukkit;

public class SGetExp extends GetExp {
    @Override
    public Float run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return 0.0F;
        return Bukkit.getPlayer(args[0]).getExp();
    }
}
