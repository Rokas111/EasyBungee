package main.bungee.redis.sections.players;

import main.bungee.BungeeMain;
import main.data.redis.value.RedisValue;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerServerValue extends RedisValue {
    public PlayerServerValue(ProxiedPlayer p) {
        super(p.getUniqueId().toString());
    }
    public String getUpdateValue() {
        try  {
            return BungeeMain.pl.getProxy().getPlayers().stream().filter(player -> player.getUniqueId().toString().equals(getKey())).findFirst().orElse(null).getServer().getInfo().getName();
        } catch (Exception e) {
            return null;
        }
    }
}
