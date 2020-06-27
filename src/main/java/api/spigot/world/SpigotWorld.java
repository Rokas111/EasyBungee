package api.spigot.world;

import api.spigot.APIInfo;
import api.spigot.SpigotSound;
import api.spigot.world.item.SpigotItem;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.message.sender.MessageSender;
import main.data.redis.channel.references.spigot.block.GetBlock;
import main.data.redis.channel.references.spigot.block.SetBlock;
import main.data.redis.channel.references.spigot.world.DropItem;
import main.data.redis.channel.references.spigot.world.PlaySound;
import main.data.redis.channel.references.spigot.world.SpawnEntity;

/**
 * Used to encode Bukkit worlds for server communication
 *
 * @author Rokaz
 */
@RequiredArgsConstructor
public class SpigotWorld {
    @NonNull @Getter private String server;
    @NonNull @Getter private String name;
    public SpigotWorld(String serializedValue) {
        this.name = serializedValue.split(APIInfo.SEPARATOR)[0];
        this.server = serializedValue.split(APIInfo.SEPARATOR)[1];
    }
    @Override
    public String toString() {
        return name + APIInfo.SEPARATOR + server;
    }
    /**
     * Use to get a block at a specific location of this world
     *
     * @author Rokaz
     */
    public MessageSender<SpigotBlock> getBlockAt(SpigotLocation location) {
        return new MessageSender<>(new ServerMessage(new GetBlock(), server, new String[]{location.toString()}),SpigotBlock.class);
    }
    /**
     * Use to set a block at a specific location of this world
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setBlock(SpigotBlock block) {
        return new MessageSender<>(new ServerMessage(new SetBlock(), server, new String[]{block.toString()}),Boolean.class);
    }
    /**
     * Use to drop an item at a specific location of this world
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> dropItem(SpigotLocation loc, SpigotItem item) {
        return new MessageSender<>(new ServerMessage(new DropItem(), server, new String[]{loc.toString(),item.toString()}),Boolean.class);
    }
    /**
     * Use to play a sound in a specific location of this world
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> playSound(SpigotLocation loc, SpigotSound sound) {
        return new MessageSender<>(new ServerMessage(new PlaySound(), server, new String[]{loc.toString(),sound.toString()}),Boolean.class);
    }
    /**
     * Use to spawn an entity in a specific location of this world
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> spawnEntity(SpigotLocation loc,String entityType) {
        return new MessageSender<>(new ServerMessage(new SpawnEntity(), server, new String[]{loc.toString(),entityType}),Boolean.class);
    }
}
