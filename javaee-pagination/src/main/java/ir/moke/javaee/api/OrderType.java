package ir.moke.javaee.api;

import java.util.Arrays;

public enum OrderType {
    ASCEND("asc"),
    DESCEND("desc");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }

    public static boolean isExists(String str) {
        return Arrays.stream(OrderType.class.getEnumConstants()).anyMatch(item -> item.name().equalsIgnoreCase(str));
    }
}
