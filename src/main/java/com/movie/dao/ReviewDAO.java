package com.movie.dao;

import java.sql.*;
import java.util.*;
import com.movie.dto.ReviewDTO;
import com.movie.util.DBUtil;

public class ReviewDAO {
    // 리뷰 등록
    public int insertReview(ReviewDTO dto) {
        String sql = "INSERT INTO review (mid, movie_id, content, rating) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, dto.getMid());
            p.setInt(2, dto.getMovieId());
            p.setString(3, dto.getContent());
            p.setInt(4, dto.getRating());
            return p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 특정 영화의 리뷰 목록 조회
    public List<ReviewDTO> getReviewsByMovieId(int movieId) {
        List<ReviewDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM review WHERE movie_id = ? ORDER BY review_id DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, movieId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ReviewDTO r = new ReviewDTO();
                r.setReviewId(rs.getInt("review_id"));
                r.setMid(rs.getString("mid"));
                r.setMovieId(rs.getInt("movie_id"));
                r.setContent(rs.getString("content"));
                r.setRating(rs.getInt("rating"));
                r.setRegDate(rs.getTimestamp("regdate"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 리뷰 삭제
    public int deleteReview(int reviewId) {
        String sql = "DELETE FROM review WHERE review_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, reviewId);
            return p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}