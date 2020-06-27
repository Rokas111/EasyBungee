package main.bungee.redis.sections.players;

import main.bungee.BungeeMain;
import main.data.redis.value.section.RedisSection;

public class PlayerServerSection extends RedisSection<PlayerServerValue> {
    public PlayerServerSection() {
        super("players");
    }
    @Override
    public void update() {
        getValues().removeIf(value -> BungeeMain.pl.getProxy().getPlayers().stream().noneMatch(player -> player.getUniqueId().toString().equals(value.getKey())));
        BungeeMain.pl.getProxy().getPlayers().stream().filter(player -> getValues().stream().noneMatch(value -> value.getKey().equals(player.getUniqueId().toString()))).forEach(player -> add(new PlayerServerValue(player)));
        super.update();
    }
}
