package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ObtainCmd;

public class GetHealth extends ObtainCmd<Double> {
    public GetHealth() {
        super("getHealth",1);
    }
    public Double run(String[] args) {
        return null;
    }
}
