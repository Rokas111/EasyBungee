package main.spigot.config.data;

import main.data.login.RedisLogin;
import main.spigot.api.config.section.SectionConfig;

public class RedisConfig extends SectionConfig<RedisLogin> {
    public RedisConfig() {
        super("redis",RedisLogin.class);
    }
    public RedisLogin getConfigSection() {
        return new RedisLogin("localhost",6379,"");
    }
    public RedisLogin read() {
        return new RedisLogin(getSection().getKeys().get("address").toString(),Integer.parseInt(getSection().getKeys().get("port").toString()),getSection().getKeys().get("auth").toString());
    }
}
