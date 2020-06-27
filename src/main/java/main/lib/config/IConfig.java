package main.lib.config;

import main.lib.config.section.ConfigSection;

import java.io.File;
import java.io.IOException;

public interface IConfig {
    ConfigSection getDefaultSection();
    void reload();
    File getFile();
    void save();
    void setSection(ConfigSection section);
    void addSection(String name,ConfigSection section);
    default boolean setup() {
        if (!getFile().exists()) {
            try {
                getFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
