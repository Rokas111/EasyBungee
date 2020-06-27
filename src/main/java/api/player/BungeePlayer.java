package api.player;

import api.server.BungeeServer;
import api.spigot.SpigotServer;
import main.EasyBungee;
import main.data.redis.channel.message.sender.MessageSender;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.references.bungee.Kick;
import main.data.redis.channel.references.bungee.MoveTo;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.stream.Stream;

public class BungeePlayer extends BOfflinePlayer {
    public BungeePlayer(UUID id) {
        super(id);
        if (!isOnline()) throw new NullPointerException("This player isn't online");
    }
    public BungeePlayer(String name) {
        super(name);
        if (!isOnline()) throw new NullPointerException("This player isn't online");
    }
    /**
     * Used to kick the player from the bungee network
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> kick() {
        return new MessageSender<>(new ServerMessage(new Kick(), new BungeeServer().getName(), new String[]{getId().toString()}),Boolean.class);
    }
    /**
     * Used to kick the player from the bungee network
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> kick(String reason){
        return new MessageSender<>(new ServerMessage(new Kick(),new BungeeServer().getName(), !reason.contains(" ")?new String[]{getId().toString(),reason}:Stream.concat(Stream.of(getId().toString()), Stream.of(reason.split(" "))).toArray(String[]::new)),Boolean.class);
    }
    /**
     * Used to move the player to another server
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> moveTo(SpigotServer server) {
        return new MessageSender<>(new ServerMessage(new MoveTo(),new BungeeServer().getName(),new String[]{getId().toString(),server.getName()}),Boolean.class);
    }
    /**
     * Used to get the current server of the player
     *
     * @author Rokaz
     */
    public SpigotServer getCurrentServer() {
        if (EasyBungee.redisConnection == null) return null;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return jedis.hexists("players",getId().toString())?new SpigotServer(jedis.hget("players",getId().toString())):null;
        }
    }
}
