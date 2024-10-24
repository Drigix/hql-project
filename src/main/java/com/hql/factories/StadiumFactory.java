package com.hql.factories;


import com.hql.entities.Stadium;

public class StadiumFactory {
    private static final String STADIUM = "STADIUM_";

    public static Stadium generateStadium(int index) {
        return new Stadium(STADIUM + index, STADIUM + index);
    }
}
