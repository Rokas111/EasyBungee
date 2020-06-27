package main.spigot.redis.channel.cmds.message;

import main.data.redis.channel.references.spigot.ExecuteCommand;
import main.spigot.SpigotMain;

public class SExecuteCommand extends ExecuteCommand {
    @Override
    public Boolean run(String[] args) {
        SpigotMain.pl.getServer().dispatchCommand(SpigotMain.pl.getServer().getConsoleSender(),args[0]);
        return true;
    }

}
