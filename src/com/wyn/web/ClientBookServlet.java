package com.wyn.web;

import com.wyn.pojo.Book;
import com.wyn.pojo.Pages;
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
public class ClientBookServlet extends BaseServlet{
    private BookService bookService= new BookServiceImpl();
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Pages.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Pages<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3 保存Page对象到Request域中
        request.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request ,response);
    }
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Pages.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Pages<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格的参数,追加到分页条的地址参数中
        if (request.getParameter("min") != null) {
            sb.append("&min=").append(request.getParameter("min"));
        }
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (request.getParameter("max") != null) {
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //page.setUrl("client/bookServlet?action=pageByPrice");
        //3 保存Page对象到Request域中
        request.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request ,response);
    }
}
