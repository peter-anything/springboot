package com.galaxy.venus.bigdata;

import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface IHBaseOperator {

    /**
     * hbase上传文件的接口，使用bufferSize字节大小的数组进行分片存储
     *
     * @param hbaseName         hbase存储文件的表名
     * @param hbaseColumnFamily hbase存储文件的列族
     * @param rowKey            rowKey
     * @param file              需要存储的文件
     * @param bufferSize        分片的大小（字节）
     */
    void upload(String hbaseName, String hbaseColumnFamily, final byte[] rowKey, MultipartFile file, int bufferSize) throws Exception;

    /**
     * hbase下载文件的接口
     *
     * @param hbaseName         hbase存储文件的表名
     * @param hbaseColumnFamily hbase存储文件的列族
     * @param rowKey            rowKey
     * @param out               需要输出的文件流
     */
    void download(String hbaseName, String hbaseColumnFamily, final byte[] rowKey, OutputStream out) throws Exception;


    void download(String hbaseName, String hbaseColumnFamily, byte[] rowKey, OutputStream out, boolean limit) throws Exception;

    /**
     * hbase上传文件，只保存在一个column里
     *
     * @param hbaseName         hbase存储文件的表名
     * @param hbaseColumnFamily hbase存储文件的列族
     * @param column            hbase存储文件的列名
     * @param rowKey            rowKey
     * @param file              需要存储的文件
     */
    void saveInSingleColumn(String hbaseName, String hbaseColumnFamily, String column, byte[] rowKey, MultipartFile file) throws Exception;


}