package com.wyn.web;

import com.wyn.pojo.User;
import com.wyn.service.UserService;
import com.wyn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    //<editor-fold desc="Description">
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if ("abcde".equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                System.out.println("用户名【" + username + "】已存在！");

                //回显信息，保存到Ruquest域中
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                //跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

            }else{
                //回显信息，保存到Ruquest域中
                request.setAttribute("msg","验证码错误");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                userService.registUser(new User(null, username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            }
        }else{

        }

    }
    //</editor-fold>
}
