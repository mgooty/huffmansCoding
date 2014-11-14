package com.huffams.coding;

import java.util.Comparator;

public class HuffTreeComparator implements Comparator<HuffTree> {

    @Override
    public int compare(HuffTree ht1, HuffTree ht2) {
        if (ht1.frequency == ht2.frequency) {
          // The objects are in a tie based on the frequency
          // value. Return a tiebreaker value based on the
          // relative hashCode values of the two objects.
          return (ht1.hashCode() - ht2.hashCode());
      } else {
          return ht1.frequency - ht2.frequency;
      }
    }

}
