package main.spigot.redis.channel.cmds.player.misc;

import api.spigot.SpigotSound;
import main.data.redis.channel.references.spigot.player.PlaySoundPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import java.util.stream.Stream;

public class SPlaySoundPlayer extends PlaySoundPlayer {
    @Override
    public Boolean run(String[] args) {
        SpigotSound sound = new SpigotSound(args[1]);
        if (Bukkit.getPlayer(args[0]) == null ||  Stream.of(Sound.values()).noneMatch(value -> value.name().equals(sound.getName()))) return false;
        Bukkit.getPlayer(args[0]).playSound(Bukkit.getPlayer(args[0]).getLocation(),Sound.valueOf(sound.getName()),sound.getVolume(),sound.getPitch());
        return true;
    }
}
