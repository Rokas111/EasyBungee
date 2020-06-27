package main.data.exception;

public class ProxyException extends Exception {
    public ProxyException() {
        super("Couldn't connect to a proxy");
    }
}
