package main.spigot.redis.channel.cmds.message;

import main.data.redis.channel.references.spigot.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Arrays;

public class SBroadcast extends Broadcast {
    @Override
    public Boolean run(String[] args) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',args.length > 1?String.join(" ",args):args[0]));
        return true;
    }
}
