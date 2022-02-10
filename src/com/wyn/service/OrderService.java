package com.wyn.service;

import com.wyn.pojo.Cart;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public interface OrderService {
    String createOrder(Cart cart,Integer userId);
}
