package main.spigot.cmd.cmds;

import api.server.BungeeServer;
import main.spigot.api.cmd.Command;
import main.spigot.api.gui.item.ItemGUI;
import main.spigot.api.gui.items.ServerItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Servers extends Command {
    public Servers() {
        super("EasyBungee.Servers", "Shows all servers", Arrays.asList(""), false, "servers");
    }
    public void runConsole(String enteredCmd, String[] args) {
    }

    @Override
    public void run(Player p, String enteredCmd, String[] args) {
        new ItemGUI<>(54, new ItemStack(Material.AIR), "Server", new BungeeServer().getServers().stream().map(ServerItem::new).collect(Collectors.toList())).toGUI().openInventory(p);
    }
}
