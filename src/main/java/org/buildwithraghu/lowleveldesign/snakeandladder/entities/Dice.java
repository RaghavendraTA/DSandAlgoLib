package org.buildwithraghu.lowleveldesign.snakeandladder.entities;

import java.util.Random;

public class Dice {

    private final static Random random = new Random();

    private final int minValue;
    private final int maxValue;

    public Dice(int minValue, int maxValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public int roll() {
        return random.nextInt(minValue, maxValue + 1);
    }
}
