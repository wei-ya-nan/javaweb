package com.wyn.web;

import com.google.gson.Gson;
import com.wyn.pojo.User;
import com.wyn.service.UserService;
import com.wyn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
@SuppressWarnings("all")
public class UserServlet extends BaseServlet {
    //<editor-fold desc="Description">
    private UserService userService = new UserServiceImpl();

    protected void ajaxExistUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        boolean existsUsername = userService.existsUsername(userName);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("existUserName", existsUsername);
        Gson gson = new Gson();
        String jsonString = gson.toJson(hashMap);
//        response.getWriter().write(jsonString);

    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User login = userService.login(new User(null, username, password, null));

        if (login == null) {
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        } else {
            request.getSession().setAttribute("user", login);
            request.getRequestDispatcher("pages/user/login_success.jsp").forward(request, response);

        }

    }

    protected void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
        System.out.println(req.getContextPath());
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        //获取session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if (token.equalsIgnoreCase(code) && token != null) {
            if (userService.existsUsername(username)) {
                System.out.println("用户名【" + username + "】已存在！");

                //回显信息，保存到Ruquest域中
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                //跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

            } else {
                //回显信息，保存到Ruquest域中
                request.setAttribute("msg", "验证码错误");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                userService.registUser(new User(null, username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,
                        response);

            }
        } else {
            request.setAttribute("msg", "验证码错误！！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }


    }
    //</editor-fold>
}
