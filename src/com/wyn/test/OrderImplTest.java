package com.wyn.test;

import com.wyn.dao.OrderDao;
import com.wyn.dao.impl.OrderImpl;
import com.wyn.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class OrderImplTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderImpl();

        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }
}