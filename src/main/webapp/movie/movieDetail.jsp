<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.movie.dto.Movie, com.movie.dto.Member, com.movie.dto.ReviewDTO" %>

<%@ include file="../header.jsp" %>

<%
    // Controller에서 보내준 데이터 받기
    Movie movie = (Movie) request.getAttribute("movie");
    
    // header.jsp에 loginUser가 이미 있으므로 중복 선언 안 함
    
    List<ReviewDTO> reviewList = (List<ReviewDTO>) request.getAttribute("reviewList");
    
    // 데이터가 없으면 목록으로 튕겨내기 (안전장치)
    if (movie == null) {
%>
    <script>
        alert("잘못된 접근입니다.");
        location.href = "<%= request.getContextPath() %>/MovieListController";
    </script>
<%
        return;
    }
%>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-4">
            <img src="<%= request.getContextPath() %>/images/<%= movie.getImage() != null ? movie.getImage() : "no_image.jpg" %>" 
                 class="img-fluid rounded shadow" style="width: 100%;" alt="<%= movie.getTitle() %>">
        </div>
        
        <div class="col-md-8">
            <h1 class="fw-bold mb-3"><%= movie.getTitle() %></h1>
            <hr>
            <p><strong>감독:</strong> <%= movie.getDirector() %></p>
            <p><strong>장르:</strong> <%= movie.getGenre() %></p>
            <p><strong>러닝타임:</strong> <%= movie.getRunningTime() %>분</p>
            <p><strong>개봉일:</strong> 2024.01.01</p> <h4 class="text-danger fw-bold mt-4">가격: <%= String.format("%,d원", movie.getPrice()) %></h4>
            
            <div class="mt-4 p-3 bg-light rounded">
                <h5 class="fw-bold">줄거리</h5>
                <p><%= movie.getDescription() %></p>
            </div>
            
            <div class="mt-4 d-grid gap-2 d-md-block">
                <a href="<%= request.getContextPath() %>/ReservationFormController?movieId=<%= movie.getMovieId() %>" class="btn btn-danger btn-lg me-2">예매하기</a>
                <a href="<%= request.getContextPath() %>/MovieListController" class="btn btn-secondary btn-lg">목록으로</a>
            </div>
        </div>
    </div>
    
    <div class="row mt-5">
        <div class="col-12">
            <h3 class="fw-bold border-bottom pb-2">관람객 한줄평</h3>
            
            <% if (loginUser != null) { %>
            <div class="card mb-3 border-0 bg-light">
                <div class="card-body">
                    <form action="<%= request.getContextPath() %>/ReviewWriteController" method="post" class="d-flex">
                        <input type="hidden" name="movieId" value="<%= movie.getMovieId() %>">
                        <select name="rating" class="form-select me-2" style="width: 120px;">
                            <option value="5">★★★★★</option>
                            <option value="4">★★★★☆</option>
                            <option value="3">★★★☆☆</option>
                            <option value="2">★★☆☆☆</option>
                            <option value="1">★☆☆☆☆</option>
                        </select>
                        <input type="text" name="content" class="form-control me-2" placeholder="한줄평을 남겨주세요." required>
                        <button class="btn btn-dark">등록</button>
                    </form>
                </div>
            </div>
            <% } else { %>
                <div class="alert alert-secondary text-center">
                    리뷰를 작성하려면 <a href="<%= request.getContextPath() %>/member/login.jsp" class="fw-bold">로그인</a>이 필요합니다.
                </div>
            <% } %>

            <div class="list-group">
                <% if (reviewList != null && !reviewList.isEmpty()) { 
                    for (ReviewDTO r : reviewList) { 
                        // 별점 표시
                        String stars = "★".repeat(r.getRating()) + "☆".repeat(5 - r.getRating());
                %>
                    <div class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <span class="text-warning me-2"><%= stars %></span>
                                <span class="fw-bold me-2"><%= r.getMid() %></span>
                                <span><%= r.getContent() %></span>
                            </div>
                            
                            <% 
                                // 본인 글이거나 관리자면 삭제 가능
                                if (loginUser != null && (loginUser.getMid().equals(r.getMid()) || "admin".equals(loginUser.getRole()))) { 
                            %>
                                <a href="<%= request.getContextPath() %>/ReviewDeleteController?reviewId=<%=r.getReviewId()%>&movieId=<%=movie.getMovieId()%>" 
                                   class="btn btn-sm btn-outline-danger" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
                            <% } %>
                        </div>
                    </div>
                <% 
                    }
                } else { 
                %>
                    <div class="text-center py-4 text-muted">등록된 리뷰가 없습니다. 첫 번째 리뷰를 남겨보세요!</div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>