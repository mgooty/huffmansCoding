package com.huffams.coding;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;

public class CodingTree {

    // data that needs to be encoded
    private String rawData;
    private PriorityQueue<HuffTree> priorityQueue = new PriorityQueue<HuffTree>(10, new HuffTreeComparator());
    private Hashtable<Character, Integer> frequencyData = new Hashtable<Character, Integer>();
    private StringBuffer code = new StringBuffer();
    Map<Character, String> huffEncodeTable = new HashMap<Character, String>();

    public CodingTree(String rawData) {
        this.rawData = rawData;
        createFreqData();
        createLeaves();
        createHuffTree();
        createBitCodes(priorityQueue.peek());
    }

    private void createFreqData() {
        for (int cnt = 0; cnt < rawData.length(); cnt++) {
            char key = rawData.charAt(cnt);
            if (frequencyData.containsKey(key)) {
                int value = frequencyData.get(key);
                value += 1;
                frequencyData.put(key, value);
            } else {
                frequencyData.put(key, 1);
            }
        }
    }

    private void createLeaves() {
        Enumeration<Character> enumerator = frequencyData.keys();
        while (enumerator.hasMoreElements()) {
            Character nextKey = enumerator.nextElement();
            priorityQueue.add(new HuffLeaf(nextKey, frequencyData.get(nextKey)));
        }
    }

    // contains a single object of type HuffTree.
    private void createHuffTree() {
        while (priorityQueue.size() > 1) {
            HuffTree left = priorityQueue.poll();

            HuffTree right = priorityQueue.poll();

            HuffNode tempNode = new HuffNode(left.getFrequency() + right.getFrequency(), left, right);
            priorityQueue.add(tempNode);
        }
    }

    private void createBitCodes(HuffTree tree) {
        if (tree instanceof HuffNode) {
            // This is a node, not a leaf. Process it as a node.

            // Cast to type HuffNode.
            HuffNode node = (HuffNode) tree;

            // Get and save the left and right branches
            HuffTree left = node.getLeft();
            HuffTree right = node.getRight();

            code.append("0");
            createBitCodes(left);
            code.deleteCharAt(code.length() - 1);
            
            code.append("1");
            createBitCodes(right);
            code.deleteCharAt(code.length() - 1);
        } else {
            HuffLeaf leaf = (HuffLeaf) tree;
            huffEncodeTable.put((char) (leaf.getValue()), code.toString());
        }

    }
}
