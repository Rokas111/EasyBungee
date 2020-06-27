package main.lib.manager;

import main.lib.config.Config;

import java.util.List;

public interface IManager {
    void onEnable();
    void onDisable();
    List<Manager> getSubManagers();
    List<Config> getConfigs();
}
