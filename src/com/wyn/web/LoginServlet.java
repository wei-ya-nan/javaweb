package com.wyn.web;

import com.wyn.pojo.User;
import com.wyn.service.UserService;
import com.wyn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //<editor-fold desc="Description">
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User login = userService.login(new User(null, username, password, null));

        if (login == null) {
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        }else{

            request.getRequestDispatcher("pages/user/login_success.jsp").forward(request, response);

        }

    }
    //</editor-fold>
}
