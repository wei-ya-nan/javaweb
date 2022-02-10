package com.wyn.dao.impl;

import com.wyn.dao.OrderDao;
import com.wyn.pojo.Order;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class OrderImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)" +
                "values( ?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(),
                order.getUserId());
    }
}
