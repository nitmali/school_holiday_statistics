package com.example.holidaystatistics.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 过滤器
 *
 * @author 马小生
 */
@WebFilter(filterName = "Filter", urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        boolean flag = true;
        String LOGIN = "login";
        String ROOT = "/";
        String WEBJARS = "webjars";
        String CSS = "css";
        String JS = "js";
        String MANAGER = "manager";
        String STUDENT = "manager";
        String PUBLIC = "public";


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userType = (String) request.getSession().getAttribute("userType");
        String uri = request.getRequestURI();

        if (userType != null) {
            if (Objects.equals(uri, ROOT)) {
                if (Objects.equals(userType, MANAGER)) {
                    response.sendRedirect("/manager/home");
                } else if (Objects.equals(userType, STUDENT)){
                    response.sendRedirect("/student/home");
                }
            }
            if (uri.contains(userType)) {
                flag = false;
            }
            if (uri.contains(PUBLIC)){
                flag = false;
            }
        }

        if (uri.contains(LOGIN) || Objects.equals(uri, ROOT) || uri.contains(WEBJARS)
                || uri.contains(CSS) || uri.contains(JS)) {
            flag = false;
        }
        if (flag) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}