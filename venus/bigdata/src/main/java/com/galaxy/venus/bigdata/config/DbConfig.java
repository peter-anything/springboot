package com.galaxy.venus.bigdata.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by STM on 2020/5/18.
 */
public class DbConfig {

    private DbConfig() {
    }

    /********** HikariDataSource 默认配置 开始 *************/

    // 最大连接数
    public final static int MAXIMUM_POOL_SIZE = 30;
    // 控制池中连接的最大生存期
    public final static int MAX_LIFETIME = 300000;
    // 控制超时时间，超过该时间报 SQLException
    public final static int CONNECTION_TIMEOUT = 50000;
    // 控制池中维护的最小空闲连接数
    public final static int MINIMUM_IDLE = 10;
    // 连接允许在池中闲置的最长时间
    public final static int IDLE_TIMEOUT = 60000;

    // 开启事务自动提交
    public final static boolean AUTO_COMMIT = true;
    // 是否自定义配置，为true时下面两个参数才生效
    public final static boolean CACHE_PREP_STMTS = true;
    // 缓存条数默认25，官方推荐250-500
    public final static int PREP_STMT_CACHE_SIZE = 250;
    // 单条语句最大长度默认256，官方推荐2048
    public final static int PREP_STMT_CACHE_SQL_LIMIT = 2048;


    /********** HikariDataSource 默认配置 结束 *************/


    public final static String SOURCE_TYPE = "sourceType";

    public final static String SOURCE_ID = "sourceId";

    public final static String PRINCIPAL = "KG_PRINCIPAL";

    public final static String KEYTAB_PATH = "KG_KEYTAB_FILE";

    public final static String HADOOP_SECURITY_AUTHENTICATION = "hadoop.security.authentication";

    public final static String JAVA_SECURITY_AUTH_LOGIN_CONFIG = "java.security.auth.login.config";

    public final static String JAVA_SECURITY_KRB5_CONF = "java.security.krb5.conf";

    public static final String KRB5_CONF_PATH = "/app/conf/krb5.conf";

    public static final String JASS_CONF_PATH = "/app/conf/jaas.conf";

    public final static String QUORUM = "quorum";

    public final static String CLIENT_PORT = "clientPort";

    public final static String DRIVER = "driver";

    public final static String URL = "url";

    public final static String USER_NAME = "userName";

    public final static String PASSWORD = "password";

    public final static String HOST = "host";

    public final static String PORT = "port";

    public final static String DATABASE = "database";

    public final static String HBASE_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";

    public final static String HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT = "hbase.zookeeper.property.clientPort";

    public final static String KERBEROS = "kerberos";

    public final static String ERROR_MSG_1 = "参数配置有误！";

    public final static String ERROR_MSG_2 = "数据库操作失败！";

    public final static String ERROR_MSG_3 = "column格式有误！";

    public final static String AGGREGATEIMPLEMENTATION_CLASS = "org.apache.hadoop.hbase.coprocessor.AggregateImplementation";

    public final static String DEFAULT = "default";


    /**************sql expr begin*****************/
    public final static String SELECT_ALL_FROM = "select * from ";

    public final static String SELECT_COUNT_ALL_FROM = "select count(*) from ";

    public final static String SELECT = "select ";

    public final static String FROM = " from ";

    public final static String LIMIT = " limit ";

    public final static String COMMA = ",";

    public final static String ALL = " * ";

    public final static String GP_DEFAULT_SCHEMA = "public";

    public final static String GP_GET_SCHEMA_SQL = "select nspname from pg_namespace";

    public final static String GP_GET_COLUMNS_SQL = "select column_name from information_schema.columns where table_schema = ''{0}'' and table_name = ''{1}''";

    public final static String GP_GET_TABLENAME_SQL = "select distinct(table_name) from information_schema.columns where table_schema=''{0}''";

    public final static List<String> GP_SYSTEM_SCHEMA = new ArrayList<>(Arrays.asList(
            "pg_catalog", "pg_toast", "pg_bitmapindex", "pg_aoseg", "information_schema", "gp_toolkit", "pg_temp_1", "pg_toast_temp_1"));

    public final static String MYSQL_GET_TABLENAME_SQL = "show tables";

    public final static String MYSQL_GET_COLUMNS_SQL = "select column_name from information_schema.columns where table_name =''{0}''";

    public final static String ORACLE_GET_TABLENAME_SQL = "select table_name from user_tables";

    public final static String ORACLE_GET_TABLE_COLUMN_SQL = "select column_name from user_tab_columns where Table_Name=''{0}''";

    public final static String SQLITE_GET_TABLENAME_SQL = "select tbl_name from sqlite_master where type='table'";

    public final static String DAMENG_GET_TABLENAME_SQL = "select table_name from user_tables";

    public final static String DAMENG_GET_TABLE_COLUMN_SQL = "select column_name from user_tab_columns where table_name = ''{0}''";

    public final static String SQLSERVER_GET_TABLENAME_SQL = "select Name FROM SysObjects Where XType='U' orDER BY Name";

    public final static String SQLSERVER_GET_TABLE_COLUMN_SQL = "select Name FROM SysColumns Where id=Object_Id(''{0}'')";

    public final static String ORACLE_GET_CONDITION = " where rownum < ";

    public final static String ORACLE_MINUS = " minus ";

    public final static String SQLITE_DRIVER_PREFIX = "jdbc:sqlite:";

    public final static String VIRGULE = "/";

    public final static String COLON = ":";

    /**************sql expr end*****************/
}
