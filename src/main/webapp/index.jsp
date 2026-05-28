<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.movie.dto.Movie, com.movie.dto.Member" %>
<%
    // 데이터가 없으면 컨트롤러로 보냄 (안전장치)
    if (request.getAttribute("MovieList") == null) {
        response.sendRedirect(request.getContextPath() + "/MovieListController");
        return; 
    }
%>
<%@ include file="header.jsp" %>
<%
    // Controller에서 보내준 데이터 받기
    List<Movie> list = (List<Movie>) request.getAttribute("MovieList");
    
    // 페이징 정보 받기
    Integer currentPageObj = (Integer) request.getAttribute("currentPage");
    Integer totalPageObj = (Integer) request.getAttribute("totalPage");
    
    int currentPage = (currentPageObj != null) ? currentPageObj : 1;
    int totalPage = (totalPageObj != null) ? totalPageObj : 1;
%>

<div class="container mt-5">
    <div class="p-5 mb-4 bg-dark rounded-3 text-white" style="background-color: #212529;">
      <div class="container-fluid py-3">
        <h1 class="display-5 fw-bold">MOVIE CINEMA</h1>
        <p class="col-md-8 fs-4">지금 가장 핫한 영화를 예매하세요!</p>
      </div>
    </div>

    <h2 class="mb-4 fw-bold border-start border-5 border-warning ps-3">현재 상영작</h2>
    
    <div class="row row-cols-1 row-cols-md-4 g-4">
    <% 
        if (list != null) {
            for (Movie m : list) { 
    %>
        <div class="col">
            <div class="card h-100 shadow-sm border-0">
                <img src="<%= request.getContextPath() %>/images/<%= m.getImage() != null ? m.getImage() : "no_image.jpg" %>" 
                     class="card-img-top" alt="<%= m.getTitle() %>" style="height: 300px; object-fit: cover;">
                <div class="card-body">
                    <h5 class="card-title fw-bold"><%= m.getTitle() %></h5>
                    <p class="card-text text-muted small"><%= m.getGenre() %> | <%= m.getRunningTime() %>분</p>
                    <p class="card-text fw-bold text-danger"><%= String.format("%,d원", m.getPrice()) %></p>
                    
                    <a href="<%= request.getContextPath() %>/MovieDetailController?id=<%= m.getMovieId() %>" class="btn btn-dark w-100">예매하기</a>
                </div>
            </div>
        </div>
    <% 
            }
        } 
    %>
    </div>
    
    <% if (list != null && !list.isEmpty()) { %>
    <div class="d-flex justify-content-center mt-4 mb-5">
        <nav>
            <ul class="pagination">
                <% if (currentPage > 1) { %>
                    <li class="page-item">
                        <a class="page-link text-dark" href="<%= request.getContextPath() %>/MovieListController?page=<%= currentPage - 1 %>">이전</a>
                    </li>
                <% } else { %>
                    <li class="page-item disabled"><span class="page-link">이전</span></li>
                <% } %>
                
                <% for (int i = 1; i <= totalPage; i++) { %>
                    <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                        <a class="page-link <%= (i == currentPage) ? "bg-dark border-dark" : "text-dark" %>" 
                           href="<%= request.getContextPath() %>/MovieListController?page=<%= i %>"><%= i %></a>
                    </li>
                <% } %>
                
                <% if (currentPage < totalPage) { %>
                    <li class="page-item">
                        <a class="page-link text-dark" href="<%= request.getContextPath() %>/MovieListController?page=<%= currentPage + 1 %>">다음</a>
                    </li>
                <% } else { %>
                    <li class="page-item disabled"><span class="page-link">다음</span></li>
                <% } %>
            </ul>
        </nav>
    </div>
    <% } %>
</div>

<%@ include file="footer.jsp" %>