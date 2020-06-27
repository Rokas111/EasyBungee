package main.lib.config;

import main.EasyBungee;
import main.lib.config.file.FileSection;

import java.io.File;

public abstract class Config implements IConfig {
    private String name;
    private FileSection section;
    public Config(String name) {
        this.name = name;
        this.section = new FileSection(EasyBungee.PLUGIN_NAME);
        boolean setup = setup();
        reload();
        if (!setup) {
            setSection(getDefaultSection());
        }
    }
    public Config(String name,FileSection section) {
        this.name = name;
        this.section = section;
        boolean setup = setup();
        reload();
        if (!setup) {
            setSection(getDefaultSection());
        }
    }
    public File getFile() {
        return new File("plugins//" + EasyBungee.PLUGIN_NAME +"//" + name+".yml");
    }

}
