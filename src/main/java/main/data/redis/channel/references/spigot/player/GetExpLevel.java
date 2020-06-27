package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ObtainCmd;

public class GetExpLevel extends ObtainCmd<Integer> {
    public GetExpLevel() {
        super("getExpLevel",1);
    }
    public Integer run(String[] args) {
        return null;
    }
}
