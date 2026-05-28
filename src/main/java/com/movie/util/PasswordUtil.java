package com.movie.util;

import java.security.MessageDigest;

public class PasswordUtil {
    // 비밀번호를 SHA-256 알고리즘으로 암호화하는 메소드
    public static String hashPassword(String pwd) {
        String hashed = "";
        try {
            // 1. SHA-256 알고리즘 인스턴스 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // 2. 비밀번호 문자열을 바이트 배열로 변환하여 해싱
            md.update(pwd.getBytes()); 
            byte[] bytes = md.digest(); 
            
            // 3. 바이트 배열을 16진수 문자열로 변환
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            hashed = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashed; // 암호화된 문자열 반환
    }
}