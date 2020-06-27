package api.spigot.world;

import api.spigot.APIInfo;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import main.EasyBungee;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.message.sender.MessageSender;
import main.data.redis.channel.references.spigot.block.BreakBlock;

/**
 * Used to encode Bukkit blocks for server communication
 *
 * @author Rokaz
 */
@RequiredArgsConstructor
public class SpigotBlock {
    @NonNull @Getter private String material;
    @NonNull @Getter private SpigotLocation location;
    @NonNull @Getter private int blockStateData;
    public SpigotBlock(String serializedValue) {
        this.material = serializedValue.split(APIInfo.SEPARATOR_2)[0];
        this.location = new SpigotLocation(serializedValue.split(APIInfo.SEPARATOR_2)[1]);
        this.blockStateData = Integer.parseInt(serializedValue.split(APIInfo.SEPARATOR_2)[2]);
    }

    @Override
    public String toString() {
        return material + APIInfo.SEPARATOR_2 + location.toString() + APIInfo.SEPARATOR_2 + blockStateData;
    }
    /**
     * Used to break the block
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> breakNaturally() {
        return new MessageSender<>(new ServerMessage(new BreakBlock(), location.getWorld().getServer(), new String[]{location.toString()}),Boolean.class);
    }
    /**
     * Used to set the material of the block
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setMaterial(String material) {
        this.material = material;
        return location.getWorld().setBlock(new SpigotBlock(material,location,blockStateData));
    }
    /**
     * Used to set the state data of the block
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setStateData(int data) {
        this.blockStateData = data;
        return location.getWorld().setBlock(new SpigotBlock(material,location,data));
    }
}
