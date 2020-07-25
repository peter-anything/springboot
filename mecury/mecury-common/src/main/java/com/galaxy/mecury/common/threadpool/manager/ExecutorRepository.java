package com.galaxy.mecury.common.threadpool.manager;

import com.galaxy.mecury.common.URL;
import com.galaxy.mecury.common.extension.SPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

@SPI("default")
public interface ExecutorRepository {
    ExecutorService createExecutorIfAbsent(URL url);

    ExecutorService getExecutor(URL url);

    void updateThreadpool(URL url, ExecutorService executor);

    ScheduledExecutorService ScheduledExecutor();

    ScheduledExecutorService getServiceScheduledExecutor();

    ExecutorService getSharedExecutor();
}
