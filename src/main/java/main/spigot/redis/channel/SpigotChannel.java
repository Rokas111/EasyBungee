package main.spigot.redis.channel;

import main.data.redis.channel.ServerChannel;
import main.data.redis.channel.cmd.IChannelCmd;
import main.spigot.redis.channel.cmds.message.SBroadcast;
import main.spigot.redis.channel.cmds.message.SExecuteCommand;
import main.spigot.redis.channel.cmds.message.SSendToConsole;
import main.spigot.redis.channel.cmds.player.SSendMessagePlayer;
import main.spigot.redis.channel.cmds.player.item.SGetPlayerInventory;
import main.spigot.redis.channel.cmds.player.item.SGetPlayerItemInHand;
import main.spigot.redis.channel.cmds.player.item.SOpenInventory;
import main.spigot.redis.channel.cmds.player.item.SSetItemInHand;
import main.spigot.redis.channel.cmds.player.misc.*;
import main.spigot.redis.channel.cmds.player.number.*;
import main.spigot.redis.channel.cmds.world.*;
import main.spigot.redis.channel.cmds.world.block.SBreakBlock;
import main.spigot.redis.channel.cmds.world.block.SGetBlock;
import main.spigot.redis.channel.cmds.world.block.SSetBlock;

import java.util.Arrays;
import java.util.List;

public class SpigotChannel extends ServerChannel {
    public SpigotChannel() {
        super();
    }
    public List<IChannelCmd> getCommands() {
        return Arrays.asList(new SBroadcast(),new SSendToConsole(),new SSpawnEntity(),new SPlaySound(),new SGetWorlds(),new SDropItem(),new SSendMessagePlayer(), new SSetHealth(),new SSetFood(),new SSetExpLevel(),new SSetExp(),new SGetHealth(),new SGetFood(),new SGetExpLevel(),new SGetExp()
                ,new SSetGameMode(),new SSetDisplayName(),new SPlaySoundPlayer(),new SPlayerTeleport(),new SKill(),new SHasPermission()
                ,new SSetItemInHand(),new SOpenInventory(),new SGetPlayerItemInHand(),new SGetPlayerInventory(), new SExecuteCommand()
                ,new SSetBlock(),new SGetBlock(),new SBreakBlock(),new SCreateWorld(),new SGetLocation());
    }
}
