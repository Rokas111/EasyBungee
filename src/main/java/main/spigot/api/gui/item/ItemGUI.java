package main.spigot.api.gui.item;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import main.spigot.api.gui.GUI;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class ItemGUI<T extends IItem> {
    @NonNull private int size;
    @NonNull private ItemStack fillIn;
    @NonNull private String title;
    @Setter @NonNull private List<T> items;
    public GUI toGUI() {
        HashMap<Integer, ItemStack> slots = new HashMap<>();
        for (int i = 0;i<size;i++) {
            slots.put(i,fillIn);
        }
        for (int i = 0; i< items.size();i++) {
            if (i >= size) break;
            slots.replace(i,items.get(i).toItem());
        }
        return new GUI(title,slots);
    }
}
