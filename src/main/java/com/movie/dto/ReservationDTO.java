package com.movie.dto;

// 예약 정보를 담는 DTO 클래스
public class ReservationDTO {
    private int reservationId;
    private String memberId;
    private int movieId;
    private String screeningDate; // 상영 날짜
    private String screeningTime; // 상영 시간
    private int peopleCount;      // 인원 수
    private String seatList;      // 선택한 좌석 목록 (예: "A1,B2")
    private int totalPrice;       // 총 결제 금액
    private String status;        // 예약 상태 (예약완료/취소됨)
    private String movieTitle;    // 조인 결과(영화 제목) 저장용

    // Getter & Setter
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public String getScreeningDate() { return screeningDate; }
    public void setScreeningDate(String screeningDate) { this.screeningDate = screeningDate; }
    public String getScreeningTime() { return screeningTime; }
    public void setScreeningTime(String screeningTime) { this.screeningTime = screeningTime; }
    public int getPeopleCount() { return peopleCount; }
    public void setPeopleCount(int peopleCount) { this.peopleCount = peopleCount; }
    public String getSeatList() { return seatList; }
    public void setSeatList(String seatList) { this.seatList = seatList; }
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
}