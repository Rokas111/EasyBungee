package api.server;

import api.player.BOfflinePlayer;
import api.player.BungeePlayer;
import api.spigot.SpigotServer;
import lombok.NoArgsConstructor;
import main.EasyBungee;
import main.data.exception.ConnectionException;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.message.sender.MessageSender;
import main.data.redis.channel.references.shared.SendToConsole;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
public class BungeeServer implements IServer {
    /**
     * Used to get all online players from the bungee network
     *
     * @author Rokaz
     */
    public List<BungeePlayer> getPlayers() {
        if (EasyBungee.redisConnection == null) return new ArrayList<>();
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return jedis.hgetAll("players").keySet().stream().map(id -> {
                    return new BungeePlayer(UUID.fromString(id));
            }).collect(Collectors.toList());
        }
    }
    /**
     * Used to get a player from the bungee network
     *
     * @author Rokaz
     */
    public BungeePlayer getPlayer(String name) {
        return getPlayers().stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
    }
    /**
     * Used to get an offline player from the bungee network
     *
     * @author Rokaz
     */
    public BOfflinePlayer getOfflinePlayer(String name) {
        return getOfflinePlayers().stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
    }
    /**
     * Used to get all offline players from the bungee network
     *
     * @author Rokaz
     */
    public List<BOfflinePlayer> getOfflinePlayers() {
        if (EasyBungee.redisConnection == null) return new ArrayList<>();
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return jedis.hgetAll("uuids").keySet().stream().map(id -> new BOfflinePlayer(UUID.fromString(id))).filter(player -> !player.isOnline()).collect(Collectors.toList());
        }
    }
    /**
     * Used to get all players from the bungee network
     *
     * @author Rokaz
     */
    public List<BOfflinePlayer> getAllPlayers() {
        if (EasyBungee.redisConnection == null) return new ArrayList<>();
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            List<BOfflinePlayer> players = new ArrayList<>();
            players.addAll(getPlayers());
            players.addAll(getOfflinePlayers());
            return players;
        }
    }
    /**
     * Used to send a message to the proxy's console
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> sendToConsole(String message) {
        return new MessageSender<>(new ServerMessage(new SendToConsole(), new BungeeServer().getName(), new String[]{message}),Boolean.class);
    }
    /**
     * Used to get all servers of the bungee network
     *
     * @author Rokaz
     */
    public List<SpigotServer> getServers() {
        if (EasyBungee.redisConnection == null) return new ArrayList<>();
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return jedis.hgetAll("servers").values().stream().map(SpigotServer::new).collect(Collectors.toList());
        }
    }
    public String getName() {
        return "proxy";
    }
}
