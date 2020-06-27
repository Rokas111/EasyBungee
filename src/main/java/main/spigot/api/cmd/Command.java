package main.spigot.api.cmd;

import lombok.Getter;
import main.spigot.SpigotMain;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Command extends org.bukkit.command.Command implements ICommand {
    @Getter private String permission;
    @Getter private String description;
    private List<String> usages;
    private List<String> cmdAliases;
    private boolean allowConsole;
    public Command(String permission, String description, List<String> usages, boolean allowConsole, String... cmdAliases) {
        super(cmdAliases[0]);
        this.permission = permission;
        this.description = description;
        this.usages = usages;
        this.allowConsole = allowConsole;
        this.cmdAliases = Arrays.asList(cmdAliases);
        super.description = description;
    }
    public boolean getAllowConsole() {
        return this.allowConsole;
    }
    @Override
    public boolean execute(CommandSender s, String alias, String[] args) {
        if (s instanceof Player) SpigotMain.pl.getCommandManager().runCommand((Player) s,this,alias,args); else SpigotMain.pl.getCommandManager().runCommand(this,alias,args);
        return true;
    }
    public List<String> getUsages() {
        return Collections.unmodifiableList(usages);
    }
    public List<String> getCmdAliases() {
        return Collections.unmodifiableList(cmdAliases);
    }
}
