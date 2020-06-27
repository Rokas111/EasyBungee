package main.bungee.api.config.section;

import main.bungee.api.config.BungeeConfig;
import main.lib.config.section.ConfigSection;

import java.util.HashMap;

public abstract class SectionConfig<T extends ConfigSection> extends BungeeConfig implements ISectionConfig<T> {
    private Class<T> clz;
    public SectionConfig(String name,Class<T> clz) {
        super(name);
        this.clz = clz;
    }
    public ConfigSection getSection() {
        HashMap<String,Object> keys = new HashMap<>();
        getYaml().getKeys().forEach(key -> keys.put(key,getYaml().get(key)));
        return new ConfigSection(keys);
    }
    public ConfigSection getDefaultSection() {
        return getConfigSection();
    }
}
