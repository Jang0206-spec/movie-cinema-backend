package com.movie.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.movie.dao.MovieDAO;
import com.movie.dto.Movie;

@WebServlet("/MovieListController")
public class MovieListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. 페이지 번호 받기 (파라미터가 없으면 1페이지)
        String pageStr = request.getParameter("page");
        int pageNo = (pageStr == null || pageStr.equals("")) ? 1 : Integer.parseInt(pageStr);
        
        // 2. 한 페이지당 보여줄 개수 및 시작 위치 계산
        int pageSize = 4; // 페이지당 4개씩 
        int start = (pageNo - 1) * pageSize;
        
        // 3. DAO를 통해 영화 목록과 전체 개수 가져오기
        MovieDAO dao = new MovieDAO();
        List<Movie> list = dao.getMovieList(start, pageSize);
        int totalCount = dao.getTotalCount();
        
        // 4. 전체 페이지 수 계산
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
        
        // 5. request 객체에 결과 저장
        request.setAttribute("MovieList", list);
        request.setAttribute("currentPage", pageNo);
        request.setAttribute("totalPage", totalPage);
        
        // 6. index.jsp로 이동
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}