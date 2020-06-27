package main.data.mysql.adapter;

public interface MySQLAdapter<T> {
    String getName();
    SQLTable toMySQL(T v);
    T fromMySQL(SQLTable table);
}
