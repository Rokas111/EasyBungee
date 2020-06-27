package main.bungee.redis.sections.uuid;

import lombok.Getter;
import main.data.redis.value.RedisValue;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class PlayerUUIDValue extends RedisValue {
    @Getter private String value;
    public PlayerUUIDValue(ProxiedPlayer p) {
        super(p.getUniqueId().toString());
        this.value = p.getName();
    }
    public PlayerUUIDValue(UUID id,String value) {
        super(id.toString());
        this.value = value;
    }
    @Override
    public String getUpdateValue() {
        return value;
    }
}
