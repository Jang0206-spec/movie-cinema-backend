package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.movie.dao.ReviewDAO;
import com.movie.dto.Member;
import com.movie.dto.ReviewDTO;

@WebServlet("/ReviewWriteController")
public class ReviewWriteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        Member loginUser = (session != null) ? (Member) session.getAttribute("loginUser") : null;
        
        if (loginUser == null) {
            response.sendRedirect("member/login.jsp");
            return;
        }

        int movieId = Integer.parseInt(request.getParameter("movieId"));
        ReviewDTO dto = new ReviewDTO();
        dto.setMid(loginUser.getMid());
        dto.setMovieId(movieId);
        dto.setContent(request.getParameter("content"));
        dto.setRating(Integer.parseInt(request.getParameter("rating")));

        ReviewDAO dao = new ReviewDAO();
        dao.insertReview(dto);

        response.sendRedirect("MovieDetailController?id=" + movieId);
    }
}