package com.old.chong;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Testnio {
    public static void main(String[] args) throws Exception {
        FileInputStream fin = new FileInputStream("d:\\test\\temptags.txt");
        FileChannel fcin = fin.getChannel();
        FileOutputStream fout = new FileOutputStream("d:\\test\\zhaochong.txt");
        FileChannel fcout = fout.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while(fcin.read(buf)!=-1){
            //limit设置为position，指针回到头部
            buf.flip();
            fcout.write(buf);
            //指针回到头部，limit回到capacity，mark为-1
            buf.clear();
        }
        fcin.close();
        fcout.close();
        fin.close();
        fout.close();
    }
}
