package main.bungee.api.config.section;

import main.lib.config.section.ConfigSection;

public interface ISectionConfig<T extends ConfigSection>  {
    T getConfigSection();
    T read();
}
