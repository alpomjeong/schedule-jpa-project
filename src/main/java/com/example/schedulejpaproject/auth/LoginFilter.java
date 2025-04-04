package com.example.schedulejpaproject.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 로그인 여부를 확인하는 필터 클래스입니다.
 * 화이트리스트 경로를 제외한 모든 요청에 대해 세션에 사용자 정보가 있는지 검사합니다.
 */
public class LoginFilter implements Filter {

    /**
     * 인증 없이 접근 가능한 경로 목록 (화이트리스트).
     */
    private static final List<String> whitelist = List.of(
            "/api/users/signup",
            "/api/users/login"
    );

    /**
     * 요청이 화이트리스트에 포함되지 않고 세션에 로그인 정보가 없으면 401 응답을 반환합니다.
     *
     * @param request  클라이언트 요청
     * @param response 클라이언트 응답
     * @param chain    필터 체인
     * @throws IOException      입출력 예외
     * @throws ServletException 서블릿 예외
     */
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

    /**
     * 요청 경로가 화이트리스트에 포함되는지 확인합니다.
     *
     * @param path 요청 경로
     * @return 화이트리스트 포함 여부
     */
    private boolean isWhitelisted(String path) {
        return whitelist.stream().anyMatch(path::startsWith);
    }
}