package com.gsir.monitor.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MonitorListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(".........monitor应用启动..........");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(".........monitor应用销毁..........");
    }
}
