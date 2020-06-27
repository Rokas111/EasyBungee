package main.data.mongo;

import main.data.connection.Connection;
import main.data.exception.ConnectionException;
import main.data.login.MongoLogin;

public class MongoConnection extends Connection<MongoLogin> {
    public MongoConnection(MongoLogin login) throws ConnectionException {
        super(login);
        //client = new MongoClient( "localhost" , 27017 )
    }

    public boolean isConnected() {
        return false;
    }
    public void close() {
    }
}
