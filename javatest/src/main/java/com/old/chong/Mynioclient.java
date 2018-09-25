package com.old.chong;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Mynioclient {
    public static void main(String[] args) throws Exception {
        SocketChannel ssc = SocketChannel.open();
        ssc.configureBlocking(false);
        InetSocketAddress addr = new InetSocketAddress("localhost", 8888);
        //链接到远程服务器
        ssc.connect(addr);
        //开启挑选器
        Selector sel = Selector.open();
        //注册感兴趣的事件到挑选器
        SelectionKey key = ssc.register(sel, SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
        int index = 0;
        while(true) {
            //开始挑选
            sel.select();
            if (key.isConnectable()) {
                System.out.println("可连接");
                ssc.finishConnect();
            }
            if (key.isWritable()) {

                String msg = "tom" + index;
                ByteBuffer buf = ByteBuffer.wrap(msg.getBytes());
                ssc.write(buf);
                index++;
            }
            //清空挑选集
            sel.selectedKeys().clear();
            Thread.sleep(1000);
        }
    }
}
