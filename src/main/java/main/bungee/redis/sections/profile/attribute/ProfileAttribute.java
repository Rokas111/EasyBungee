package main.bungee.redis.sections.profile.attribute;

import lombok.Setter;
import main.data.redis.value.RedisValue;

public class ProfileAttribute<E> extends RedisValue {
    @Setter private E value;
    public ProfileAttribute(String name, E value) {
        super(name);
        this.value = value;
    }
    public String getUpdateValue() {
        return value.toString();
    }
    public E getRawValue() { return this.value;}
}
