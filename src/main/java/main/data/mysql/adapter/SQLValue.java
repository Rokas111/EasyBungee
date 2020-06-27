package main.data.mysql.adapter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SQLValue {
    @NonNull @Getter private SQLValueType type;
    @NonNull @Getter private String value;
}
