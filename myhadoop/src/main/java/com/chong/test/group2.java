package com.chong.test;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class group2 extends WritableComparator {
    protected group2() {
        super(compkey1.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        compkey1 a1 = (compkey1) a;
        compkey1 b1 = (compkey1) b;
        return a1.getId()-b1.getId();
    }
}
