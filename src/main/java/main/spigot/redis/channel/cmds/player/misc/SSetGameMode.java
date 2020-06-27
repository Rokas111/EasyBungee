package main.spigot.redis.channel.cmds.player.misc;

import main.data.redis.channel.references.spigot.player.SetGameMode;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.stream.Stream;

public class SSetGameMode extends SetGameMode {
    @Override
    public Boolean run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null || Stream.of(GameMode.values()).noneMatch(value -> value.name().equals(args[1]))) return false;
        new BukkitRunnable() {
            public void run() {
                Bukkit.getPlayer(args[0]).setGameMode(GameMode.valueOf(args[1]));
            }
        }.runTask(SpigotMain.pl);
        return true;
    }
}
