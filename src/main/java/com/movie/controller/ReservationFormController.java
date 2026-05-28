package com.movie.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.movie.dao.MovieDAO;
import com.movie.dao.ReservationDAO;
import com.movie.dto.Movie;

@WebServlet("/ReservationFormController")
public class ReservationFormController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect("member/login.jsp");
            return;
        }

        int movieId = Integer.parseInt(request.getParameter("movieId"));
        MovieDAO dao = new MovieDAO();
        Movie m = dao.getMovieById(movieId);
        
        // [추가] 중복 예약을 막기 위해 예약된 좌석 정보를 가져옴
        // (간단 구현을 위해 1회차 10:00로 고정해서 테스트, 실제론 ajax 필요)
        ReservationDAO rDao = new ReservationDAO();
        // 테스트용: 12월 25일 10:00 좌석 정보 조회
        List<String> occupiedList = rDao.getReservedSeats(movieId, "2025-12-25", "10:00");
        
        request.setAttribute("movie", m);
        request.setAttribute("occupiedList", occupiedList);
        request.getRequestDispatcher("reservation/reservationForm.jsp").forward(request, response);
    }
}