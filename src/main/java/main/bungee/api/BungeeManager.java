package main.bungee.api;

import lombok.NoArgsConstructor;
import main.bungee.BungeeMain;
import main.bungee.api.config.BungeeConfig;
import main.lib.manager.Manager;
import net.md_5.bungee.api.plugin.Listener;

@NoArgsConstructor
public abstract class BungeeManager extends Manager {
    @Override
    public void enable() {
        getConfigs().forEach(config -> BungeeMain.pl.getConfigManager().registerConfig((BungeeConfig) config));
        super.enable();
    }
}
