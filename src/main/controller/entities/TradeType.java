package main.controller.entities;

public enum TradeType {
    EXPORT("EXP"), IMPORT("IMP");

    public final String dbValue;

    TradeType(String dbValue) {
        this.dbValue = dbValue;
    }

    public static TradeType getByDbTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            return null;
        }
        for (TradeType t: TradeType.values()) {
            if (t.dbValue.equals(tag)) {
                return t;
            }
        }
        return null;
    }
}
