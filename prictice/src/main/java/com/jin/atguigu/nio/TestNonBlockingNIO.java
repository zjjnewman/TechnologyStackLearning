package com.jin.atguigu.nio;

import org.junit.jupiter.api.Test;

import javax.naming.ldap.SortKey;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TestNonBlockingNIO {

    /**
     * 客户端
     */
    @Test
    public void client() throws IOException {
        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 设置成非阻塞
        socketChannel.configureBlocking(false);

        // 分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 放入数据（可以改为循环放入数据）
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            byteBuffer.put((LocalDateTime.now().toString() + "\n").getBytes());
            byteBuffer.put(scanner.next().getBytes());
            // 切换缓冲区读模式
            byteBuffer.flip();

            // 把缓冲区数据 写入通道
            socketChannel.write(byteBuffer);

            // 清空缓冲区
            byteBuffer.clear();
        }

        // 关闭通道
        socketChannel.close();
    }


    /**
     * 服务端
     * @throws IOException
     */
    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);

        // 绑定通道端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));

        // 创建选择器
        Selector selector = Selector.open();

        // 把通道注册到选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // selector循环检测通道是否就绪
        while (selector.select() > 0){
            // 有就绪通道，就获取就绪通道集合的迭代器
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            // 迭代每一个selectionKey
            while (keyIterator.hasNext()){
                // get a selected selectionKey
                SelectionKey selectionKey = keyIterator.next();
                // judge the key whether be a acceptable key
                if (selectionKey.isAcceptable()){
                    // get the key-selected Channel
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    // 切换到非阻塞模式
                    socketChannel.configureBlocking(false);

                    // register the channel into the selector
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    int len = 0;
                    // 读缓冲区，若有数据，打印
                    while ((len = socketChannel.read(byteBuffer)) > 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                keyIterator.remove();
            }

        }
    }
}
