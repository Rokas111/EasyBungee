package main.bungee.api.config;

import lombok.Getter;
import main.lib.config.Config;
import main.lib.config.section.ConfigSection;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;

public abstract class BungeeConfig extends Config {
    @Getter private Configuration yaml;
    public BungeeConfig(String name) {
        super(name);
    }
    public void reload() {
        try {
            this.yaml = ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(getYaml(),getFile());
        } catch (IOException e) {
            throw new NullPointerException("Failed saving config");
        }
    }
    public void setSection(ConfigSection section) {
        section.getKeys().forEach((key,value) -> {
            if (value instanceof ConfigSection) addSection(key,(ConfigSection) value); else getYaml().set(key,value);
        });
        save();
    }
    public void addSection(String name,ConfigSection section) {
        section.getKeys().forEach((key,value) -> {
            if (value instanceof ConfigSection) addSection(name + "." + key,(ConfigSection) value); else getYaml().set(name + "." + key,value);
        });
        save();
    }
}
