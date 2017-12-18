package com.example.holidaystatistics.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String login = "login";
        String root = "/";
        String webjars = "webjars";
        String css = "css";
        String js = "js";
        String admin = "admin";
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userType = (String) request.getSession().getAttribute("usertype");
        String uri = request.getRequestURI();

        if (userType != null) {
            flag = false;
            if(Objects.equals(uri, root))
            {
                if(Objects.equals(userType, admin))
                {
                    response.sendRedirect("/admin_home");
                }else {
                    response.sendRedirect("/student_home");
                }
            }
        }

        if (uri.contains(login) || Objects.equals(uri, root) || uri.contains(webjars)
                || uri.contains(css) || uri.contains(js)) {
            flag = false;
        }
        if (flag) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}