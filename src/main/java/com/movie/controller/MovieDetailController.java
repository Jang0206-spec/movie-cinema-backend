package com.movie.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.movie.dao.MovieDAO;
import com.movie.dao.ReviewDAO;
import com.movie.dto.Movie;
import com.movie.dto.ReviewDTO;

@WebServlet("/MovieDetailController")
public class MovieDetailController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            response.sendRedirect("MovieListController");
            return;
        }
        int movieId = Integer.parseInt(id);
        
        // 1. 영화 상세 정보 가져오기
        MovieDAO movieDao = new MovieDAO();
        Movie m = movieDao.getMovieById(movieId);
        
        // 2. 해당 영화의 리뷰 목록 가져오기
        ReviewDAO reviewDao = new ReviewDAO();
        List<ReviewDTO> reviewList = reviewDao.getReviewsByMovieId(movieId);
        
        // 3. 결과 저장 및 이동
        request.setAttribute("movie", m);
        request.setAttribute("reviewList", reviewList); 
        request.getRequestDispatcher("movie/movieDetail.jsp").forward(request, response);
    }
}