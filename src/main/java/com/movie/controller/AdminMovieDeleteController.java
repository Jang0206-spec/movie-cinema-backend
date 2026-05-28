package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.movie.dao.MovieDAO;
import com.movie.dto.Member;

@WebServlet("/AdminMovieDeleteController")
public class AdminMovieDeleteController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 세션 확인
        HttpSession session = request.getSession(false);
        Member m = (session != null) ? (Member) session.getAttribute("loginUser") : null;
        
        // 2. 관리자 권한 체크 (admin일 경우에만 삭제 진행)
        if (m != null && "admin".equals(m.getRole())) {
            int movieId = Integer.parseInt(request.getParameter("id"));
            MovieDAO dao = new MovieDAO();
            dao.deleteMovie(movieId);
        }
        
        // 3. 삭제 후 목록으로 이동
        response.sendRedirect("MovieListController");
    }
}