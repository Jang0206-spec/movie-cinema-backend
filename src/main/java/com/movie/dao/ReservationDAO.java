package com.movie.dao;

import java.sql.*;
import java.util.*;
import com.movie.dto.ReservationDTO;
import com.movie.util.DBUtil;

public class ReservationDAO {
    
    // 예약 정보 DB에 저장
    public int insertReservation(ReservationDTO dto) {
        String sql = "INSERT INTO movie_reservation (mid, movie_id, screening_date, screening_time, people_count, seat_list, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, dto.getMemberId());
            p.setInt(2, dto.getMovieId());
            p.setString(3, dto.getScreeningDate());
            p.setString(4, dto.getScreeningTime());
            p.setInt(5, dto.getPeopleCount());
            p.setString(6, dto.getSeatList());
            p.setInt(7, dto.getTotalPrice());
            return p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 내 예약 목록 조회 (조인 사용)
    public List<ReservationDTO> getReservationsByMember(String mid) {
        List<ReservationDTO> list = new ArrayList<>();
        String sql = "SELECT r.*, m.title FROM movie_reservation r " +
                     "JOIN movie m ON r.movie_id = m.movie_id " +
                     "WHERE r.mid = ? ORDER BY r.reservation_id DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, mid);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                ReservationDTO dto = new ReservationDTO();
                dto.setReservationId(rs.getInt("reservation_id"));
                dto.setMovieTitle(rs.getString("title"));
                dto.setScreeningDate(rs.getString("screening_date"));
                dto.setScreeningTime(rs.getString("screening_time"));
                dto.setSeatList(rs.getString("seat_list"));
                dto.setTotalPrice(rs.getInt("total_price"));
                dto.setStatus(rs.getString("status"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // [중복방지] 이미 예약된 좌석 목록 가져오기
    public List<String> getReservedSeats(int movieId, String date, String time) {
        List<String> occupiedSeats = new ArrayList<>();
        String sql = "SELECT seat_list FROM movie_reservation " +
                     "WHERE movie_id=? AND screening_date=? AND screening_time=? AND status='예약완료'";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, movieId);
            p.setString(2, date);
            p.setString(3, time);
            ResultSet rs = p.executeQuery();
            while(rs.next()) {
                String[] seats = rs.getString("seat_list").split(",");
                for(String s : seats) {
                    occupiedSeats.add(s.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return occupiedSeats;
    }

    // 예약 취소 처리 (상태 변경)
    public int cancelReservation(int rid, String mid) {
        String sql = "UPDATE movie_reservation SET status = '취소됨' WHERE reservation_id = ? AND mid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, rid);
            p.setString(2, mid);
            return p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}