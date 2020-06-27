package main.data.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.lib.config.section.ConfigSection;

@AllArgsConstructor
public class MongoLogin extends ConfigSection {
    @Key(name="ip") @Getter private String ip;
    @Key(name="port") @Getter private int port;
    @Key(name="password") @Getter private String password;
}
