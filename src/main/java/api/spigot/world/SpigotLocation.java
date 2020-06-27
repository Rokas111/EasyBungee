package api.spigot.world;

import api.spigot.APIInfo;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Used to encode Bukkit locations for server communication
 *
 * @author Rokaz
 */
@RequiredArgsConstructor
public class SpigotLocation {
    @NonNull @Getter private double x;
    @NonNull @Getter private double y;
    @NonNull @Getter private double z;
    @NonNull @Getter private float pitch;
    @NonNull @Getter private float yaw;
    @NonNull @Getter private SpigotWorld world;
    public SpigotLocation(String serializedValue) {
        if (serializedValue.split(APIInfo.SEPARATOR).length < 6) {
            this.x = Double.parseDouble(serializedValue.split(APIInfo.SEPARATOR)[0]);
            this.y = Double.parseDouble(serializedValue.split(APIInfo.SEPARATOR)[1]);
            this.z = Double.parseDouble(serializedValue.split(APIInfo.SEPARATOR)[2]);
            this.pitch = Float.parseFloat(serializedValue.split(APIInfo.SEPARATOR)[3]);
            this.yaw = Float.parseFloat(serializedValue.split(APIInfo.SEPARATOR)[4]);
            this.world = new SpigotWorld(String.join(APIInfo.SEPARATOR,Arrays.asList(serializedValue.split(APIInfo.SEPARATOR)).subList(6,serializedValue.split(APIInfo.SEPARATOR).length)));
        } else {
            this.x = Double.parseDouble(serializedValue.split(APIInfo.SEPARATOR)[0]);
            this.y = Double.parseDouble(serializedValue.split(APIInfo.SEPARATOR)[1]);
            this.z = Double.parseDouble(serializedValue.split(APIInfo.SEPARATOR)[2]);
            this.world = new SpigotWorld(String.join(APIInfo.SEPARATOR,Arrays.asList(serializedValue.split(APIInfo.SEPARATOR)).subList(5,serializedValue.split(APIInfo.SEPARATOR).length)));
        }
    }
    public SpigotLocation(double x,double y,double z,SpigotWorld world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }
    @Override
    public String toString() {
        return x + APIInfo.SEPARATOR + y + APIInfo.SEPARATOR + z + APIInfo.SEPARATOR + pitch + APIInfo.SEPARATOR + yaw + APIInfo.SEPARATOR + world.toString();
    }
}
