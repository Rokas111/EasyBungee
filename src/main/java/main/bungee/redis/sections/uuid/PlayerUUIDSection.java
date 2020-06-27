package main.bungee.redis.sections.uuid;

import main.EasyBungee;
import main.bungee.BungeeMain;
import main.bungee.mysql.MySQLProcessor;
import main.data.mysql.adapter.MySQLSaveable;
import main.data.redis.value.section.RedisSection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerUUIDSection extends RedisSection<PlayerUUIDValue> implements MySQLSaveable {
    public PlayerUUIDSection() {
        super("uuids");
        BungeeMain.pl.getProxy().getScheduler().runAsync(BungeeMain.pl, () -> {
            if (EasyBungee.mySQLConnection != null && MySQLProcessor.contains(PlayerUUIDValue.class)) {
                MySQLProcessor.getObjects(PlayerUUIDValue.class).forEach(value -> add(value));
            }
        });
    }
    @Override
    public void update() {
        BungeeMain.pl.getProxy().getPlayers().stream().filter(player -> getValues().stream().noneMatch(key -> key.getKey().equals(player.getUniqueId().toString()))).forEach(player -> add(new PlayerUUIDValue(player)));
        super.update();
    }
    public void save() {
        List<PlayerUUIDValue> values = MySQLProcessor.contains(PlayerUUIDValue.class)?MySQLProcessor.getObjects(PlayerUUIDValue.class):new ArrayList<>();
        getValues().stream().filter(value -> values.isEmpty() || values.stream().noneMatch(v -> v.getKey().equals(value.getKey()))).forEach(MySQLProcessor::store);
    }
}
