package com.wyn.dao.impl;

import com.wyn.dao.OrderItemDao;
import com.wyn.pojo.OrderItem;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)" +
                "values(?,?,?,?,?)";

        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(),
                orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
