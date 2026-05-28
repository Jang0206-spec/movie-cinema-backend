package com.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.movie.dto.Member;
import com.movie.util.DBUtil;

// member 테이블에 접근하여 데이터를 조작하는 클래스
public class MemberDAO {

    // 아이디 중복 체크 메소드
    public boolean isUserExists(String uid) {
        boolean exists = false;
        String sql = "SELECT mid FROM member WHERE mid = ?";
        
        // try-with-resources 구문을 사용하여 자원 자동 해제
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            
            p.setString(1, uid);
            ResultSet rs = p.executeQuery();
            
            // 결과가 존재하면 이미 사용 중인 아이디임
            if (rs.next()) exists = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    // 회원가입(정보 저장) 메소드
    public int insertMember(Member m) {
        String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            
            // 파라미터 설정
            p.setString(1, m.getMid());
            p.setString(2, m.getName());
            p.setString(3, m.getPwd());
            p.setString(4, m.getEmail());
            p.setString(5, m.getTelno());
            p.setString(6, m.getRole());
            p.setString(7, m.getRegDate());
            
            // 쿼리 실행 (성공 시 1 반환)
            return p.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 로그인 처리 메소드
    public Member login(String uid, String pwd) {
        Member member = null;
        String sql = "SELECT * FROM member WHERE mid = ? AND pwd = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            
            p.setString(1, uid);
            p.setString(2, pwd);
            
            ResultSet rs = p.executeQuery();
            
            // 일치하는 회원이 있으면 Member 객체에 정보 담기
            if (rs.next()) {
                member = new Member();
                member.setMid(rs.getString("mid"));
                member.setName(rs.getString("name"));
                member.setPwd(rs.getString("pwd"));
                member.setEmail(rs.getString("email"));
                member.setTelno(rs.getString("telno"));
                member.setRole(rs.getString("role"));
                member.setRegDate(rs.getString("regdate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }
}