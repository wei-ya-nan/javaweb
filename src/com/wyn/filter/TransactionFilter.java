package com.wyn.filter;

import com.wyn.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException,
            IOException {
        try {
            chain.doFilter(req, resp);
            JdbcUtils.CommitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
