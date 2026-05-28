package com.movie.dao;

import java.sql.*;
import java.util.*;
import com.movie.dto.Movie;
import com.movie.util.DBUtil;

public class MovieDAO {

    // [페이징] 영화 목록 조회
    public List<Movie> getMovieList(int start, int count) {
        List<Movie> list = new ArrayList<>();
        // 최신순 정렬, LIMIT 사용
        String sql = "SELECT * FROM movie ORDER BY movie_id DESC LIMIT ?, ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, start);
            p.setInt(2, count);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                m.setMovieId(rs.getInt("movie_id"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                m.setGenre(rs.getString("genre"));
                m.setPrice(rs.getInt("price"));
                m.setImage(rs.getString("image"));
                m.setRunningTime(rs.getInt("running_time"));
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("DAO 에러 (목록조회): " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // 전체 영화 개수 조회 (페이징용)
    public int getTotalCount() {
        int count = 0;
        String sql = "SELECT count(*) FROM movie";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // 영화 상세 조회
    public Movie getMovieById(int movieId) {
        Movie m = null;
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, movieId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                m = new Movie();
                m.setMovieId(rs.getInt("movie_id"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                m.setGenre(rs.getString("genre"));
                m.setPrice(rs.getInt("price"));
                m.setDescription(rs.getString("description"));
                m.setImage(rs.getString("image"));
                m.setRunningTime(rs.getInt("running_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    // [관리자] 영화 등록
    public int insertMovie(Movie m) {
        // SQL 컬럼명: running_time 주의
        String sql = "INSERT INTO movie (title, director, genre, price, description, image, running_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, m.getTitle());
            p.setString(2, m.getDirector());
            p.setString(3, m.getGenre());
            p.setInt(4, m.getPrice());
            p.setString(5, m.getDescription());
            p.setString(6, m.getImage()); 
            p.setInt(7, m.getRunningTime());
            
            return p.executeUpdate();
            
        } catch (SQLException e) {
            // SQL 에러 발생 시 콘솔에 자세히 출력
            System.out.println(">>> [DB 에러 발생] SQL 문법이나 컬럼명을 확인하세요!");
            System.out.println("에러 메시지: " + e.getMessage());
            e.printStackTrace();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // [관리자] 영화 삭제
    public int deleteMovie(int movieId) {
        String sql = "DELETE FROM movie WHERE movie_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, movieId);
            return p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}