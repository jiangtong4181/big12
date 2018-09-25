package com.old.chong;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Testzero {
    public static void main(String[] args) throws Exception{
        long start =System.currentTimeMillis();
        File file = new File("d:\\test\\duowan_user.txt");
        FileInputStream fin = new FileInputStream(file);
        FileChannel fcin = fin.getChannel();
        FileOutputStream fos = new FileOutputStream("d:\\test\\b.dat");
        FileChannel fcout = fos.getChannel();
        fcin.transferTo(0,file.length(),fcout);
        fcout.close();
        fcin.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
