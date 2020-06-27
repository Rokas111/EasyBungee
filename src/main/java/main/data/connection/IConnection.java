package main.data.connection;

public interface IConnection {
    boolean isConnected();
    void close();
}
