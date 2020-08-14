package com.galaxy.venus.bigdata.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConnectThreadService {
    private ConnectThreadService() {

    }

    public static ConnectThreadService getInstance() {
        return ConnectThreadServiceHolder.INSTANCE;
    }

    //使用了线程池，限定了线程数量最大为8，最小为1，如果同时运行的线程数量过多，server则会报超时异常
    // TODO maybe 交给容器管理
    private ExecutorService executorService = new ThreadPoolExecutor(
            8, 8,
            30L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    public ExecutorService getExecutorService() {
        return executorService;
    }

    private static class ConnectThreadServiceHolder {
        private static ConnectThreadService INSTANCE = new ConnectThreadService();
    }
}
