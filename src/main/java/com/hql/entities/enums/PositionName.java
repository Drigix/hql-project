package com.hql.entities.enums;

public enum PositionName {
    GOALKEEPER("goalkeeper"),
    LEFT_WING("left wing"),
    LEFT_BACK("left back"),
    LEFT_MIDFIELDER("left midfielder"),
    STRIKER("striker"),
    RESERVE("reserve");

    private final String value;

    PositionName(String value) {
        this.value = value;
    }

    // region GETTERS and SETTERS

    public String getValue() {
        return value;
    }

    // endregion
}
