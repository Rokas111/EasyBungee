package main.lib.manager;

import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public abstract class Manager implements IManager {
    public void enable() {
        onEnable();
        getSubManagers().forEach(Manager::enable);
    }
    public void disable() {
        getSubManagers().forEach(Manager::disable);
        onDisable();
    }
    public static List<Manager> getManagers(Object obj) {
        final List<Manager> fields = new ArrayList<>();
        for (Field f: obj.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(ManagerHandler.class)) {
                f.setAccessible(true);
                try {
                    fields.add((Manager) f.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return fields;
    }
}
