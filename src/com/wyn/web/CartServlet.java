package com.wyn.web;

import com.wyn.pojo.Book;
import com.wyn.pojo.Cart;
import com.wyn.pojo.CartItem;
import com.wyn.service.BookService;
import com.wyn.service.impl.BookServiceImpl;
import com.wyn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("请求头Referer的值：" + request.getHeader("Referer"));
        // 最后一个添加的商品名称
        request.getSession().setAttribute("lastName", cartItem.getName());
//        response.sendRedirect(request.getContextPath() + "/index.jsp");
        response.sendRedirect(request.getHeader("Referer"));
        request.getSession().setAttribute("lastName",cartItem.getName());
        System.out.println(request.getHeader("Referee"));
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // 1 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 清空购物车
            cart.clear();
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            response.sendRedirect(request.getHeader("Referer"));
        }

    }


}
