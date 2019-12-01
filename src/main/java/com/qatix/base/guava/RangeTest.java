package com.qatix.base.guava;

import com.google.common.primitives.Ints;
import org.apache.curator.shaded.com.google.common.collect.ContiguousSet;
import org.apache.curator.shaded.com.google.common.collect.DiscreteDomain;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSortedSet;
import org.apache.curator.shaded.com.google.common.collect.Range;

public class RangeTest {
    public static void main(String[] args) {

        System.out.println(Range.closed(1, 3).contains(2)); // returns true
        System.out.println(Range.closed(1, 3).contains(4)); // returns false
        System.out.println(Range.lessThan(5).contains(5)); // returns false
        System.out.println(Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3))); // returns true


        Range.closed(3, 5).isConnected(Range.open(5, 10)); // returns true
        Range.closed(0, 9).isConnected(Range.closed(3, 4)); // returns true
        Range.closed(0, 5).isConnected(Range.closed(3, 9)); // returns true
        Range.open(3, 5).isConnected(Range.open(5, 10)); // returns false
        Range.closed(1, 5).isConnected(Range.closed(6, 10)); // returns false


        Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
        Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
        Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
//        Range.open(3, 5).intersection(Range.open(5, 10)); // throws IAE java.lang.IllegalArgumentException
//        Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IAE java.lang.IllegalArgumentException

        ImmutableSortedSet<Integer> set = ContiguousSet.create(Range.open(1, 5), DiscreteDomain.integers());
        // set contains [2, 3, 4]
        for (Integer i : set) {
            System.out.println(i);
        }
    }
}
