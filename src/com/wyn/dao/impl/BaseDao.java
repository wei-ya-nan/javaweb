package com.wyn.dao.impl;

import com.wyn.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@SuppressWarnings("all")
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行update insert delete 语句
     *
     * @param sql
     * @param params
     * @return int
     */
    public int update(String sql, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * @param sql    数据库语句
     * @param type   返回的对象的类型
     * @param params sql 对应的参数值
     * @param <T>    返回的类型的泛型
     * @return
     */
    public <T> T queryOne(String sql, Class<T> type, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        //QueryRunner queryRunner = new QueryRunner();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 查询多个Javabean 的sql语句
     *
     * @param type 返回的对象类型
     * @param sql  数据库语句
     * @param args sql对应的数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


}
