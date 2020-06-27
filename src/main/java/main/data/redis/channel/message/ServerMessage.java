package main.data.redis.channel.message;

import lombok.Getter;
import lombok.Setter;
import main.EasyBungee;
import main.data.redis.channel.cmd.IChannelCmd;
import main.data.redis.channel.message.callbacks.onComplete;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerMessage {
    @Getter private UUID id;
    @Getter private IChannelCmd cmd;
    @Getter private String server;
    @Getter private String value;
    @Getter private String responseServer;
    @Getter private MessageType type;
    public ServerMessage(IChannelCmd cmd,String server,String[] args) {
        this.id = UUID.randomUUID();
        this.type = MessageType.DIRECT;
        this.cmd = cmd;
        this.responseServer = EasyBungee.name;
        this.value = String.join(" ", args);
        this.server = server;
    }
    public ServerMessage(UUID id,String server,String value) {
        this.id = id;
        this.type = MessageType.RESPONSE;
        this.cmd = null;
        this.responseServer = server;
        this.value = value;
        this.server = server;
    }
    public ServerMessage(String rawMessage) {
        this.server = rawMessage.split("\\.")[0];
        this.type = MessageType.valueOf(rawMessage.split("\\.")[1]);
        this.responseServer = type == MessageType.DIRECT?rawMessage.split("\\.")[2]:"";
        this.id = UUID.fromString(type == MessageType.DIRECT?rawMessage.split("\\.")[3]:rawMessage.split("\\.")[2]);
        this.cmd = type == MessageType.DIRECT?EasyBungee.serverChannel.getCommands().stream().filter(command -> command.getAlias().equals(rawMessage.split("\\.")[4])).findFirst().orElse(null):null;
        this.value = type == MessageType.DIRECT?String.join(".",Stream.of(rawMessage.split("\\.")).collect(Collectors.toList()).subList(5,rawMessage.split("\\.").length)):String.join(".",Stream.of(rawMessage.split("\\.")).collect(Collectors.toList()).subList(3,rawMessage.split("\\.").length));
    }
    public String toRawString() {
        switch (type) {
            case DIRECT:
                return server +"."+type.toString() + "."+responseServer + "." +id.toString() +"."+ cmd.getAlias() + "." + value;
            case RESPONSE:
                return responseServer +"."+type.toString()+"."+id.toString() + "." + value;
            default:
                return "";
        }
    }
    public void runCommand() {
        if (type == MessageType.RESPONSE) return;
        if (cmd == null) return;
        EasyBungee.serverChannel.publish(new ServerMessage(id,responseServer,cmd.execute(value.contains(" ")?value.split(" "):new String[]{value}).toString()));
    }
}
