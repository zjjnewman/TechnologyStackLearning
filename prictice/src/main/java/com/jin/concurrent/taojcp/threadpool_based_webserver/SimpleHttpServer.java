package com.jin.concurrent.taojcp.threadpool_based_webserver;


import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.io.*;
import java.net.Socket;

/**
 * 4-21
 *
 * {@link java.util.concurrent.FutureTask}
 *
 * {@link java.util.concurrent.Executors}
 *
 * 接口继承关系
 * {@link java.util.concurrent.Executor}
 *      {@link java.util.concurrent.ExecutorService} 接口
 *          {@link java.util.concurrent.AbstractExecutorService}抽象实现
 *              {@link java.util.concurrent.ThreadPoolExecutor}
 *                  {@link java.util.concurrent.ScheduledThreadPoolExecutor }
 *                  implements {@link java.util.concurrent.ScheduledExecutorService}
 *
 * {@link java.lang.Runnable}
 * {@link java.util.concurrent.Future}
 *      {@link java.util.concurrent.RunnableFuture}
 *          {@link java.util.concurrent.FutureTask}
 *
 * {@link java.lang.Runnable}
 * {@link java.util.concurrent.Future}
 *      {@link java.util.concurrent.RunnableFuture}
 * {@link java.util.concurrent.Delayed extends Comparable<Delayed>}
 * {@link java.util.concurrent.Future}
 *      {@link java.util.concurrent.ScheduledFuture}
 *          {@link java.util.concurrent.RunnableScheduledFuture}
 *              {@link java.util.concurrent.ScheduledThreadPoolExecutor.ScheduledFutureTask}
 *
 *
 * {@link java.util.Timer}
 *
 */
public class SimpleHttpServer {


    static class HttpRequestHandler implements Runnable{
        private Socket socket;
        public HttpRequestHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
