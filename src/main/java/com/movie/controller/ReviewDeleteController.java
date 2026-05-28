package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.movie.dao.ReviewDAO;

@WebServlet("/ReviewDeleteController")
public class ReviewDeleteController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 파라미터 받기 (리뷰ID, 돌아갈 영화ID)
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        
        // 2. 리뷰 삭제 실행
        ReviewDAO dao = new ReviewDAO();
        dao.deleteReview(reviewId);
        
        // 3. 영화 상세 페이지로 복귀
        response.sendRedirect("MovieDetailController?id=" + movieId);
    }
}