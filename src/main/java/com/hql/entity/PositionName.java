package com.hql.entity;

public enum PositionName {
    GOALKEEPER("goalkeeper"),
    LEFT_WING("left wing"),
    LEFT_BACK("left back"),
    LEFT_MIDFIEDLER("left midfiedler"),
    STRIKER("striker"),
    RESERVE("reserve");

    public final String value;

    private PositionName(String value) {
        this.value = value;
    }
}
