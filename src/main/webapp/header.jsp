<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.movie.dto.Member" %> 
<%
    // 세션에서 로그인 정보 가져오기
    Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Cinema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { font-family: 'Noto Sans KR', sans-serif; }
        a { text-decoration: none; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand fw-bold text-warning" href="<%= request.getContextPath() %>/MovieListController">MOVIE CINEMA</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link text-white" href="<%= request.getContextPath() %>/MovieListController">영화목록</a></li>
        
        <% if (loginUser != null) { %>
          <li class="nav-item"><a class="nav-link text-white" href="<%= request.getContextPath() %>/ReservationListController">내 예약</a></li>
          
          <% if ("admin".equals(loginUser.getRole())) { %>
             <li class="nav-item"><a class="nav-link text-warning fw-bold" href="<%= request.getContextPath() %>/AdminMovieWriteController">영화등록(Admin)</a></li>
          <% } %>
          
          <li class="nav-item"><span class="nav-link text-info"><%= loginUser.getName() %>님</span></li>
          <li class="nav-item"><a class="nav-link text-white" href="<%= request.getContextPath() %>/logout">로그아웃</a></li>
        <% } else { %>
          <li class="nav-item"><a class="nav-link text-white" href="<%= request.getContextPath() %>/member/login.jsp">로그인</a></li>
          <li class="nav-item"><a class="nav-link text-white" href="<%= request.getContextPath() %>/member/joinForm.jsp">회원가입</a></li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>