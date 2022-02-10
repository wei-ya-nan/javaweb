package com.wyn.test;

import com.wyn.pojo.Cart;
import com.wyn.pojo.CartItem;
import com.wyn.service.OrderService;
import com.wyn.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );

    }
}