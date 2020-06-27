package main.bungee.mysql;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import main.EasyBungee;
import main.bungee.BungeeMain;
import main.bungee.api.BungeeManager;
import main.bungee.config.data.MySQLConfig;
import main.bungee.redis.sections.profile.attribute.defaults.ProfileLastServer;
import main.data.exception.ConnectionException;
import main.data.mysql.MySQLConnection;
import main.data.mysql.adapter.MySQLAdapter;
import main.data.mysql.adapter.SQLTable;
import main.data.mysql.adapter.SQLValue;
import main.data.mysql.adapter.SQLValueType;
import main.bungee.redis.sections.profile.Profile;
import main.bungee.redis.sections.profile.attribute.ProfileAttribute;
import main.bungee.redis.sections.uuid.PlayerUUIDSection;
import main.bungee.redis.sections.uuid.PlayerUUIDValue;
import main.lib.config.Config;
import main.lib.manager.Manager;
import net.md_5.bungee.api.ChatColor;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MySQLManager extends BungeeManager {
    public static final HashMap<Class<?>, MySQLAdapter<?>> ADAPTERS = new HashMap<>(ImmutableMap.<Class<?>, MySQLAdapter<?>>builder()
            .put(PlayerUUIDValue.class,new MySQLAdapter<PlayerUUIDValue>() {
                public String getName() {
                    return "UUIDs";
                }
                public SQLTable toMySQL(PlayerUUIDValue value) {
                    HashMap<String, SQLValue> keys = new HashMap<>();
                    keys.put("id",new SQLValue(SQLValueType.ID,value.getKey()));
                    keys.put("name",new SQLValue(SQLValueType.STRING,value.getValue()));
                    return new SQLTable(keys);
                }
                public PlayerUUIDValue fromMySQL(SQLTable table) {
                    return new PlayerUUIDValue(UUID.fromString(table.getKeys().get("id").getValue()),table.getKeys().get("name").getValue());
                }
            }).put(Profile.class,new MySQLAdapter<Profile>() {
                public String getName() {
                    return "Profiles";
                }
                public SQLTable toMySQL(Profile profile) {
                    HashMap<String, SQLValue> keys = new HashMap<>(ImmutableMap.<String, SQLValue>builder().
                            put("id",new SQLValue(SQLValueType.ID,profile.getId().toString())).build());
                    profile.getValues().forEach(key -> keys.put(key.getClass().getSimpleName(),new SQLValue(SQLValueType.getTypeByClass(key.getRawValue().getClass()),profile.getValue(key))));
                    return new SQLTable(keys);
                }
                public Profile fromMySQL(SQLTable table) {
                    UUID id = UUID.fromString(table.getKeys().get("id").getValue());
                    return new Profile(id,table.getKeysWithoutPrimary().keySet().stream().map(key -> {
                        try {
                            return Profile.DEFAULT_ATTRIBUTES.stream().filter(attribute -> attribute.getSimpleName().equals(key)).findFirst().orElse(null).getConstructor(table.getKeysWithoutPrimary().get(key).getType().getType(),UUID.class).newInstance(table.getKeysWithoutPrimary().get(key).getType().getConverter().convert(table.getKeysWithoutPrimary().get(key).getValue()),id);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(Collectors.toList()));
                }
            }).build());
    @Getter private MySQLConfig config;
    @Getter private MySQLProcessor MySQLProcessor;
    public void onEnable() {
        config = new MySQLConfig();
        try {
            EasyBungee.mySQLConnection = new MySQLConnection(getConfig().read());
            BungeeMain.sendConsole(ChatColor.BLUE + "Successfully connected to "+ ChatColor.YELLOW + "MySQL");
            MySQLProcessor = new MySQLProcessor();
        } catch (ConnectionException e) {
            BungeeMain.sendConsole(ChatColor.RED + "Failed to connect to "+ ChatColor.YELLOW + "MySQL" + ChatColor.WHITE + "." + ChatColor.YELLOW + "MySQL " + ChatColor.WHITE + "features unavailable");
        }
    }
    public void onDisable() {
    }
    public List<Manager> getSubManagers() {
        return new ArrayList<>();
    }
    public List<Config> getConfigs() {
        return Arrays.asList(config);
    }
}
