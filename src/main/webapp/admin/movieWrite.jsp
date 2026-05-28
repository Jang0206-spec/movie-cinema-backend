<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.movie.dto.Member" %>
<%
    // 1. 세션에서 로그인 정보 가져오기 (header.jsp가 없어도 동작하도록 안전장치)
    Member loginUser = (Member) session.getAttribute("loginUser");

    // 2. 관리자가 아니면 쫓아내기
    if (loginUser == null || !"admin".equals(loginUser.getRole())) {
%>
    <script>
        alert('관리자만 접근 가능합니다.');
        location.href='<%= request.getContextPath() %>/MovieListController';
    </script>
<%
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>영화 등록 (Admin)</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { font-family: 'Noto Sans KR', sans-serif; }
        a { text-decoration: none; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand fw-bold text-warning" href="<%= request.getContextPath() %>/MovieListController">MOVIE CINEMA (Admin)</a>
    <div class="collapse navbar-collapse">
       <ul class="navbar-nav ms-auto">
          <li class="nav-item"><a class="nav-link text-white" href="<%= request.getContextPath() %>/MovieListController">메인으로 돌아가기</a></li>
       </ul>
    </div>
  </div>
</nav>

<div class="container mt-5" style="max-width: 700px;">
    <div class="text-center mb-4">
        <h3 class="fw-bold">영화 신규 등록</h3>
        <p class="text-muted">새로운 영화 정보를 입력해주세요.</p>
    </div>
    
    <div class="card p-4 shadow-sm border-0 bg-light">
        <form action="<%= request.getContextPath() %>/AdminMovieWriteController" method="post">
            
            <div class="mb-3">
                <label class="form-label fw-bold">영화 제목</label>
                <input type="text" name="title" class="form-control" placeholder="예: 아바타: 물의 길" required>
            </div>
            
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">감독</label>
                    <input type="text" name="director" class="form-control" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">장르</label>
                    <select name="genre" class="form-select">
                        <option value="액션">액션</option>
                        <option value="드라마">드라마</option>
                        <option value="코미디">코미디</option>
                        <option value="스릴러">스릴러</option>
                        <option value="로맨스">로맨스</option>
                        <option value="SF">SF</option>
                        <option value="판타지">판타지</option>
                        <option value="애니메이션">애니메이션</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">티켓 가격 (원)</label>
                    <input type="number" name="price" class="form-control" value="15000" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-bold">러닝타임 (분)</label>
                    <input type="number" name="runningTime" class="form-control" placeholder="예: 120" required>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">포스터 이미지 파일명</label>
                <input type="text" name="image" class="form-control" placeholder="예: movie1.jpg">
                <div class="form-text text-danger small">* webapp/images 폴더에 있는 파일명을 확장자(.jpg)까지 정확히 입력하세요.</div>
            </div>

            <div class="mb-4">
                <label class="form-label fw-bold">줄거리</label>
                <textarea name="description" class="form-control" rows="5" placeholder="영화 줄거리를 입력하세요." required></textarea>
            </div>

            <div class="d-grid gap-2">
                <button class="btn btn-dark btn-lg">영화 등록하기</button>
                <a href="<%= request.getContextPath() %>/MovieListController" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</div>

<footer class="bg-light text-center text-lg-start mt-5">
  <div class="text-center p-4" style="background-color: #f8f9fa;">
    <p class="mb-0 text-muted">© 2025 Movie Cinema Admin Page</p>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>