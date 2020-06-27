package main.data.redis.channel.message.callbacks;

import api.spigot.world.SpigotBlock;
import api.spigot.world.SpigotLocation;
import api.spigot.world.item.SpigotItem;
import api.spigot.world.lists.SpigotInventory;
import api.spigot.world.lists.SpigotWorlds;
import lombok.Getter;

import java.util.Arrays;

public enum MessageValueConverter {
    BOOLEAN(Boolean.class, Boolean::valueOf),
    STRING(String.class,(value) -> value),
    SPIGOT_WORLDS(SpigotWorlds.class, SpigotWorlds::new),
    INTEGER(Integer.class,Integer::parseInt),
    DOUBLE(Double.class,Double::parseDouble),
    FLOAT(Float.class,Float::parseFloat),
    SPIGOT_BLOCK(SpigotBlock.class,SpigotBlock::new),
    SPIGOT_INVENTORY(SpigotInventory.class,SpigotInventory::new),
    SPIGOT_ITEM(SpigotItem.class,SpigotItem::new),
    SPIGOT_LOCATION(SpigotLocation.class,SpigotLocation::new);
    @Getter private Class<?> referenceClass;
    @Getter private valueConverter converter;
    private MessageValueConverter(Class<?> clz,valueConverter converter) {
        this.referenceClass = clz;
        this.converter =converter;
    }
    public static <T> T convertValue(Class<T> clz,String value) {
        return (T) Arrays.asList(values()).stream().filter(type -> type.getReferenceClass().equals(clz)).findFirst().orElse(null).getConverter().convert(value);
    }
    interface valueConverter<T> {
        T convert(String value);
    }

}
