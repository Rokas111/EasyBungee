package main.data.redis.channel.message.sender;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.EasyBungee;
import main.data.redis.channel.message.ServerMessage;
import main.data.redis.channel.message.callbacks.MessageCallBack;
import main.data.redis.channel.message.callbacks.onComplete;

@RequiredArgsConstructor
public class MessageSender<T> {
    @NonNull private ServerMessage message;
    @NonNull private Class<?> clz;
    public void send() {
        EasyBungee.serverChannel.publish(message);
    }
    public void send(onComplete<T> onComplete) {
        EasyBungee.serverChannel.addResponse(message.getId(), new MessageCallBack(clz, onComplete));
        send();
    }

}
