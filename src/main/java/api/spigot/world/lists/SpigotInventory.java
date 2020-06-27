package api.spigot.world.lists;

import api.spigot.APIInfo;
import api.spigot.world.item.SpigotItem;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Used to encode Bukkit inventories for server communication
 *
 * @author Rokaz
 */
@RequiredArgsConstructor
public class SpigotInventory {
    @Setter @NonNull @Getter private String title;
    @NonNull @Getter private HashMap<Integer, SpigotItem> slots;
    public SpigotInventory(String serializedValue) {
        this.title = serializedValue.split(APIInfo.SEPARATOR_2)[0];
        if (serializedValue.split(APIInfo.SEPARATOR_2).length > 2) {
            this.slots = (HashMap<Integer, SpigotItem>)Stream.of(serializedValue.split(APIInfo.SEPARATOR_2)).collect(Collectors.toList()).subList(1,serializedValue.split(APIInfo.SEPARATOR_2).length).stream().collect(Collectors.toMap(line -> Integer.parseInt(line.split("-")[0]), line -> new SpigotItem(line.split("-")[1])));
        } else {
            this.slots = (HashMap<Integer, SpigotItem>)Stream.of(serializedValue.split(APIInfo.SEPARATOR_2)[1]).collect(Collectors.toMap(line -> Integer.parseInt(line.split("-")[0]), line -> new SpigotItem(line.split("-")[1])));
        }
    }
    public SpigotInventory(String title,Integer size,HashMap<Integer, SpigotItem> slots) {
        this.title = title;
        HashMap<Integer,SpigotItem> items = new HashMap<>();
        for (int i = 0; i<size; i++) {
            items.put(i,new SpigotItem("AIR",1));
        }
        slots.forEach((slot,item) -> {
            if (slot > size) return;
            items.replace(slot, item);
        });

        this.slots = items;
    }
    public SpigotInventory(String title,Integer size) {
        this.title = title;
        HashMap<Integer,SpigotItem> items = new HashMap<>();
        for (int i = 0; i<size; i++) {
            items.put(i,new SpigotItem("AIR",1));
        }
        this.slots = items;
    }
    public SpigotInventory(String title,Integer size,SpigotItem fillIn) {
        this.title = title;
        HashMap<Integer,SpigotItem> items = new HashMap<>();
        for (int i = 0; i<size; i++) {
            items.put(i,fillIn);
        }
        this.slots = items;
    }
    public SpigotInventory(String title,Integer size,HashMap<Integer, SpigotItem> slots,SpigotItem fillIn) {
        this.title = title;
        HashMap<Integer,SpigotItem> items = new HashMap<>();
        for (int i = 0; i<size; i++) {
            items.put(i,fillIn);
        }
        slots.forEach((slot,item) -> {
            if (slot > size) return;
            items.replace(slot, item);
        });
        this.slots = items;
    }
    public void setItem(Integer slot,SpigotItem item) {
        if (slots.containsKey(slot)) slots.replace(slot,item); else slots.put(slot, item);
    }
    @Override
    public String toString() {
        return title+APIInfo.SEPARATOR_2+String.join(APIInfo.SEPARATOR_2,slots.keySet().stream().map(slot -> slot + "-" + slots.get(slot).toString()).collect(Collectors.toList()));
    }
}
