package main.data.mysql.adapter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

@RequiredArgsConstructor
public class SQLTable {
    public static final String PRIMARY_KEY = "id";
    @NonNull @Getter private HashMap<String,SQLValue> keys;
    public SQLTable(ResultSet set) {
        try {
            keys = new HashMap<>();
            ResultSetMetaData metadata = set.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                keys.put(metadata.getColumnName(i), new SQLValue(SQLValueType.getTypeByDataType(metadata.getColumnType(i) != Types.LONGVARCHAR?metadata.getColumnTypeName(i):"TEXT"),set.getObject(metadata.getColumnName(i)).toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public SQLValue getPrimaryValue() {
        return keys.get(PRIMARY_KEY);
    }
    public HashMap<String,SQLValue> getKeysWithoutPrimary() {
        HashMap<String, SQLValue> keys = this.keys;
        keys.remove(PRIMARY_KEY);
        return keys;
    }

}
