package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.movie.dao.MovieDAO;
import com.movie.dto.Member;
import com.movie.dto.Movie;

@WebServlet("/AdminMovieWriteController")
public class AdminMovieWriteController extends HttpServlet {
    
    // GET: 등록 폼 화면 보여주기
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkAdmin(request, response)) {
            request.getRequestDispatcher("admin/movieWrite.jsp").forward(request, response);
        }
    }

    // POST: 실제 DB에 저장하기
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        System.out.println("=== [영화 등록 요청 시작] ===");

        // 1. 관리자 권한 다시 체크
        if (!checkAdmin(request, response)) return;

        try {
            // 2. 파라미터 받기 (숫자 변환 예외처리 포함)
            String title = request.getParameter("title");
            String director = request.getParameter("director");
            String genre = request.getParameter("genre");
            String priceStr = request.getParameter("price");
            String timeStr = request.getParameter("runningTime");
            String description = request.getParameter("description");
            String image = request.getParameter("image");

            // 디버깅용 출력
            System.out.println("제목: " + title);
            System.out.println("가격: " + priceStr);

            // 3. DTO 생성
            Movie m = new Movie();
            m.setTitle(title);
            m.setDirector(director);
            m.setGenre(genre);
            m.setPrice(Integer.parseInt(priceStr));       // 숫자로 변환
            m.setRunningTime(Integer.parseInt(timeStr)); // 숫자로 변환
            m.setDescription(description);
            m.setImage(image);

            // 4. DAO 저장 실행
            MovieDAO dao = new MovieDAO();
            int result = dao.insertMovie(m);
            
            if (result > 0) {
                System.out.println(">> DB 저장 성공!");
                // [중요] 절대 경로를 사용하여 이동 (오류 해결 핵심)
                response.sendRedirect(request.getContextPath() + "/MovieListController");
            } else {
                System.out.println(">> DB 저장 실패 (결과 0)");
                response.sendRedirect(request.getContextPath() + "/MovieListController?error=fail");
            }

        } catch (NumberFormatException e) {
            System.out.println(">> 오류: 숫자 입력칸에 문자가 들어왔습니다.");
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/AdminMovieWriteController?error=number");
        } catch (Exception e) {
            System.out.println(">> 알 수 없는 오류 발생");
            e.printStackTrace();
        }
    }

    // 관리자 여부 확인 함수
    private boolean checkAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        Member m = (session != null) ? (Member) session.getAttribute("loginUser") : null;
        
        if (m == null || !"admin".equals(m.getRole())) {
            // 권한 없으면 메인으로 쫓아냄
            resp.sendRedirect(req.getContextPath() + "/MovieListController");
            return false;
        }
        return true;
    }
}