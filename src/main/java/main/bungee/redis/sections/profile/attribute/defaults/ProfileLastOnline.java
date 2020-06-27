package main.bungee.redis.sections.profile.attribute.defaults;


import api.player.BungeePlayer;
import main.bungee.redis.sections.profile.attribute.ProfileAttribute;

import java.util.Date;
import java.util.UUID;

public class ProfileLastOnline extends ProfileAttribute<Long> {
    private static final String NAME = "lastOnline";
    public ProfileLastOnline(UUID id) {
        super(NAME, Long.MIN_VALUE);
    }
    public ProfileLastOnline(Long value,UUID id) {
        super(NAME, value);
    }
}
