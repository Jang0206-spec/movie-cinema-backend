package com.movie.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.movie.dao.ReservationDAO;
import com.movie.dto.Member;
import com.movie.dto.ReservationDTO;

@WebServlet("/ReservationListController")
public class ReservationListController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 로그인 체크
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("loginUser") == null) {
            resp.sendRedirect("member/login.jsp");
            return;
        }
        
        // 2. 현재 로그인한 사용자 정보 가져오기
        Member m = (Member) s.getAttribute("loginUser");
        
        // 3. 해당 사용자의 예약 목록 조회
        ReservationDAO dao = new ReservationDAO();
        List<ReservationDTO> list = dao.getReservationsByMember(m.getMid());
        
        // 4. 결과 저장 후 뷰로 이동
        req.setAttribute("reservations", list);
        req.getRequestDispatcher("reservation/reservationList.jsp").forward(req, resp);
    }
}