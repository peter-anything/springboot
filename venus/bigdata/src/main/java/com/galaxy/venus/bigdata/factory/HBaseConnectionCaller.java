package com.galaxy.venus.bigdata.factory;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.Callable;

import static com.galaxy.venus.bigdata.config.DbConfig.*;

public class HBaseConnectionCaller implements Callable<Pair<String, Connection>> {
    private final static Logger LOGGER = LogManager.getLogger();

    private Map<String, Object> configMap;
    private Map<String, Connection> connectionMap;

    public HBaseConnectionCaller(Map<String, Object> configMap, Map<String, Connection> connectionMap) {
        this.configMap = configMap;
        this.connectionMap = connectionMap;
    }

    @Override
    public Pair<String, Connection> call() throws Exception {
        LOGGER.info("=== get HBase connection ===");
        Connection connection = null;
        StringBuffer sb = new StringBuffer();

        try {
            // 如果map中不含 QUORUM, 从 hbase-site.xml 中读取默认值代替
            String quorum= "localhost:2181";
            // 如果map中不含 CLIENT_PORT, 从 hbase-site.xml 中读取默认值代替
            String clientPort= "2181";
            sb.append(quorum).append(":").append(clientPort);
        } catch (Exception e) {
            LOGGER.error("解析HBase配置失败", e);
        }

        Admin admin = null;
        try {
            if (connectionMap.getOrDefault(sb.toString(), null) != null) {
                LOGGER.info("use mapped hbase connection {}", sb.toString());
                connection = connectionMap.get(sb.toString());
            } else {
                LOGGER.info("=== create connection ===");
                Configuration configuration = HBaseConnectionFactory.getConfig(configMap);
                connection = ConnectionFactory.createConnection(configuration);
                admin = connection.getAdmin();
                ClusterStatus clusterStatus = admin.getClusterStatus();
                LOGGER.info("=== clusterStatus:{} ===", clusterStatus);
            }
        } catch (Exception e) {
            LOGGER.error("HBase connection创建失败", e);
        } finally {
            if (admin != null) {
                admin.close();
            }
        }
        return Pair.of(sb.toString(), connection);
    }
}
