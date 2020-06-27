package main.spigot.api;

import main.lib.manager.Manager;
import main.spigot.SpigotMain;
import main.spigot.api.config.SpigotConfig;
import org.bukkit.event.Listener;

public abstract class SpigotManager extends Manager implements Listener {
    @Override
    public void enable() {
        getConfigs().forEach(config -> SpigotMain.pl.getConfigManager().registerConfig((SpigotConfig) config));
        SpigotMain.pl.getServer().getPluginManager().registerEvents(this,SpigotMain.pl);
        super.enable();
    }
}
