package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.movie.dao.MemberDAO;
import com.movie.dto.Member;
import com.movie.util.PasswordUtil;

@WebServlet("/JoinController")
public class JoinController extends HttpServlet {
    
    // 회원가입 폼에서 POST 방식으로 데이터가 넘어올 때 처리
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 한글 처리를 위한 인코딩 설정
        req.setCharacterEncoding("UTF-8");
        
        // 2. 요청 파라미터 값 가져오기
        String mid = req.getParameter("mid");
        String name = req.getParameter("name");
        String pwd = PasswordUtil.hashPassword(req.getParameter("pwd")); // 비밀번호는 암호화
        String email = req.getParameter("email");
        String telno = req.getParameter("telno");
        String role = "user"; // 기본 권한은 일반 사용자
        String regDate = new java.sql.Date(System.currentTimeMillis()).toString();

        MemberDAO dao = new MemberDAO();
        
        // 3. 아이디 중복 체크
        if (dao.isUserExists(mid)) {
            req.setAttribute("error", "이미 존재하는 아이디입니다.");
            req.getRequestDispatcher("member/joinFail.jsp").forward(req, resp);
            return;
        }

        // 4. DTO 생성 및 DB 저장
        Member member = new Member(mid, name, pwd, email, telno, role, regDate);
        int result = dao.insertMember(member);
        
        // 5. 결과에 따른 페이지 이동
        if (result > 0) {
            resp.sendRedirect("member/joinSuccess.jsp"); // 성공 시 성공 페이지로
        } else {
            req.setAttribute("error", "회원가입 실패");
            req.getRequestDispatcher("member/joinFail.jsp").forward(req, resp);
        }
    }
}