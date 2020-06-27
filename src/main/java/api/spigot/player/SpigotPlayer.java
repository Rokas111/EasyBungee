package api.spigot.player;

import api.player.BungeePlayer;
import api.spigot.SpigotSound;
import api.spigot.world.SpigotLocation;
import api.spigot.world.item.SpigotItem;
import api.spigot.world.lists.SpigotInventory;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.message.sender.MessageSender;
import main.data.redis.channel.references.spigot.player.*;

import java.util.UUID;

public class SpigotPlayer extends BungeePlayer {
    public SpigotPlayer(UUID id) {
        super(id);
    }
    public SpigotPlayer(String name) {
        super(name);
    }
    /**
     * Used to send a message to the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> sendMessage(String message) {
        return new MessageSender<>(new ServerMessage(new SendMessagePlayer(), getCurrentServer().getName(), new String[]{getName(),message}),Boolean.class);
    }
    /**
     * Used to get a location of the player
     *
     * @author Rokaz
     */
    public MessageSender<SpigotLocation> getLocation() {
        return new MessageSender<>(new ServerMessage(new GetLocation(), getCurrentServer().getName(), new String[]{getName()}),SpigotLocation.class);
    }
    /**
     * Used to get the exp of the player
     *
     * @author Rokaz
     */
    public MessageSender<Float> getExp() {
        return new MessageSender<>(new ServerMessage(new GetExp(), getCurrentServer().getName(), new String[]{getName()}),Float.class);
    }
    /**
     * Used to get the exp level of the player
     *
     * @author Rokaz
     */
    public MessageSender<Integer> getExpLevel() {
        return new MessageSender<>(new ServerMessage(new GetExpLevel(), getCurrentServer().getName(), new String[]{getName()}),Integer.class);
    }
    /**
     * Used to get the food level of the player
     *
     * @author Rokaz
     */
    public MessageSender<Integer> getFood() {
        return new MessageSender<>(new ServerMessage(new GetFood(), getCurrentServer().getName(), new String[]{getName()}),Integer.class);
    }
    /**
     * Used to get the health of the player
     *
     * @author Rokaz
     */
    public MessageSender<Double> getHealth() {
        return new MessageSender<>(new ServerMessage(new GetHealth(), getCurrentServer().getName(), new String[]{getName()}),Double.class);
    }
    /**
     * Used to get the inventory of the player
     *
     * @author Rokaz
     */
    public MessageSender<SpigotInventory> getInventory() {
        return new MessageSender<>(new ServerMessage(new GetPlayerInventory(), getCurrentServer().getName(), new String[]{getName()}),SpigotInventory.class);
    }
    /**
     * Used to get the item in the hand of the player
     *
     * @author Rokaz
     */
    public MessageSender<SpigotItem> getItemInHand() {
        return new MessageSender<>(new ServerMessage(new GetPlayerItemInHand(), getCurrentServer().getName(), new String[]{getName()}),SpigotItem.class);
    }
    /**
     * Used to see if the player has a permission
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> hasPermission(String permission) {
        return new MessageSender<>(new ServerMessage(new HasPermission(), getCurrentServer().getName(), new String[]{getName(),permission}),Boolean.class);
    }
    /**
     * Used to kill the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> kill() {
        return new MessageSender<>(new ServerMessage(new Kill(), getCurrentServer().getName(), new String[]{getName()}),Boolean.class);
    }
    /**
     * Used to open an inventory to the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> openInventory(SpigotInventory item) {
        return new MessageSender<>(new ServerMessage(new OpenInventory(), getCurrentServer().getName(), new String[]{getName(),item.toString()}),Boolean.class);
    }
    /**
     * Used to teleport the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> teleport(SpigotLocation loc) {
        return new MessageSender<>(new ServerMessage(new PlayerTeleport(), getCurrentServer().getName(), new String[]{getName(),loc.toString()}),Boolean.class);
    }
    /**
     * Used to play a sound to the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> playSound(SpigotSound sound) {
        return new MessageSender<>(new ServerMessage(new PlaySoundPlayer(), getCurrentServer().getName(), new String[]{getName(),sound.toString()}),Boolean.class);
    }
    /**
     * Used to set the display name of the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setDisplayName(String name) {
        return new MessageSender<>(new ServerMessage(new SetDisplayName(), getCurrentServer().getName(), new String[]{getName(),name}),Boolean.class);
    }
    /**
     * Used to set the exp of the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setExp(float exp) {
        return new MessageSender<>(new ServerMessage(new SetExp(), getCurrentServer().getName(), new String[]{getName(),Float.toString(exp)}),Boolean.class);
    }
    /**
     * Used to set the exp level of the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setExpLevel(int level) {
        return new MessageSender<>(new ServerMessage(new SetExpLevel(), getCurrentServer().getName(), new String[]{getName(),Integer.toString(level)}),Boolean.class);
    }
    /**
     * Used to set the food level of the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setFood(int food) {
        return new MessageSender<>(new ServerMessage(new SetFood(), getCurrentServer().getName(), new String[]{getName(),Integer.toString(food)}),Boolean.class);
    }
    /**
     * Used to set the GameMode of the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setGameMode(String gameMode) {
        return new MessageSender<>(new ServerMessage(new SetGameMode(), getCurrentServer().getName(), new String[]{getName(),gameMode}),Boolean.class);
    }
    /**
     * Used to set the health of the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setHealth(double health) {
        return new MessageSender<>(new ServerMessage(new SetHealth(), getCurrentServer().getName(), new String[]{getName(),Double.toString(health)}),Boolean.class);
    }
    /**
     * Used to set the item in the hand of the player
     *
     * @author Rokaz
     */
    public MessageSender<Boolean> setItemInHand(SpigotItem item) {
        return new MessageSender<>(new ServerMessage(new SetItemInHand(), getCurrentServer().getName(), new String[]{getName(),item.toString()}),Boolean.class);
    }
}
