// Ryan Backa
// Jav1 - 1609
// Players
package com.ce07.backaryan.backaryan_ce07;

/**
 * Created by ryankbacka on 9/11/16.
 */
public class Players {

    String name;
    String position;
    String opponent;
    boolean isInjured;
    boolean isStarting;
    int points;

    public Players(String name, String position, String opponent, boolean isInjured, boolean isStarting, int points){
        this.name = name;
        this.position = position;
        this.opponent = opponent;
        this.isInjured = isInjured;
        this.isStarting = isStarting;
        this.points = points;
    }


}
