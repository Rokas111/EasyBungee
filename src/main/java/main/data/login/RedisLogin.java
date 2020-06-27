package main.data.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.lib.config.section.ConfigSection;

@AllArgsConstructor
public class RedisLogin extends ConfigSection {
    @Key(name="address") @Getter private String address;
    @Key(name="port") @Getter private int port;
    @Key(name="auth") @Getter private String auth;
}
