
package com.wyn.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class BaseServlet extends HttpServlet {
    //<editor-fold desc="Description">

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            Method declaredMethod = this.getClass().
                    getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println(action+"被调用了");
            declaredMethod.invoke(this, req, resp);
        } catch (NoSuchMethodException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    //</editor-fold>
}
