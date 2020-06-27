package main.spigot.redis.channel.cmds.player.item;

import api.spigot.world.item.SpigotItem;
import main.data.redis.channel.references.spigot.player.GetPlayerItemInHand;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;

public class SGetPlayerItemInHand extends GetPlayerItemInHand {
    @Override
    public SpigotItem run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return null;
        HashMap<String,Integer> enchants = new HashMap<>();
        if (Bukkit.getPlayer(args[0]).getItemInHand().hasItemMeta() && Bukkit.getPlayer(args[0]).getItemInHand().getItemMeta().hasEnchants()) {
            Bukkit.getPlayer(args[0]).getItemInHand().getEnchantments().keySet().forEach(enchantment -> {
                enchants.put(enchantment.getName(),Bukkit.getPlayer(args[0]).getItemInHand().getEnchantments().get(enchantment));
            });
        }
        return new SpigotItem(Bukkit.getPlayer(args[0]).getItemInHand().getType().name()
                ,Bukkit.getPlayer(args[0]).getItemInHand().getAmount()
                ,Bukkit.getPlayer(args[0]).getItemInHand().hasItemMeta() && Bukkit.getPlayer(args[0]).getItemInHand().getItemMeta().hasDisplayName()?Bukkit.getPlayer(args[0]).getItemInHand().getItemMeta().getDisplayName():""
                ,Bukkit.getPlayer(args[0]).getItemInHand().getDurability()
                ,Bukkit.getPlayer(args[0]).getItemInHand().hasItemMeta() && Bukkit.getPlayer(args[0]).getItemInHand().getItemMeta().hasLore()?Bukkit.getPlayer(args[0]).getItemInHand().getItemMeta().getLore():new ArrayList<>()
                ,enchants);
    }
}
