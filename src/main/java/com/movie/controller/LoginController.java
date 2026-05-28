package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.movie.dao.MemberDAO;
import com.movie.dto.Member;
import com.movie.util.PasswordUtil;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 한글 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        
        // 2. 파라미터 받기
        String uid = request.getParameter("userid");
        String rawPwd = request.getParameter("userpw");
        
        // 비밀번호 암호화 체크
        String pwd = PasswordUtil.hashPassword(rawPwd);
        
        System.out.println("[로그인 시도] ID: " + uid);
        
        // 3. DAO를 통해 회원 확인
        MemberDAO dao = new MemberDAO();
        Member member = dao.login(uid, pwd);
        
        if (member != null) {
            String dbRole = member.getRole();
            if (dbRole != null) {
                String cleanRole = dbRole.trim();
                member.setRole(cleanRole);        
                
                System.out.println("---------------------------------------------");
                System.out.println("DB 원본 Role: [" + dbRole + "]");     // 디버깅용
                System.out.println("수정된 Role : [" + member.getRole() + "]"); // 디버깅용
                System.out.println("---------------------------------------------");
            }

            // 4. 세션에 회원 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", member);
            
            // 5. 메인으로 이동 (절대 경로 사용)
            response.sendRedirect(request.getContextPath() + "/MovieListController"); 
        } else {
            // 로그인 실패
            System.out.println("[로그인 실패] 정보 불일치");
            request.setAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            request.getRequestDispatcher("member/login.jsp").forward(request, response);
        }
    }
}