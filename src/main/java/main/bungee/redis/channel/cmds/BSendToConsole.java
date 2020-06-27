package main.bungee.redis.channel.cmds;

import main.bungee.BungeeMain;
import main.data.redis.channel.references.shared.SendToConsole;
import net.md_5.bungee.api.ChatColor;

public class BSendToConsole extends SendToConsole {
    @Override
    public Boolean run(String[] args) {
        BungeeMain.pl.getProxy().getConsole().sendMessage(ChatColor.translateAlternateColorCodes('&',String.join(" ",args)));
        return true;
    }

}
