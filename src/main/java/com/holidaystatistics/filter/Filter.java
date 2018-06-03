package com.holidaystatistics.filter;

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
        String loginUri = "login";
        String rootUri = "/";
        String webjarsUri = "webjars";
        String cssUri = "css";
        String jsUri = "js";
        String managerUri = "manager";
        String studentUri = "student";
        String publicUri = "public";
        String openApiUri = "openApi";


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userType = (String) request.getSession().getAttribute("userType");
        String uri = request.getRequestURI();

        if (userType != null) {
            if (Objects.equals(uri, rootUri)) {
                if (Objects.equals(userType, managerUri)) {
                    response.sendRedirect("/manager/home");
                } else if (Objects.equals(userType, studentUri)) {
                    response.sendRedirect("/student/home");
                }
            }
            if (uri.contains(userType)) {
                flag = false;
            }
        }

        if (uri.contains(loginUri) || Objects.equals(uri, rootUri) || uri.contains(webjarsUri)
                || uri.contains(cssUri) || uri.contains(jsUri) || uri.contains(openApiUri)
                || uri.contains(publicUri)) {
            flag = false;
        }
        if (flag) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}