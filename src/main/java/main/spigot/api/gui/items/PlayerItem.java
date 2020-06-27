package main.spigot.api.gui.items;

import api.player.BOfflinePlayer;
import api.player.BungeePlayer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.spigot.api.gui.item.IItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PlayerItem implements IItem {
    @NonNull private BOfflinePlayer player;
    public ItemStack toItem() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        if (player.isOnline()) {
            item.setDurability((short) 3);
            SkullMeta sm = (SkullMeta) item.getItemMeta();
            sm.setOwner(player.getName());
            item.setItemMeta(sm);
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(player.isOnline()?(ChatColor.GREEN + player.getName()):(ChatColor.RED + player.getName()));
        List<String> lore = new ArrayList<>();
        if (player.isOnline()) {
            lore.add(ChatColor.GRAY + "Current Server: " + ChatColor.GREEN + ((BungeePlayer)player).getCurrentServer().getName());
        } else {
            lore.add(ChatColor.GRAY + "Last Server: " + ChatColor.GREEN + player.getLastServerOnline().getName());
            lore.add(ChatColor.GRAY + "Last Online: " + ChatColor.GREEN + new SimpleDateFormat("yyyy-MM-dd").format(player.getLastOnline()));
        }
        lore.add(ChatColor.GRAY + "PlayTime: " + ChatColor.GREEN+(player.getPlayTime() < 3600?(player.getPlayTime() > 60?player.getPlayTime()/60+" min":"1 min"):player.getPlayTime() /3600 + " hours " + (player.getPlayTime() % 3600) / 60 + " min"));
        lore.add(ChatColor.GRAY + "JoinDate: " + ChatColor.GREEN +new SimpleDateFormat("yyyy-MM-dd").format(player.getJoinDate()));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
