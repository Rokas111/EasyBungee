package main.lib.config.section;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.stream.Stream;

public class ConfigSection {
    private HashMap<String,Object> keys = new HashMap<>();
    public ConfigSection() {
    }
    public ConfigSection(HashMap<String,Object> keys) {
        this.keys = keys;
    }
    public HashMap<String,Object> getKeys() {
        HashMap<String,Object> objects = keys;
        Stream.of(this.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Key.class)).filter(field -> objects.keySet().stream().noneMatch(key -> field.getAnnotation(Key.class).name().equals(key))).forEach(field -> {
            try {
                field.setAccessible(true);
                objects.put(field.getAnnotation(Key.class).name(),field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return objects;
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Key {
        String name();
    }
}
