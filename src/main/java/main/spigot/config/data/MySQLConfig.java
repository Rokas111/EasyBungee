package main.spigot.config.data;

import main.data.login.MySQLLogin;
import main.spigot.api.config.section.SectionConfig;

public class MySQLConfig extends SectionConfig<MySQLLogin> {
    public MySQLConfig() {
        super("mysql",MySQLLogin.class);
    }
    public MySQLLogin getConfigSection() {
        return new MySQLLogin("localhost",3306,"","","");
    }
    public MySQLLogin read() {
        return new MySQLLogin(getSection().getKeys().get("host").toString(),Integer.parseInt(getSection().getKeys().get("port").toString()),getSection().getKeys().get("username").toString(),getSection().getKeys().get("password").toString(),getSection().getKeys().get("database").toString());
    }
}
