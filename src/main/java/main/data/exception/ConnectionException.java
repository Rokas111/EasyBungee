package main.data.exception;

public class ConnectionException extends Exception {
    public ConnectionException() {
        super("Couldn't connect to the database");
    }
}
