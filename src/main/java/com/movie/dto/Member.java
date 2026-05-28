package com.movie.dto;

// 회원 정보를 담는 데이터 클래스 (DTO)
public class Member {
    private String mid;      // 아이디
    private String name;     // 이름
    private String pwd;      // 비밀번호 (암호화됨)
    private String email;    // 이메일
    private String telno;    // 전화번호
    private String role;     // 역할 (admin:관리자, user:일반회원)
    private String regDate;  // 가입일

    // 기본 생성자
    public Member() {}

    // 필드 초기화 생성자
    public Member(String mid, String name, String pwd, String email, String telno, String role, String regDate) {
        this.mid = mid;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.telno = telno;
        this.role = role;
        this.regDate = regDate;
    }

    // Getter & Setter 메소드
    public String getMid() { return mid; }
    public void setMid(String mid) { this.mid = mid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelno() { return telno; }
    public void setTelno(String telno) { this.telno = telno; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
}