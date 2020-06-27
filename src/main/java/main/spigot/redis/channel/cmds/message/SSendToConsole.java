package main.spigot.redis.channel.cmds.message;

import main.data.redis.channel.references.shared.SendToConsole;
import main.spigot.SpigotMain;
import org.bukkit.ChatColor;

public class SSendToConsole extends SendToConsole {
    @Override
    public Boolean run(String[] args) {
        SpigotMain.pl.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',String.join(" ",args)));
        return true;
    }
}
