package main.bungee.redis.sections.profile.attribute.defaults;

import api.player.BOfflinePlayer;
import main.bungee.BungeeMain;
import main.bungee.redis.sections.profile.attribute.ProfileAttribute;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ProfileTime extends ProfileAttribute<Integer> {
    private static final String NAME = "time";
    private UUID id;
    public ProfileTime(UUID id) {
        super(NAME, 0);
        this.id = id;
        start();
    }
    public ProfileTime(Integer value,UUID id) {
        super(NAME, value);
        this.id = id;
        start();
    }
    private void start() {
        BungeeMain.pl.getProxy().getScheduler().schedule(BungeeMain.pl,() -> {
            if (new BOfflinePlayer(id).isOnline())setValue(getRawValue() +1);
        },1000,1000, TimeUnit.MILLISECONDS);
    }
}
