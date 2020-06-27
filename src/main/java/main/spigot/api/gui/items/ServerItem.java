package main.spigot.api.gui.items;

import api.spigot.SpigotServer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.spigot.api.gui.item.IItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class ServerItem implements IItem {
    @NonNull private SpigotServer server;
    public ItemStack toItem() {
        ItemStack item = new ItemStack(server.isOnline()? Material.EMERALD_BLOCK: Material.IRON_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(server.isOnline()?(ChatColor.GREEN + server.getName()):(ChatColor.RED + server.getName()));
        List<String> lore = new LinkedList<>(Arrays.asList(ChatColor.GRAY + "Status: " + (server.isOnline()? ChatColor.GREEN + "Online": ChatColor.RED + "Offline")));
        if (server.isOnline()) lore.add(ChatColor.GRAY + "Players: " + ChatColor.GREEN + server.getPlayers().size());
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
