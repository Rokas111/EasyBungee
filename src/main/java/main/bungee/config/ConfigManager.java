package main.bungee.config;

import main.EasyBungee;
import main.bungee.api.BungeeManager;
import main.bungee.api.config.BungeeConfig;
import main.lib.config.Config;
import main.lib.manager.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager extends BungeeManager {
    final private List<BungeeConfig> configs = new ArrayList<>();
    public ConfigManager() {
    }
    private void setupDir() {
        File file =new File("plugins//" + EasyBungee.PLUGIN_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    public void registerConfig(BungeeConfig c) {
        configs.add(c);
    }
    public void reloadConfigs() {
        configs.forEach(BungeeConfig::reload);
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
