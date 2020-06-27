package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ObtainCmd;

public class GetFood extends ObtainCmd<Integer> {
    public GetFood() {
        super("getFood",1);
    }
    public Integer run(String[] args) {
        return null;
    }
}
