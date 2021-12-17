package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/7/22 8:59
 */
public class NioServer {

    public static void main(String[] args) throws IOException {

        // 创建一个在本地端口进行监听的服务Socket通道.并设置为非阻塞方式
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(5233));

        // 创建一个选择器selector
        Selector selector = Selector.open();
        // 把ServerSocketChannel注册到selector上，并且selector对客户端accept连接操作感兴趣
        //这个key会在后续发生时间的时候 出现在 selector.selectedKeys()
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 轮询监听channel里的key，select是阻塞的，accept()也是阻塞的
            selector.select();
            // 有客户端请求，被轮询监听到，包括客户端连接，数据发送
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                handler(key);
            }

        }


    }

    private static void handler(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            // key.channel() 返回的是 对应channel调用register注册的那个channel,这里是ServerSocketChannel
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            //accept 返回新客户端连接channel
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            //将心客户端连接注册到selector
            SelectionKey selectionKey = socketChannel.register(key.selector(), SelectionKey.OP_READ);
            System.out.println("新客户端连接");
        } else if (key.isReadable()) {
            //这里返回的是注册的那个客户端
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (socketChannel.read(buf) > 0) {
                System.out.println("客户端发来数据：" + new String(buf.array()));
            }
            ByteBuffer writeBuffer = ByteBuffer.wrap("from server".getBytes());
            socketChannel.write(writeBuffer);
        } else if (key.isWritable()) {

        }
    }

}
