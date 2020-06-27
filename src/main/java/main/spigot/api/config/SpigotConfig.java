package main.spigot.api.config;

import lombok.Getter;
import main.lib.config.Config;
import main.lib.config.section.ConfigSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public abstract class SpigotConfig extends Config {
    @Getter private YamlConfiguration yaml;
    public SpigotConfig(String name) {
        super(name);
    }
    public void reload() {
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
    }
    public void save() {
        try {
            yaml.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
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
