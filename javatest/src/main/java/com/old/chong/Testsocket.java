package com.old.chong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class Testsocket {
    public static void main(String[] args) throws IOException {
        ServerSocket ssock = new ServerSocket(8888);
        while(true){
            System.out.println("开始接受");
            Socket sock = ssock.accept();
            String host = getaddr(sock);
            System.out.println(host+"_连进来了");
            new threadserver(sock).start();
        }
    }

    static class threadserver extends Thread{
        private Socket sock;
        public threadserver(Socket sock){
            this.sock=sock;
        }
        @Override
        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String line=null;
                while((line=br.readLine())!=null){
                    System.out.println(getaddr(sock)+"_发来消息: "+line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获得远端属性信息
    public static String getaddr(Socket sock){
        InetSocketAddress addr = (InetSocketAddress) sock.getRemoteSocketAddress();
        String ip = addr.getHostName();
        int port = addr.getPort();
        return ip+":"+port;
    }
}
