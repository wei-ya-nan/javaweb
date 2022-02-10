package com.wyn.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    //<editor-fold desc="Description">
    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("data" +
                    ".properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
//            System.out.println(dataSource.getConnection());
//            System.out.println(dataSource);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取数据库连接池中的连接
     *
     * @return 如果返回null, 说明获取连接失败<br />有值就是获取连接成功
     */
    public static Connection getConnection() {

        //<editor-fold desc="Description">
//        Connection conn = null;
//
//        try {
//            conn = dataSource.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //</editor-fold>
        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection();//从数据库连接池获取conn对象
                conns.set(conn);//保存到ThreadLocal中，供后面的jdbc操作
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return conn;
    }
    public static void CommitAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try {
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                conn.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    //<editor-fold desc="Description">
    /**
     * 关闭连接，放回数据库连接池
     *
     * @param conn
     */
    public static void getClose(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>
    //</editor-fold>
}
