package main.spigot;

import main.lib.config.Config;
import main.lib.manager.Manager;
import main.spigot.api.SpigotManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;

public class GUIManager extends SpigotManager {
    private List<Player> inGUI = new ArrayList<>();
    public void onEnable() {
    }
    public void onDisable() {
    }
    public List<Manager> getSubManagers() {
        return new ArrayList<>();
    }
    public List<Config> getConfigs() {
        return new ArrayList<>();
    }
    @EventHandler
    public void close(InventoryCloseEvent e) {
        if (inGUI.contains(e.getPlayer())) inGUI.remove(e.getPlayer());
    }
    @EventHandler
    public void click(InventoryClickEvent e) {
        if (inGUI.contains(e.getWhoClicked())) e.setCancelled(true);
    }
    public void addGUI(Player p) {
        inGUI.add(p);
    }
}
