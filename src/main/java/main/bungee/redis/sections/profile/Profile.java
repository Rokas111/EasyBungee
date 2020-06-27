package main.bungee.redis.sections.profile;

import lombok.Getter;
import main.EasyBungee;
import main.bungee.mysql.MySQLProcessor;
import main.bungee.redis.sections.profile.attribute.defaults.ProfileJoinDate;
import main.bungee.redis.sections.profile.attribute.defaults.ProfileLastOnline;
import main.bungee.redis.sections.profile.attribute.defaults.ProfileLastServer;
import main.data.mysql.adapter.MySQLSaveable;
import main.bungee.redis.RedisManager;
import main.bungee.redis.sections.profile.attribute.ProfileAttribute;
import main.bungee.redis.sections.profile.attribute.defaults.ProfileTime;
import main.data.redis.value.section.RedisSection;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Profile extends RedisSection<ProfileAttribute> implements MySQLSaveable {
    public static final List<Class<? extends ProfileAttribute>> DEFAULT_ATTRIBUTES = Arrays.asList(ProfileTime.class, ProfileJoinDate.class, ProfileLastOnline.class, ProfileLastServer.class);
    private static final String PREFIX = "profile.";
    @Getter private UUID id;
    public Profile(UUID id) {
        super(PREFIX + id);
        this.id = id;
        getValues().addAll(DEFAULT_ATTRIBUTES.stream().map(attribute -> {
            try {
                return attribute.getConstructor(UUID.class).newInstance(id);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                return null;
            }
        }).collect(Collectors.toList()));
    }
    public Profile(UUID id,List<ProfileAttribute> attributes) {
        super(PREFIX + id);
        this.id = id;
        getValues().addAll(DEFAULT_ATTRIBUTES.stream().filter(attribute -> attributes.stream().noneMatch(a -> {
            try {
                return a.getKey().equals(attribute.getConstructor(UUID.class).newInstance(id).getKey());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                return false;
            }
        })).map(attribute -> {
            try {
                return attribute.getConstructor(UUID.class).newInstance(id);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                return null;
            }
        }).collect(Collectors.toList()));
        getValues().addAll(attributes);
    }
    public List<ProfileAttribute> getCustomAttributes() {
        return getValues().stream().map(key -> (ProfileAttribute) key).filter(key -> !DEFAULT_ATTRIBUTES.contains(key.getClass())).collect(Collectors.toList());
    }
    public static boolean hasProfile(UUID id) {
        return RedisManager.VALUES.stream().anyMatch(value -> value.getKey().equals(PREFIX +id));
    }
    public void save() {
        if (EasyBungee.mySQLConnection != null) {
            MySQLProcessor.store(this);
        }
    }
}
