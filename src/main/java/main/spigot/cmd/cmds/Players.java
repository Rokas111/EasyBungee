package main.spigot.cmd.cmds;

import api.server.BungeeServer;
import main.spigot.api.cmd.Command;
import main.spigot.api.gui.item.ItemGUI;
import main.spigot.api.gui.items.PlayerItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Players extends Command {
    public Players() {
        super("EasyBungee.Players", "Shows all player with their details", Arrays.asList(""), false, "players");
    }
    public void runConsole(String enteredCmd, String[] args) {
    }

    @Override
    public void run(Player p, String enteredCmd, String[] args) {
        new ItemGUI<>(54, new ItemStack(Material.AIR), "Players", new BungeeServer().getAllPlayers().stream().map(PlayerItem::new).collect(Collectors.toList())).toGUI().openInventory(p);
    }
}
