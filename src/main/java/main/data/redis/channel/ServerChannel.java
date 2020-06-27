package main.data.redis.channel;

import main.EasyBungee;
import main.data.redis.channel.message.MessageType;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.message.callbacks.MessageCallBack;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.HashMap;
import java.util.UUID;

public abstract class ServerChannel implements IServerChannel {
    private final HashMap<UUID, MessageCallBack> responses = new HashMap<>();
    private static final String CHANNEL_NAME = "EasyBungee";
    public ServerChannel() {
    }
    public void message() {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
            }
            jedis.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    if (!channel.equals(CHANNEL_NAME)) {
                        return;
                    }
                    ServerMessage smessage = new ServerMessage(message);
                    if (!smessage.getServer().equals(EasyBungee.name)) return;
                    if (smessage.getType() == MessageType.RESPONSE) {
                        if (responses.containsKey(smessage.getId())) {
                            responses.get(smessage.getId()).complete(smessage.getValue());
                            responses.remove(smessage.getId());
                        }
                        return;
                    }
                    smessage.runCommand();
                }
            },CHANNEL_NAME);
        }
    }
    public void publish(ServerMessage message) {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
            }
            jedis.publish(CHANNEL_NAME,message.toRawString());
        }
    }
    public void addResponse(UUID id,MessageCallBack callBack) {
        responses.put(id, callBack);
    }
}
