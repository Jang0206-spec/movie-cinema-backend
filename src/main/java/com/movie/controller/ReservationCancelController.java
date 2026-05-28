package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.movie.dao.ReservationDAO;
import com.movie.dto.Member;

@WebServlet("/ReservationCancelController")
public class ReservationCancelController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 로그인 체크
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("loginUser") == null) {
            resp.sendRedirect("member/login.jsp");
            return;
        }
        
        // 2. 파라미터 받기
        Member m = (Member) s.getAttribute("loginUser");
        int reservationId = Integer.parseInt(req.getParameter("reservationId"));
        
        // 3. 예약 취소 실행 (상태 업데이트)
        ReservationDAO dao = new ReservationDAO();
        dao.cancelReservation(reservationId, m.getMid());
        
        // 4. 목록 페이지로 다시 이동
        resp.sendRedirect("ReservationListController");
    }
}