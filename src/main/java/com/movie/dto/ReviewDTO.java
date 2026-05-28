package com.movie.dto;

import java.util.Date;

// 한줄평(리뷰) 데이터를 위한 DTO 클래스
public class ReviewDTO {
    private int reviewId;
    private String mid;     // 작성자 ID
    private int movieId;    // 영화 ID
    private String content; // 내용
    private int rating;     // 별점 (1~5)
    private Date regDate;   // 작성일

    // Getter & Setter
    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }
    public String getMid() { return mid; }
    public void setMid(String mid) { this.mid = mid; }
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }
}