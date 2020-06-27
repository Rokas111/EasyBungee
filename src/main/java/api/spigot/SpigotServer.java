package api.spigot;

import api.player.BOfflinePlayer;
import api.player.BungeePlayer;
import api.server.BungeeServer;
import api.server.IServer;
import api.spigot.world.SpigotWorld;
import api.spigot.world.lists.SpigotWorlds;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.EasyBungee;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.message.sender.MessageSender;
import main.data.redis.channel.references.shared.SendToConsole;
import main.data.redis.channel.references.spigot.Broadcast;
import main.data.redis.channel.references.spigot.ExecuteCommand;
import main.data.redis.channel.references.spigot.world.CreateWorld;
import main.data.redis.channel.references.spigot.world.GetWorlds;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SpigotServer implements IServer {
    @NonNull @Getter private String name;
    public SpigotServer(String ip,String port) {
        if (EasyBungee.redisConnection == null) return;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            this.name = jedis.hget("servers",(ip.isEmpty()?"localhost":ip) + ":" + port);
        }
    }
    /**
     * Used to get all players inside the server
     *
     * @author Rokaz
     */
    public List<BungeePlayer> getPlayers() {
        return new BungeeServer().getPlayers().stream().filter(player -> player.getCurrentServer().getName().equals(name)).collect(Collectors.toList());
    }
    /**
     * Used to execute a command in the server
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> executeCommand(String command) {
        return new MessageSender<>(new ServerMessage(new ExecuteCommand(), name, new String[]{command}),Boolean.class);
    }
    /**
     * Used to send a message to the server's console
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> sendToConsole(String message) {
        return new MessageSender<>(new ServerMessage(new SendToConsole(), name, new String[]{message}),Boolean.class);
    }
    /**
     * Used to broadcast a message in the server
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> broadcast(String message) {
        return new MessageSender<>(new ServerMessage(new Broadcast(), name, new String[]{message}),Boolean.class);
    }
    /**
     * Use to get all server's worlds
     *
     * @author Rokaz
     */
    public MessageSender<SpigotWorlds> getWorlds() {
        return new MessageSender<>(new ServerMessage(new GetWorlds(), name, new String[]{name}),SpigotWorlds.class);
    }
    /**
     * Used to create a world in the server
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> createWorld(SpigotWorld world) {
        return new MessageSender<>(new ServerMessage(new CreateWorld(), name, new String[]{world.toString()}),Boolean.class);
    }
    /**
     * Use to get server's ip
     *
     * @author Rokaz
     */
    public String getIp() {
        if (EasyBungee.redisConnection == null) return "";
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return jedis.hgetAll("servers").keySet().stream().filter(server -> jedis.hget("servers",server).equals(name)).findFirst().orElse(null);
        }
    }
    /**
     * Use to check if server is online
     *
     * @author Rokaz
     */
    public boolean isOnline() {
        if (EasyBungee.redisConnection == null) return false;
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());}
            return jedis.exists("serverstatus") && jedis.smembers("serverstatus").contains(getIp());
        }
    }
}
