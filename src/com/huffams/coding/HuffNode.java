package com.huffams.coding;

class HuffNode extends HuffTree {

    private HuffTree left;
    private HuffTree right;

    public HuffNode(int frequency, HuffTree left, HuffTree right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    public HuffTree getLeft() {
        return left;
    }

    public HuffTree getRight() {
        return right;
    }

}
