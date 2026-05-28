<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.movie.dto.ReservationDTO" %>
<%@ include file="../header.jsp" %>

<div class="container mt-5">
    <h2 class="mb-4 fw-bold">🎟️ 내 예매 내역</h2>
    
    <%
        List<ReservationDTO> list = (List<ReservationDTO>) request.getAttribute("reservations");
        if (list == null || list.isEmpty()) {
    %>
        <div class="alert alert-secondary text-center p-5">
            <h4>아직 예매 내역이 없습니다.</h4>
            <a href="../MovieListController" class="btn btn-primary mt-3">영화 예매하러 가기</a>
        </div>
    <% } else { %>
    
    <div class="table-responsive">
        <table class="table table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>예매번호</th>
                    <th>영화제목</th>
                    <th>상영일시</th>
                    <th>인원</th>
                    <th>좌석</th>
                    <th>결제금액</th>
                    <th>상태</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody>
                <% for (ReservationDTO r : list) { %>
                <tr>
                    <td><%= r.getReservationId() %></td>
                    <td class="fw-bold"><%= r.getMovieTitle() %></td>
                    <td><%= r.getScreeningDate() %> <br> <small class="text-muted"><%= r.getScreeningTime() %></small></td>
                    <td><%= r.getPeopleCount() %>명</td>
                    <td><span class="badge bg-info text-dark"><%= r.getSeatList() %></span></td>
                    <td class="text-danger fw-bold"><%= String.format("%,d원", r.getTotalPrice()) %></td>
                    <td>
                        <% if ("취소됨".equals(r.getStatus())) { %>
                            <span class="badge bg-secondary">취소완료</span>
                        <% } else { %>
                            <span class="badge bg-success">예약완료</span>
                        <% } %>
                    </td>
                    <td>
                        <% if (!"취소됨".equals(r.getStatus())) { %>
                            <form action="ReservationCancelController" method="post" onsubmit="return confirm('정말 취소하시겠습니까?');">
                                <input type="hidden" name="reservationId" value="<%= r.getReservationId() %>">
                                <button class="btn btn-sm btn-outline-danger">취소</button>
                            </form>
                        <% } %>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <% } %>
</div>

<%@ include file="../footer.jsp" %>