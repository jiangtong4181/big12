package com.old.chong;

import org.junit.Test;
import sun.misc.Cleaner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class copy1 {
@Test
public void test2() throws Exception {
    ByteBuffer buf = ByteBuffer.allocateDirect(1024 * 1024 * 500);
    Class<Cleaner> cleanerClass = Cleaner.class;
    Method clean = cleanerClass.getDeclaredMethod("clean");
    clean.setAccessible(true);
    //clean.invoke(array);
//    Cleaner o = constructor.newInstance(null,null);
//    o.clean();

}



    @Test
    public  void copytest() throws IOException {
        long start=System.currentTimeMillis();
        FileInputStream fin = new FileInputStream("d:\\test\\duowan_user.txt");
        FileChannel fcIn = fin.getChannel();
        FileOutputStream fout = new FileOutputStream("d:\\test1\\duowan_user2.txt");
        FileChannel fcOut = fout.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024*1024*8) ;
        while(fcIn.read(buf) != -1){
            buf.flip();
            fcOut.write(buf) ;
            buf.flip();
        }
        fcOut.close();
        fcIn.close();
        fout.close();
        fin.close();
        System.out.println(System.currentTimeMillis() - start);
    }
    @Test
    public  void copytest2() throws IOException {
        long start=System.currentTimeMillis();
        FileInputStream fin = new FileInputStream("d:\\test\\duowan_user.txt");
        FileChannel fcIn = fin.getChannel();
        FileOutputStream fout = new FileOutputStream("d:\\test1\\duowan_user3.txt");
        FileChannel fcOut = fout.getChannel();
        ByteBuffer buf = ByteBuffer.allocateDirect(1024*1024*8) ;
        while(fcIn.read(buf) != -1){
            buf.flip();
            fcOut.write(buf) ;
            buf.flip();
        }
        fcOut.close();
        fcIn.close();
        fout.close();
        fin.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
