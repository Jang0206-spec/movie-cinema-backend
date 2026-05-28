<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>

<div class="container mt-5" style="max-width: 400px;">
    <h3 class="text-center mb-4 fw-bold">로그인</h3>
    
    <div class="card p-4 border-0 shadow-sm">
        <% if(request.getAttribute("error") != null) { %>
            <div class="alert alert-danger text-center">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/LoginController" method="post">
            <div class="mb-3">
                <label class="form-label">아이디</label>
                <input type="text" name="userid" class="form-control" required>
            </div>
            <div class="mb-4">
                <label class="form-label">비밀번호</label>
                <input type="password" name="userpw" class="form-control" required>
            </div>
            
            <div class="d-grid gap-2">
                <button class="btn btn-dark btn-lg">로그인</button>
                <a href="joinForm.jsp" class="btn btn-outline-secondary">회원가입</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="../footer.jsp" %>