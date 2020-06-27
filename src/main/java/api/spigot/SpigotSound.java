package api.spigot;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Used to encode Bukkit sounds for server communication
 *
 * @author Rokaz
 */
@RequiredArgsConstructor
public class SpigotSound {
    @NonNull @Getter private String name;
    @NonNull @Getter private float volume;
    @NonNull @Getter private float pitch;
    public SpigotSound(String serializedValue) {
        this.name = serializedValue.split(APIInfo.SEPARATOR)[0];
        this.volume = Float.parseFloat(serializedValue.split(APIInfo.SEPARATOR)[1]);
        this.pitch = Float.parseFloat(serializedValue.split(APIInfo.SEPARATOR)[2]);
    }
    @Override
    public String toString() {
        return name + APIInfo.SEPARATOR + volume + APIInfo.SEPARATOR + pitch;
    }
}
