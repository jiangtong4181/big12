package com.old.chong;

public class tt {
    public static void main(String[] args) {
        int n=1024*1024*6;
        long start=System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[n];
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
