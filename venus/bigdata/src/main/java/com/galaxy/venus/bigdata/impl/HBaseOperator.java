package com.galaxy.venus.bigdata.impl;

import com.galaxy.venus.bigdata.IHBaseOperator;
import com.galaxy.venus.bigdata.control.RateLimiterControl;
import com.galaxy.venus.bigdata.factory.HBaseConnectionFactory;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.galaxy.venus.bigdata.factory.HBaseConnectionFactory.getConnection;

@Service
public class HBaseOperator implements IHBaseOperator {
    private static final Logger logger = LogManager.getLogger();
    // TODO maybe 交给容器管理
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    @Resource
    private RateLimiterControl ctrl;

    @Override
    public void upload(String hbaseName, String hbaseColumnFamily, byte[] rowKey, MultipartFile file, int bufferSize) throws Exception {
        String originalFilename = file.getOriginalFilename();
        Connection connection = getConnection(hbaseName, hbaseColumnFamily);
        try (Table table = connection.getTable(TableName.valueOf(hbaseName))) {
            try (InputStream inputStream = file.getInputStream()) {
                byte[] buffer = new byte[bufferSize];
                int index = 0;
                for (; ; ) {
                    int read = inputStream.read(buffer);
                    if (read != -1) {
                        Put put = new Put(rowKey);
                        byte[] bufferInput;
                        if (read == bufferSize) {
                            bufferInput = buffer;
                        } else {
                            bufferInput = new byte[read];
                            System.arraycopy(buffer, 0, bufferInput, 0, read);
                        }
                        put.addColumn(hbaseColumnFamily.getBytes(), ("" + index++).getBytes(), bufferInput);
                        table.put(put);
                        logger.info("保存了文件{}的第{}个切片的数据,切片大小为{}字节", originalFilename, index, bufferInput.length);
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
            logger.error("文件保存失败:{}", originalFilename);
            throw e;
        }
    }

    @Override
    public void download(String hbaseName, String hbaseColumnFamily, final byte[] rowKey, OutputStream out) throws Exception {
        download(hbaseName, hbaseColumnFamily, rowKey, out, true);
    }


    @Override
    public void download(String hbaseName, String hbaseColumnFamily, final byte[] rowKey, OutputStream out, boolean limit) throws Exception {
        StopWatch sw = new StopWatch();
        sw.start();
        int total = 0;
        Connection connection = getConnection(hbaseName, hbaseColumnFamily);
        try (final Table table = connection.getTable(TableName.valueOf(hbaseName))) {
            byte[] value;
            int bufferSize = ctrl.getBufferSize();
            Result result;
            int index = 1;
            for (Future<Result> task = submitDownLoadQuery(table, hbaseColumnFamily, rowKey, "0"); ; index++) {
                result = task.get();
                if ((result == null || result.isEmpty())) {
                    break;
                }
                final String columnStart = "" + index;
                task = submitDownLoadQuery(table, hbaseColumnFamily, rowKey, columnStart);
                value = result.getValue(hbaseColumnFamily.getBytes(), ("" + (index - 1)).getBytes());
                total += value.length;
                int downLoadIndex = 0;
                while (downLoadIndex < value.length) {
                    if (limit) {
                        ctrl.getRate();
                    }
                    int len = ((downLoadIndex + bufferSize) <= value.length) ? bufferSize : (value.length - downLoadIndex);
                    out.write(value, downLoadIndex, len);
                    out.flush();
                    downLoadIndex = downLoadIndex + bufferSize;
                }
            }
            sw.stop();
            logger.info("下载一共耗时{}s,速率为{}kb/s", sw.getTime() / 1000, total / 1024 * 1000 / sw.getTime());

        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    private Future<Result> submitDownLoadQuery(final Table table, String hbaseColumnFamily, final byte[] rowKey, final String columnIndex) {
        return executor.submit(() -> {
            try {
                logger.info("hbase下载文件....正在下载第{}个切片", columnIndex);
                Get get = new Get(rowKey);
                get.addColumn(hbaseColumnFamily.getBytes(), columnIndex.getBytes());
                return table.get(get);
            } catch (Exception e) {
                logger.error(e);
                return null;
            }
        });
    }


    private Connection getConnection(String hbaseName, String hbaseColumnFamily) throws Exception {
        int retry = 0;
        Connection connection;
        do {
            connection = HBaseConnectionFactory.getConnection(new HashMap<>());
            if (retry > 0 && retry < 6) {
                logger.error("获取hbase连接失败,重试第{}次(最多重试5次)....", retry);
            }
        } while (retry++ <= 5 && connection == null);
        if (retry > 5) {
            logger.error("获取hbase连接失败,已重试5次,终止任务 ....");
            throw new Exception("获取hbase连接失败");
        }
        try (Admin admin = connection.getAdmin()) {
            //没有hbase则创建
            if (!admin.tableExists(TableName.valueOf(hbaseName))) {
                synchronized (HBaseOperator.class) {
                    if (!admin.tableExists(TableName.valueOf(hbaseName))) {
                        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(hbaseName));
                        tableDescriptor.addFamily(new HColumnDescriptor(hbaseColumnFamily));
                        admin.createTable(tableDescriptor);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return connection;
    }

    @Override
    public void saveInSingleColumn(String hbaseName, String hbaseColumnFamily, String column, byte[] rowKey, MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        Connection connection = getConnection(hbaseName, hbaseColumnFamily);
        try (Table table = connection.getTable(TableName.valueOf(hbaseName))) {
            byte[] bytes = file.getBytes();
            Put put = new Put(rowKey);
            put.addColumn(hbaseColumnFamily.getBytes(), column.getBytes(), bytes);
            table.put(put);
            logger.info("保存了文件{}的第{}个切片的数据", originalFilename, column);
        } catch (Exception e) {
            logger.error(e);
            logger.error("文件保存失败:{}", originalFilename);
            throw e;
        }
    }
}
