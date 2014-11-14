package com.huffams.coding;

public class HuffLeaf extends HuffTree {

    private int value;

    public HuffLeaf(int value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public int getValue() {
        return value;
    }

}
