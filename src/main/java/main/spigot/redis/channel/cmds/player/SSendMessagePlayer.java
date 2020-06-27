package main.spigot.redis.channel.cmds.player;

import main.data.redis.channel.references.spigot.player.SendMessagePlayer;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SSendMessagePlayer extends SendMessagePlayer {
    @Override
    public Boolean run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return false;
        Bukkit.getPlayer(args[0]).sendMessage(ChatColor.translateAlternateColorCodes('&',String.join(" ", Stream.of(args).collect(Collectors.toList()).subList(1,args.length))));
        return true;
    }
}
