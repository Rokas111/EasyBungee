package main.spigot.cmd.cmds;

import api.server.BungeeServer;
import api.spigot.SpigotServer;
import api.spigot.player.SpigotPlayer;
import api.spigot.world.SpigotLocation;
import api.spigot.world.SpigotWorld;
import main.EasyBungee;
import main.spigot.SpigotMain;
import main.spigot.api.cmd.Command;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BTeleport extends Command {

    public BTeleport() {
        super("EasyBungee.BTeleport", "Teleports a player to a location", Arrays.asList("<Player> <Server> <World> x y z"), false, "bteleport");
    }
    public void runConsole(String enteredCmd, String[] args) {
    }

    @Override
    public void run(Player p, String enteredCmd, String[] args) {
        if (EasyBungee.redisConnection == null)  {p.sendMessage(ChatColor.RED + "All " + ChatColor.DARK_RED + "Redis " + ChatColor.RED+"features are unavailable");return;}
        if (args == null || args.length < 5) {p.sendMessage(ChatColor.RED + "Wrong command usage. Please use this format /bteleport <Player> <Server> <World> x y z");return;}
        if (new BungeeServer().getServers().stream().noneMatch(server -> server.getName().equals(args.length == 5?args[0]:args[1]) && server.isOnline())) { p.sendMessage(ChatColor.RED + "No such server"); return;}
        if (!NumberUtils.isDigits(args.length == 5?args[2]:args[3]) || !NumberUtils.isDigits(args.length == 5?args[3]:args[4]) || !NumberUtils.isDigits(args.length == 5?args[4]:args[5])) { p.sendMessage(ChatColor.RED + "The coordinates must be numbers"); return;}
        SpigotLocation loc = new SpigotLocation(Double.parseDouble(args.length == 5?args[2]:args[3]), Double.parseDouble(args.length == 5?args[3]:args[4]), Double.parseDouble(args.length == 5?args[4]:args[5]), args.length == 5?new SpigotWorld(args[0], args[1]):new SpigotWorld(args[1], args[2]));
        new SpigotServer(args[0]).getWorlds().send(worlds -> {
            if (worlds.getWorlds().stream().noneMatch(world -> world.getName().equals(args.length == 5?args[1]:args[2]))) {
                p.sendMessage(ChatColor.RED + "No such world");
            } else {
                if (!new SpigotPlayer(p.getName()).getCurrentServer().getName().equals(args.length == 5?args[0]:args[1])) {
                    new SpigotPlayer(p.getName()).moveTo(new SpigotServer(args.length == 5?args[0]:args[1])).send(complete -> {
                        if (complete) {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(SpigotMain.pl,() -> new SpigotPlayer(p.getName()).teleport(loc).send(),15);
                        }
                    });
                } else {
                    new SpigotPlayer(p.getName()).teleport(loc).send();
                }
                p.sendMessage(ChatColor.GREEN + "You have successfully teleported " + (args.length == 5?"to your provided location":args[0] + " to your provided location"));
            }
        });
    }
}
