package com.wyn.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
@SuppressWarnings("all")
public class Cart {
    /**
     * kye是商品id
     * value是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改购物车里的商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
    public Integer getTotalCount(){
        Integer totaoCount = 0;
        for (Map.Entry<Integer, CartItem> itemEntry : items.entrySet()) {
            totaoCount+=itemEntry.getValue().getCount();
        }
        return totaoCount;
    }
    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
