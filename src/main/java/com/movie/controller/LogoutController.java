package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 현재 세션 가져오기
        HttpSession session = request.getSession(false);
        
        // 2. 세션이 존재하면 무효화 (로그아웃 처리)
        if (session != null) session.invalidate();
        
        // 3. 메인 페이지로 리다이렉트
        response.sendRedirect("MovieListController");
    }
}