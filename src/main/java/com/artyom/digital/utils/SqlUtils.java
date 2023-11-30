package com.artyom.digital.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class SqlUtils {
    public static String insert(String tableName, String... field) {
        var fieldList = Arrays.asList(field);
        var params = String.join(",", fieldList);
        var values = fieldList.stream().map(it -> ":" + it).collect(Collectors.joining(","));
        return "INSERT INTO " + tableName + " (" + params + ") " + "VALUES (" + values + ");";
    }

    public static String update(String nameId, String tableName, String ...fields) {
        var fieldList = Arrays.asList(fields);
        var values = fieldList.stream().map(it-> it + "=:" + it).collect(Collectors.joining(", "));
        return "UPDATE " + tableName +
                " SET " + values +
                " WHERE " + nameId + " = id";
    }

    public static String getByColumn(String tableName, String column) {
        return "SELECT * FROM " + tableName +
                " WHERE " + column + "= ?";
    }

    public static String getAll(String tableName) {
        return "SELECT * FROM " + tableName;
    }
}
