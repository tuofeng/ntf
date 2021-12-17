package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/7/22 9:14
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(5233));

        socket.getOutputStream().write("I am client".getBytes());
        socket.getOutputStream().flush();

        byte b[] = new byte[1024];
        socket.getInputStream().read(b);



    }

}
