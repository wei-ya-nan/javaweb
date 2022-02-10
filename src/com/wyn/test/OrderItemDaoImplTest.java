package com.wyn.test;

import com.wyn.dao.OrderItemDao;
import com.wyn.dao.impl.OrderItemDaoImpl;
import com.wyn.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通", 1, new BigDecimal(100),
                new BigDecimal(100), "1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javaScript从入门到精通", 2, new BigDecimal(100),
                new BigDecimal(200), "1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Netty入门", 1, new BigDecimal(100),
                new BigDecimal(100), "1234567891"));
    }
}