package com.felink.netframework.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/12/28.
 */


public class ThreadPoolManager {

    public static ThreadPoolManager instance = new ThreadPoolManager();

    private ThreadPoolExecutor threadPoolExecutor;

    private LinkedBlockingQueue<Future<?>> service = new LinkedBlockingQueue<Future<?>>();

    private ThreadPoolManager(){
        threadPoolExecutor = new ThreadPoolExecutor(4,10,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),handler);

        threadPoolExecutor.execute(runnable);
    }


    public static  ThreadPoolManager getInstance(){
        return instance;
    }


    public void execute(FutureTask futureTask){
        threadPoolExecutor.execute(futureTask);
    }


    private RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                service.put(new FutureTask<Object>(r,null) {
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                FutureTask task = null;
                try {
                    task = (FutureTask) service.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (task != null){
                    task.run();
                }
            }
        }
    };
}
