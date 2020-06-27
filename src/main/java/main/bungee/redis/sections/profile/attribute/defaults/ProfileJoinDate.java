package main.bungee.redis.sections.profile.attribute.defaults;

import main.bungee.redis.sections.profile.attribute.ProfileAttribute;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ProfileJoinDate extends ProfileAttribute<Long> {
    private static final String NAME = "joinDate";
    public ProfileJoinDate(UUID id) {
        super(NAME, Calendar.getInstance().getTimeInMillis());
    }
    public ProfileJoinDate(Long value,UUID id) {
        super(NAME, value);
    }
}
