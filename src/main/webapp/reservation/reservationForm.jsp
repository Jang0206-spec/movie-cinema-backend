<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.movie.dto.Movie, java.util.List, java.util.ArrayList"%>
<%@ include file="../header.jsp"%>
<%
    Movie movie = (Movie) request.getAttribute("movie");
    List<String> occupiedList = (List<String>) request.getAttribute("occupiedList");
    if (occupiedList == null) occupiedList = new ArrayList<>();
    
    // header.jsp에서 loginUser를 이미 가져왔으므로 중복 선언 안 함
    
    // 안전장치: 영화 정보가 없으면 목록으로 돌아가기
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

<div class="container mt-5" style="max-width: 800px;">
    <div class="text-center mb-4">
        <h3>예매하기 - <span class="text-primary"><%= movie.getTitle() %></span></h3>
    </div>

    <form action="<%= request.getContextPath() %>/ReservationController" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="movieId" value="<%= movie.getMovieId() %>">
        <input type="hidden" name="price" id="pricePerTicket" value="<%= movie.getPrice() %>">
        
        <div class="row mb-4">
            <div class="col-md-6">
                <label class="form-label fw-bold">상영 날짜</label>
                <input type="date" name="screeningDate" class="form-control" value="2025-12-25" required>
            </div>
            <div class="col-md-6">
                <label class="form-label fw-bold">상영 시간</label>
                <select name="screeningTime" class="form-select" required>
                    <option value="10:00">1회차 10:00</option>
                    <option value="13:00">2회차 13:00</option>
                    <option value="16:00">3회차 16:00</option>
                    <option value="19:00">4회차 19:00</option>
                </select>
            </div>
        </div>

        <div class="mb-4">
            <label class="form-label fw-bold">좌석 선택</label>
            <div class="card p-3 bg-dark">
                <div class="d-flex justify-content-center flex-wrap gap-2">
                    <% 
                        // A1 ~ E5 까지 좌석 생성
                        char[] rows = {'A', 'B', 'C', 'D', 'E'};
                        for(char r : rows) {
                            for(int i=1; i<=5; i++) {
                                String seat = r + String.valueOf(i);
                                boolean isOccupied = occupiedList.contains(seat);
                    %>
                        <input type="checkbox" class="btn-check" id="seat_<%= seat %>" value="<%= seat %>" 
                               onchange="updateTotal()" <%= isOccupied ? "disabled" : "" %>>
                        <label class="btn <%= isOccupied ? "btn-secondary" : "btn-outline-light" %>" for="seat_<%= seat %>" style="width: 50px;">
                            <%= seat %>
                        </label>
                        <% if(i == 5) { %><div class="w-100"></div><% } %> <% 
                            }
                        } 
                    %>
                </div>
                <div class="text-center mt-3">
                    <span class="badge bg-secondary">이미 예약됨</span>
                    <span class="badge bg-light text-dark border">선택 가능</span>
                    <span class="badge bg-primary">선택함</span>
                </div>
            </div>
        </div>

        <input type="hidden" name="seatList" id="seatList">
        <input type="hidden" name="persons" id="personsInput">

        <div class="card bg-light mb-4 border-0">
            <div class="card-body">
                <div class="d-flex justify-content-between mb-2">
                    <span>선택 좌석:</span>
                    <span id="selectedSeatsDisplay" class="fw-bold">-</span>
                </div>
                <div class="d-flex justify-content-between mb-2">
                    <span>인원:</span>
                    <span class="fw-bold"><span id="personCount">0</span>명</span>
                </div>
                <hr>
                <div class="d-flex justify-content-between align-items-center">
                    <label class="fw-bold fs-5">총 결제 금액</label>
                    <div class="fw-bold text-danger fs-5"><span id="totalPriceDisplay">0</span>원</div>
                    <input type="hidden" name="totalPrice" id="totalPriceInput">
                </div>
            </div>
        </div>
        <button class="btn btn-primary w-100 btn-lg">예매 완료</button>
    </form>
</div>

<script>
    function updateTotal() {
        const checkboxes = document.querySelectorAll('.btn-check:checked');
        const pricePerTicket = parseInt(document.getElementById('pricePerTicket').value);
        let selectedSeats = [];
        checkboxes.forEach((cb) => selectedSeats.push(cb.value));
        const count = selectedSeats.length;
        const total = count * pricePerTicket;

        document.getElementById('selectedSeatsDisplay').innerText = count > 0 ? selectedSeats.join(", ") : "-";
        document.getElementById('personCount').innerText = count;
        document.getElementById('totalPriceDisplay').innerText = total.toLocaleString();

        document.getElementById('seatList').value = selectedSeats.join(",");
        document.getElementById('personsInput').value = count;
        document.getElementById('totalPriceInput').value = total;
    }

    function validateForm() {
        if (!document.getElementById('seatList').value) {
            alert("최소 1개 이상의 좌석을 선택해주세요.");
            return false;
        }
        return confirm("정말 예매하시겠습니까?");
    }
</script>

<%@ include file="../footer.jsp" %>