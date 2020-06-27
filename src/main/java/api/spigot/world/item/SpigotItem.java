package api.spigot.world.item;

import api.spigot.APIInfo;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Used to encode Bukkit items for server communication
 *
 * @author Rokaz
 */
@RequiredArgsConstructor
public class SpigotItem {
    @Setter @NonNull @Getter private String material;
    @Setter @NonNull @Getter private int amount;
    @Setter @NonNull @Getter private String name;
    @Setter @NonNull @Getter private short durability;
    @Setter @NonNull @Getter private List<String> lore;
    @Setter @NonNull @Getter private HashMap<String,Integer> enchantments;
    public SpigotItem(String serializedValue) {
        this.material = serializedValue.split(APIInfo.SEPARATOR,-1)[0];
        this.amount = Integer.parseInt(serializedValue.split(APIInfo.SEPARATOR,-1)[1]);
        this.durability = Short.parseShort(serializedValue.split(APIInfo.SEPARATOR,-1)[2]);
        this.name = serializedValue.split(APIInfo.SEPARATOR,-1)[3] == null?"":serializedValue.split(APIInfo.SEPARATOR,-1)[3];
        this.lore = !serializedValue.split(APIInfo.SEPARATOR,-1)[4].isEmpty()?Stream.of(serializedValue.split(APIInfo.SEPARATOR,-1)[4].contains(APIInfo.SEPARATOR_2)?serializedValue.split(APIInfo.SEPARATOR,-1)[4].split(APIInfo.SEPARATOR_2,-1):new String[]{serializedValue.split(APIInfo.SEPARATOR,-1)[4]}).collect(Collectors.toList()):new ArrayList<>();
        this.enchantments = !serializedValue.split(APIInfo.SEPARATOR,-1)[5].isEmpty()?(HashMap<String,Integer>)Stream.of(serializedValue.split(APIInfo.SEPARATOR,-1)[5].contains(APIInfo.SEPARATOR_2)?serializedValue.split(APIInfo.SEPARATOR,-1)[5].split(APIInfo.SEPARATOR_2,-1):serializedValue.split(APIInfo.SEPARATOR,-1)[4]).collect(Collectors.toMap(line -> ((String)line).split("_")[0],line -> Integer.parseInt(((String)line).split("_")[1]))):new HashMap<>();
    }
    public SpigotItem(String material,int amount) {
        this.material = material;
        this.amount = amount;
        this.durability = (short) 0;
        this.name = "";
        this.lore = new ArrayList<>();
        this.enchantments = new HashMap<>();
    }
    public SpigotItem(String material,int amount,short durability) {
        this.material = material;
        this.amount = amount;
        this.durability = durability;
        this.name = "";
        this.lore = new ArrayList<>();
        this.enchantments = new HashMap<>();
    }
    public SpigotItem(String material,int amount,String name,List<String> lore,HashMap<String,Integer> enchantments) {
        this.material = material;
        this.amount = amount;
        this.durability = (short) 0;
        this.name = name;
        this.lore = lore;
        this.enchantments = enchantments;
    }
    public SpigotItem(String material,int amount,short durability,String name,List<String> lore,HashMap<String,Integer> enchantments) {
        this.material = material;
        this.amount = amount;
        this.durability = durability;
        this.name = name;
        this.lore = lore;
        this.enchantments = enchantments;
    }
    @Override
    public String toString() {
        return material + APIInfo.SEPARATOR + amount+ APIInfo.SEPARATOR + durability + APIInfo.SEPARATOR + name + APIInfo.SEPARATOR + (lore.isEmpty()?"":String.join(APIInfo.SEPARATOR_2,lore)) + APIInfo.SEPARATOR + (enchantments.isEmpty()?"":String.join(APIInfo.SEPARATOR_2,enchantments.keySet().stream().map(enchantment -> enchantment + "_" + enchantments.get(enchantment)).collect(Collectors.toList())));
    }
}
