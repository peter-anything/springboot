package com.galaxy.venus.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseConnection {
    protected static Connection conn;
    private static final String ZK_QUORUM = "hbase.zookeeper.quorum";
    private static final String ZK_CLIENT_PORT = "hbase.zookeeper.property.clientPort";
    private static final String HBASE_POS = "localhost";
    private static final String ZK_POS = "localhost:2181";
    private static final String ZK_PORT_VALUE = "2181";
    private static Configuration conf;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.rootdir", "hdfs://"+ HBASE_POS + ":9000/hbase");
        conf.set(ZK_QUORUM, ZK_POS);
        conf.set(ZK_CLIENT_PORT, ZK_PORT_VALUE);
        //创建连接池
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createTable(String tableName, String seriesStr) throws IOException {
        Admin admin = null;
        TableName table = TableName.valueOf(tableName);
        admin = conn.getAdmin();
        if (!admin.tableExists(table)) {
            System.out.println(tableName + " table not exists");
            HTableDescriptor descriptor = new HTableDescriptor(table);
            String[] series = seriesStr.split(",");
            for (String s : series) {
                descriptor.addFamily(new HColumnDescriptor(s.getBytes()));
            }
            admin.createTable(descriptor);
        }
    }


    public static void main(String[] args) throws IOException {
        createTable("Student", "stuInfo");
    }
}
