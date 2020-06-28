package api.player;

import api.spigot.SpigotServer;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.EasyBungee;
import main.data.exception.ConnectionException;
import main.data.exception.ProxyException;
import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.UUID;

public class BOfflinePlayer {
    @Getter private UUID id;
    private String name;
    public BOfflinePlayer(UUID id) {
        this.id = id;
        if (EasyBungee.redisConnection == null) return;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            this.name = jedis.hget("uuids",id.toString());
        }
    }
    public BOfflinePlayer(String name) {
        if (EasyBungee.redisConnection == null) return;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            this.name = name;
            this.id = UUID.fromString(jedis.hgetAll("uuids").keySet().stream().filter(id -> jedis.hgetAll("uuids").get(id).equals(name)).findFirst().orElse(null));
        }
    }
    /**
     * Used to see if the player is online
     *
     * @author Rokaz
     */
    public boolean isOnline() {
        if (EasyBungee.redisConnection == null) return false;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return jedis.hexists("players",id.toString());
        }
    }
    /**
     * Used to get the last played server of the player
     *
     * @author Rokaz
     */
    public SpigotServer getLastServerOnline() {
        if (EasyBungee.redisConnection == null || EasyBungee.mySQLConnection == null) return null;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return new SpigotServer(jedis.hget("profile." + id.toString(),"lastServer"));
        }
    }
    /**
     * Used to get the last time online of the player
     *
     * @author Rokaz
     */
    public Date getLastOnline() {
        if (EasyBungee.redisConnection == null || EasyBungee.mySQLConnection == null) return null;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return new Date(Long.parseLong(jedis.hget("profile." + id.toString(),"lastOnline")));
        }
    }
    /**
     * Used to get the join date of the player
     *
     * @author Rokaz
     */
    public Date getJoinDate() {
        if (EasyBungee.redisConnection == null || EasyBungee.mySQLConnection == null) return null;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return new Date(Long.parseLong(jedis.hget("profile." + id.toString(),"joinDate")));
        }
    }
    /**
     * Used to get the play time of the player
     *
     * @author Rokaz
     */
    public int getPlayTime() {
        if (EasyBungee.redisConnection == null || EasyBungee.mySQLConnection == null) return 0;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return Integer.parseInt(jedis.hget("profile." + id.toString(),"time"));
        }
    }
    public String getName() {
        return name;
    }


}
