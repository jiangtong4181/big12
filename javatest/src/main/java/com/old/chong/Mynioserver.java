package com.old.chong;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Mynioserver {
    public static void main(String[] args) throws Exception {
        //打开一个服务器通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 8888);
        ssc.bind(addr);
        //配置非阻塞
        ssc.configureBlocking(false);
        //打开挑选器
        Selector sel = Selector.open();
        //注册到挑选器
        ssc.register(sel, SelectionKey.OP_ACCEPT);
        for (; ; ) {
            //开始挑选
            sel.select();
            Set<SelectionKey> keys = sel.selectedKeys();
            //得到挑选出来的集合
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                //进行迭代
                SelectionKey key = it.next();
                try {
                    if (key.isAcceptable()) {
                        SocketChannel sock = ssc.accept();
                        sock.configureBlocking(false);
                        sock.register(sel, SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
                    }
                    if (key.isReadable()) {
                        SocketChannel sc0 = (SocketChannel) key.channel();
                        String msg = getstring(sc0);
                        System.out.println(getaddr(sc0) + "_发来消息: " + msg);
                    }
                    if (key.isConnectable()) {
                        SocketChannel sock = (SocketChannel) key.channel();
                        System.out.println(getaddr(sock) + " 可连接");
                        sock.finishConnect();
                    }
                    if (key.isWritable()) {
                        SocketChannel sc0 = (SocketChannel) key.channel();
                        Date date = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String date1 = format.format(date);
                        ByteBuffer buf = ByteBuffer.wrap(date1.getBytes());
                        sc0.write(buf);
                    }
                } catch (Exception e) {
                    //如果key出问题就撤销
                    key.cancel();
                }finally {
                    //不管这个key成功还是失败都要移除
                    it.remove();
                }
            }
        }
    }
        public static String getstring (SocketChannel sc) throws Exception {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (sc.read(buf) != 0) {
                buf.flip();
                baos.write(buf.array(), 0, buf.limit());
                buf.clear();
            }
            return new String(baos.toByteArray());
        }
        public static String getaddr (SocketChannel sc) throws Exception {
            InetSocketAddress addr = (InetSocketAddress) sc.getRemoteAddress();
            String ip = addr.getAddress().getHostAddress();
            int port = addr.getPort();
            return ip + ":" + port;
        }
}