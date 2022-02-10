package com.wyn.test;


import com.wyn.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class TestBook {
    @Test
    public void testJdbc() {
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.getClose(connection);
        }

    }
}
