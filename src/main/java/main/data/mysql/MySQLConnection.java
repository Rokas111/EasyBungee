package main.data.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import main.data.connection.Connection;
import main.data.exception.ConnectionException;
import main.data.login.MySQLLogin;

public class MySQLConnection extends Connection<MySQLLogin> {
    @Getter private HikariDataSource hikari;
    public MySQLConnection(MySQLLogin login) throws ConnectionException {
        super(login);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            HikariConfig jdbcConfig = new HikariConfig();
            jdbcConfig.setPoolName("mysticpool");
            jdbcConfig.setMaximumPoolSize(10);
            jdbcConfig.setMinimumIdle(2);
            jdbcConfig.setJdbcUrl("jdbc:mysql://" + login.getHost() + ":" + login.getPort() + "/" + login.getDatabase());
            jdbcConfig.setUsername(login.getUsername());
            jdbcConfig.setPassword(login.getPassword());
            hikari = new HikariDataSource(jdbcConfig);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectionException();
        }
    }

    public boolean isConnected() {
        return hikari != null;
    }
    public void close() {
        hikari.close();
    }
}
