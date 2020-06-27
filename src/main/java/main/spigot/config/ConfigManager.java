package main.spigot.config;

import main.EasyBungee;
import main.lib.config.Config;
import main.lib.manager.Manager;
import main.spigot.api.SpigotManager;
import main.spigot.api.config.SpigotConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager extends SpigotManager {
    final private List<SpigotConfig> configs = new ArrayList<>();
    public ConfigManager() {
    }
    private void setupDir() {
        File file =new File("plugins//" + EasyBungee.PLUGIN_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    public void registerConfig(SpigotConfig c) {
        configs.add(c);
    }
    public void reloadConfigs() {
        configs.forEach(SpigotConfig::reload);
    }
    public void onEnable() {
        setupDir();
    }
    public void onDisable() {
    }
    public List<Manager> getSubManagers() {
        return new ArrayList<>();
    }
    public List<Config> getConfigs() {
        return new ArrayList<>();
    }
}
