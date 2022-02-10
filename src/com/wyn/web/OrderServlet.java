package com.wyn.web;

import com.wyn.pojo.Cart;
import com.wyn.pojo.User;
import com.wyn.service.OrderService;
import com.wyn.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser != null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        Integer loginUserId = loginUser.getId();
        String orderId = orderService.createOrder(cart, loginUserId);
        request.getSession().setAttribute("orderId", orderId);
        //考虑表单重复提交的问题使用 重定向
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }


}
