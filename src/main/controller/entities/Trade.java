package main.controller.entities;

public enum Trade {
    EXPORT("EXP"), IMPORT("IMP");

    public final String dbValue;

    Trade(String dbValue) {
        this.dbValue = dbValue;
    }

    public static Trade getByDbTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            return null;
        }
        for (Trade t: Trade.values()) {
            if (t.dbValue.equals(tag)) {
                return t;
            }
        }
        return null;
    }
}
