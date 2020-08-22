package com.jin.concurrent.juc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {
    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(new Properties());
        druidDataSource.getConnection();


        /**
         * {@link java.util.concurrent.FutureTask}
         * execute不会吧runnable封装成FutureTask
         */
        Future future1 = threadPool.submit(new RunnableTask());
        Future future2 = threadPool.submit(new CallableTask<Object>());
        threadPool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });


    }

    private static class RunnableTask implements Runnable{
        @Override
        public void run() {

        }
    }
    private static class CallableTask<T> implements Callable {
        @Override
        public T call() throws Exception {
            return null;
        }
    }
}
