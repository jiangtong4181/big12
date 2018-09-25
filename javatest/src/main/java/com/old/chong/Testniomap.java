package com.old.chong;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Testniomap {
    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("d:\\test\\zhaochong.txt","rw");
        MappedByteBuffer buf = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 1, 9);
        for (int i = 1; i <1000 ; i++) {
            buf.put((byte)(97+i%10));
            if(i%9==0){
                buf.clear();
            }
        }
    }
}
