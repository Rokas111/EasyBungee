package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ObtainCmd;

public class GetExp extends ObtainCmd<Float> {
    public GetExp() {
        super("getExp",1);
    }
    public Float run(String[] args) {
        return null;
    }
}
