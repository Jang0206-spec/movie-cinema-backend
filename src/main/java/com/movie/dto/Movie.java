package com.movie.dto;

import java.util.Date;

// movie 테이블과 매핑되는 데이터 클래스 (DTO)
public class Movie {
    private int movieId;
    private String title;
    private String director; // 감독
    private String genre;    // 장르
    private int price;
    private String description;
    private String image;
    private int runningTime; // 상영 시간(분)
    private Date regDate;

    // Getter & Setter
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public int getRunningTime() { return runningTime; }
    public void setRunningTime(int runningTime) { this.runningTime = runningTime; }
    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }
}