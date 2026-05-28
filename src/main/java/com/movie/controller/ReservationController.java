package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.movie.dao.ReservationDAO;
import com.movie.dto.Member;
import com.movie.dto.ReservationDTO;

@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        HttpSession s = req.getSession(false);
        Member m = (Member) s.getAttribute("loginUser");
        if (m == null) {
            resp.sendRedirect("member/login.jsp");
            return;
        }

        try {
            ReservationDTO dto = new ReservationDTO();
            dto.setMemberId(m.getMid());
            dto.setMovieId(Integer.parseInt(req.getParameter("movieId")));
            dto.setScreeningDate(req.getParameter("screeningDate"));
            dto.setScreeningTime(req.getParameter("screeningTime"));
            dto.setSeatList(req.getParameter("seatList")); 
            dto.setPeopleCount(Integer.parseInt(req.getParameter("persons")));
            dto.setTotalPrice(Integer.parseInt(req.getParameter("totalPrice")));

            ReservationDAO dao = new ReservationDAO();
            dao.insertReservation(dto);
            
            resp.sendRedirect("ReservationListController");
            
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("index.jsp");
        }
    }
}