package main.bungee.redis.sections.profile.attribute.defaults;

import api.player.BOfflinePlayer;
import api.player.BungeePlayer;
import main.bungee.redis.sections.profile.attribute.ProfileAttribute;

import java.util.UUID;

public class ProfileLastServer extends ProfileAttribute<String> {
    private static final String NAME = "lastServer";
    public ProfileLastServer(UUID id) {
        super(NAME, new BOfflinePlayer(id).isOnline()?new BungeePlayer(id).getCurrentServer().getName():"noneServer");
    }
    public ProfileLastServer(String value,UUID id) {
        super(NAME, value);
    }
}
