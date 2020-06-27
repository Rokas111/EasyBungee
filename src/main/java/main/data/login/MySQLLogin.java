package main.data.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.lib.config.section.ConfigSection;

@AllArgsConstructor
public class MySQLLogin extends ConfigSection {
    @Key(name="host") @Getter private String host;
    @Key(name="port") @Getter private int port;
    @Key(name="username") @Getter private String username;
    @Key(name="password") @Getter private String password;
    @Key(name="database") @Getter private String database;
}
