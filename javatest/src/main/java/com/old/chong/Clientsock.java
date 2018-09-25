package com.old.chong;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Clientsock {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1",8888);
        OutputStream out = sock.getOutputStream();
        int i=0;
        while(true){
            out.write(("hello "+i+"\r\n").getBytes());
            i++;
            out.flush();
            Thread.sleep(1000);
        }
    }
}
