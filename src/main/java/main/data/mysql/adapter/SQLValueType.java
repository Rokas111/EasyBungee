package main.data.mysql.adapter;

import lombok.Getter;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public enum SQLValueType {
    ID("VARCHAR(36)","VARCHAR", UUID.class, UUID::fromString),
    LONG("BIGINT(19)","BIGINT",Long.class, Long::parseLong),
    STRING("TEXT",String.class,(value) -> value),
    INTEGER("INT",Integer.class, Integer::parseInt),
    BOOLEAN("TINYINT(1)","TINYINT",Boolean.class, Boolean::parseBoolean),
    DOUBLE("DOUBLE",Double.class, Double::parseDouble);
    @Getter private String stringType;
    @Getter private Class<?> type;
    @Getter private String mySQLType;
    @Getter private SQLValueConverter converter;
    private SQLValueType(String stringType,String mySQLType,Class<?> clz,SQLValueConverter converter) {
        this.stringType = stringType;
        this.type = clz;
        this.mySQLType = mySQLType;
        this.converter = converter;
    }
    private SQLValueType(String stringType,Class<?> clz,SQLValueConverter converter) {
        this.stringType = stringType;
        this.type = clz;
        this.mySQLType = stringType;
        this.converter = converter;
    }
    private SQLValueType(String stringType,SQLValueConverter converter) {
        this.stringType = stringType;
        this.type = null;
        this.mySQLType = stringType;
        this.converter = converter;
    }
    public static SQLValueType getTypeByDataType(String datatype) {
        return Arrays.stream(values()).filter(type -> type.getMySQLType().equals(datatype)).findFirst().orElse(null);
    }
    public static SQLValueType getTypeByClass(Class<?> clz) {
        return Arrays.stream(values()).filter(type -> type.getType() != null && type.getType().equals(clz)).findFirst().orElse(null);
    }
    public interface SQLValueConverter {
        Object convert(String value);
    }
}
