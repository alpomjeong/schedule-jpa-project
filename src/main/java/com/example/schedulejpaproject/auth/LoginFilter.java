package com.example.schedulejpaproject.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class LoginFilter implements Filter {

    private static final List<String> whitelist = List.of(
            "/api/users/signup",
            "/api/users/login"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        if (isWhitelisted(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (httpRequest.getSession().getAttribute("user") == null) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json;charset=UTF-8");
            httpResponse.getWriter().write("{\"code\":\"C001\",\"message\":\"로그인이 필요합니다\",\"status\":\"UNAUTHORIZED\"}");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isWhitelisted(String path) {
        return whitelist.stream().anyMatch(path::startsWith);
    }
}