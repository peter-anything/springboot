package com.galaxy.venus.bigdata.factory;

import com.galaxy.venus.bigdata.util.ConnectThreadService;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.galaxy.venus.bigdata.config.DbConfig.*;

public class HBaseConnectionFactory {
    private final static Logger LOGGER = LogManager.getLogger();

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static Configuration getConfig(Map<String, Object> map) {

        Configuration configuration = HBaseConfiguration.create();
        configuration.addResource("hbase-site.xml");

        // 参数覆盖
        if (map.getOrDefault(QUORUM, null) != null) {
            configuration.set(HBASE_ZOOKEEPER_QUORUM, map.get(QUORUM).toString());
        }
        if (map.getOrDefault(CLIENT_PORT, null) != null) {
            configuration.set(HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT, map.get(CLIENT_PORT).toString());
        }
        return configuration;
    }


    public static Connection getConnection(Map<String, Object> map) throws Exception {
        ExecutorService executorService = ConnectThreadService.getInstance().getExecutorService();
        Future<Pair<String, Connection>> future = executorService.submit(new HBaseConnectionCaller(map, connectionMap));
        Pair<String, Connection> pair;
        String timeout = null;
        if (timeout == null) {
            timeout = "50000";
        }
        try {
            pair = future.get(Long.parseLong(timeout), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception("HBase connection创建失败");
        }
        connectionMap.put(pair.getLeft(), pair.getRight());
        return pair.getRight();
    }

    public static Connection getConnection(String quorum, String clientPort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(QUORUM, quorum);
        map.put(CLIENT_PORT, clientPort);
        return getConnection(map);
    }
}
