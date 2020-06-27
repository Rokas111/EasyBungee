package main.spigot.redis.channel.cmds.player.item;

import api.spigot.world.item.SpigotItem;
import api.spigot.world.lists.SpigotInventory;
import main.data.redis.channel.references.spigot.player.GetPlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SGetPlayerInventory extends GetPlayerInventory {
    @Override
    public SpigotInventory run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return null;
        HashMap<Integer,SpigotItem> items = new HashMap<>();
        for (int i = 0; i < Bukkit.getPlayer(args[0]).getInventory().getSize();i++) {
            HashMap<String,Integer> enchants = new HashMap<>();
            if (Bukkit.getPlayer(args[0]).getInventory().getItem(i) != null&&Bukkit.getPlayer(args[0]).getInventory().getItem(i).hasItemMeta() && Bukkit.getPlayer(args[0]).getInventory().getItem(i).getItemMeta().hasEnchants()) {
                for (Enchantment enchantment : Bukkit.getPlayer(args[0]).getInventory().getItem(i).getEnchantments().keySet()) {
                    enchants.put(enchantment.getName(),Bukkit.getPlayer(args[0]).getInventory().getItem(i).getEnchantments().get(enchantment));
                }
            }
            ItemStack item = Bukkit.getPlayer(args[0]).getInventory().getItem(i);
            items.put(i,item != null?new SpigotItem(item.getType().name()
                    ,item.getAmount()
                    ,item.hasItemMeta() && item.getItemMeta().hasDisplayName()?item.getItemMeta().getDisplayName():""
                    ,item.getDurability()
                    ,item.hasItemMeta() && item.getItemMeta().hasLore()?item.getItemMeta().getLore():new ArrayList<>()
                    ,enchants):new SpigotItem("AIR",1,"",(short)0,new ArrayList<>(),new HashMap<>()));
        }
        return new SpigotInventory("PlayerInventory",items);
    }
}
