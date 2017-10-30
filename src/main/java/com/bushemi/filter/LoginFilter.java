package com.bushemi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by igor on 23.09.17.
 * useless comment
 */
public class LoginFilter implements Filter {

    public static final String LOGIN_ATTR = "LOGIN_ATTR";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LocalDateTime loginTime = (LocalDateTime) ((HttpServletRequest) servletRequest).getSession().getAttribute(LOGIN_ATTR);
        if (loginTime == null || loginTime.isBefore(LocalDateTime.now().minusMinutes(30))){
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
