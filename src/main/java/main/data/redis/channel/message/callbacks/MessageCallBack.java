package main.data.redis.channel.message.callbacks;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MessageCallBack<T> {
    @NonNull @Getter private Class<T> referenceClass;
    @NonNull @Getter private onComplete<T> onComplete;
    public void complete(String value) {
        onComplete.onComplete(MessageValueConverter.convertValue(referenceClass,value));
    }
}
