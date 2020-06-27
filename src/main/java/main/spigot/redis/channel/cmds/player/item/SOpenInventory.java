package main.spigot.redis.channel.cmds.player.item;

import api.spigot.world.lists.SpigotInventory;
import main.data.redis.channel.references.spigot.player.OpenInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SOpenInventory extends OpenInventory {
    @Override
    public Boolean run(String[] args) {
        SpigotInventory inventory = new SpigotInventory(args[1]);
        if (Bukkit.getPlayer(args[0]) == null) return false;
        Inventory inv = Bukkit.createInventory(null,inventory.getSlots().size(),inventory.getTitle());
        inventory.getSlots().forEach((slot,item) -> {
            inv.setItem(slot,item.getDurability() != 0?new ItemStack(Material.valueOf(item.getMaterial()),item.getAmount(),item.getDurability()):new ItemStack(Material.valueOf(item.getMaterial()),item.getAmount()));
        });
        Bukkit.getPlayer(args[0]).openInventory(inv);
        return true;
    }
}
