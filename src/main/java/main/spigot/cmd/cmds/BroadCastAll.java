package main.spigot.cmd.cmds;

import api.server.BungeeServer;
import api.spigot.SpigotServer;
import main.EasyBungee;
import main.spigot.api.cmd.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BroadCastAll extends Command {
    public BroadCastAll() {
        super("EasyBungee.BroadCastAll", "Broadcasts a message", Arrays.asList("[Message]"), false, "broadcastall");
    }
    public void runConsole(String enteredCmd, String[] args) {
        new BungeeServer().getServers().forEach(server -> server.broadcast(ChatColor.translateAlternateColorCodes('&',String.join(" ",args))).send());
    }

    @Override
    public void run(Player p, String enteredCmd, String[] args) {
        if (EasyBungee.redisConnection == null)  {p.sendMessage(ChatColor.RED + "All " + ChatColor.DARK_RED + "Redis " + ChatColor.RED+"features are unavailable");return;}
        if (args == null || args.length < 1) {p.sendMessage(ChatColor.RED + "Wrong command usage. Please use this format /broadcastall [Message]");return;}
        new BungeeServer().getServers().stream().filter(SpigotServer::isOnline).forEach(server -> server.broadcast(ChatColor.translateAlternateColorCodes('&',String.join(" ",args))).send());
    }
}
