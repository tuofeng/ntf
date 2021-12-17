package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/7/23 9:16
 */
public class AioServer {

    public static void main(String[] args) throws IOException {

        final AsynchronousServerSocketChannel serverChannel =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9000));

        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {

                //当有新的客户端连接时，回调
                serverChannel.accept(attachment, this);
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                //客户端连接继续监听读事件
                socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {

//                        try {
//                            buffer.flip();
//                            System.out.println(new String(buffer.array(), 0, result));
//                            socketChannel.write(ByteBuffer.wrap("HelloClient".getBytes()));
//                        }catch (Exception e){}

                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) { }
                });

            }

            @Override
            public void failed(Throwable exc, Object attachment) { }

        });

    }

}
