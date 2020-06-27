package main.data.connection;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Connection<T> implements IConnection{
    @NonNull @Getter private T login;
}
