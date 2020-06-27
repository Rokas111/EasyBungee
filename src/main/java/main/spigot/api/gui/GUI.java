package main.spigot.api.gui;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@RequiredArgsConstructor
public class GUI {
    @NonNull private String title;
    @NonNull private HashMap<Integer, ItemStack> items;
    public void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null,items.size(),title);
        items.forEach(inv::setItem);
        p.openInventory(inv);
        SpigotMain.pl.getGUIManager().addGUI(p);
    }
}
