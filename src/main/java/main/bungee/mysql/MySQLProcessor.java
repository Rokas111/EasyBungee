package main.bungee.mysql;

import main.EasyBungee;
import main.bungee.redis.sections.profile.Profile;
import main.data.mysql.adapter.MySQLAdapter;
import main.data.mysql.adapter.SQLTable;
import main.data.mysql.adapter.SQLValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MySQLProcessor {
    public static void store(Object object) {
        if (!MySQLManager.ADAPTERS.containsKey(object.getClass())) return;
        Connection connection = null;
        try {
            connection = EasyBungee.mySQLConnection.getHikari().getConnection();
            PreparedStatement ps1 = connection.prepareStatement(checkObjectTable(object));
            PreparedStatement ps2 = connection.prepareStatement(storeObject(object));
            ps1.execute();
            ps1.close();
            ps2.execute();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void remove(Object object) {
        if (!MySQLManager.ADAPTERS.containsKey(object.getClass())) return;
        Connection connection = null;
        try {
            connection = EasyBungee.mySQLConnection.getHikari().getConnection();
            PreparedStatement ps = connection.prepareStatement(removeObject(object));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static boolean contains(Class<?> clz) {
        if (!MySQLManager.ADAPTERS.containsKey(clz)) return false;
        return !getObjects(clz).isEmpty();
    }
    public static <T> List<T> getObjects(Class<T> clz) {
        if (!MySQLManager.ADAPTERS.containsKey(clz)) return new ArrayList<>();
        Connection connection=null;
        ResultSet set = null;
        PreparedStatement ps= null;
        try {
            MySQLAdapter<T> adapter = getAdapter(clz);
            connection = EasyBungee.mySQLConnection.getHikari().getConnection();
            ps = connection.prepareStatement(attainObjects(clz));
            set = ps.executeQuery();
            List<T> elements = new ArrayList<>();
            while (set.next()) {
                elements.add(adapter.fromMySQL(new SQLTable(set)));
            }
            return elements;
        } catch (SQLException e) {
            return new ArrayList<>();
        } finally {
            try {
                if (ps != null) {ps.close();}
                if (set != null) {set.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void clear(Class<?> clz) {
        Connection connection = null;
        try {
            connection = EasyBungee.mySQLConnection.getHikari().getConnection();
            PreparedStatement ps = connection.prepareStatement(clearObjectTable(clz));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static String checkObjectTable(Object obj) {
        MySQLAdapter adapter = getAdapter(obj.getClass());
        return "CREATE TABLE IF NOT EXISTS " +adapter.getName() + "(" + adapter.toMySQL(obj).getKeys().keySet().stream().map(key-> " " + key + " " + adapter.toMySQL(obj).getKeys().get(key).getType().getStringType() + " NOT NULL ").collect(Collectors.joining(",")) + ", PRIMARY KEY ("+SQLTable.PRIMARY_KEY+"))";
    }
    private static String storeObject(Object obj) {
        MySQLAdapter adapter = getAdapter(obj.getClass());
        return "INSERT INTO " +adapter.getName() + " ("+adapter.toMySQL(obj).getKeys().keySet().stream().collect(Collectors.joining(","))+")" + " VALUES(" + adapter.toMySQL(obj).getKeys().values().stream().map(SQLValue::getValue).map(key -> "'" + key + "'").collect(Collectors.joining(",")) + ")";
    }
    private static String containsObject(Class<?> clz) {
        MySQLAdapter adapter = getAdapter(clz);
        return "SHOW TABLE LIKE " + adapter.getName();
    }
    private static String attemptObjectSearch(Object obj) {
        MySQLAdapter adapter = getAdapter(obj.getClass());
        return "SELECT " + adapter.toMySQL(obj).getKeysWithoutPrimary().keySet().stream().collect(Collectors.toList()).get(0) + " FROM " + adapter.getName() + " WHERE " + SQLTable.PRIMARY_KEY + "=" + adapter.toMySQL(obj).getPrimaryValue().getValue();
    }
    private static String attainObjects(Class<?> clz) {
        MySQLAdapter adapter = getAdapter(clz);
        return "SELECT * FROM " + adapter.getName();
    }
    private static String removeObject(Object obj) {
        MySQLAdapter adapter = getAdapter(obj.getClass());
        return "DELETE FROM " + adapter.getName() + " WHERE " + SQLTable.PRIMARY_KEY + "=" + adapter.toMySQL(obj).getPrimaryValue().getValue();
    }
    private static String clearObjectTable(Class<?> clz) {
        MySQLAdapter adapter = getAdapter(clz);
        return "DROP TABLE "+ adapter.getName();
    }
    public static <T> MySQLAdapter<T> getAdapter(Class<T> clz) {
        if (!MySQLManager.ADAPTERS.containsKey(clz) && MySQLManager.ADAPTERS.keySet().stream().noneMatch(adapterClass -> clz.getSuperclass().equals(adapterClass))) return null;
        return (MySQLAdapter<T>) (MySQLManager.ADAPTERS.keySet().stream().noneMatch(adapterClass -> clz.getSuperclass().equals(adapterClass))?MySQLManager.ADAPTERS.get(clz):MySQLManager.ADAPTERS.keySet().stream().filter(adapterClass -> clz.getSuperclass().equals(adapterClass)).findFirst().orElse(null));
    }
}
