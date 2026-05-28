<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>

<div class="container mt-5" style="max-width: 500px;">
    <h3 class="text-center mb-4 fw-bold">회원가입</h3>
    
    <div class="card p-4 bg-light border-0 shadow-sm">
        <form action="<%= request.getContextPath() %>/JoinController" method="post">
            <div class="mb-3">
                <label class="form-label">아이디</label>
                <input type="text" name="mid" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">비밀번호</label>
                <input type="password" name="pwd" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">이름</label>
                <input type="text" name="name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">이메일</label>
                <input type="email" name="email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">전화번호</label>
                <input type="text" name="telno" class="form-control" placeholder="010-0000-0000">
            </div>
            
            <div class="d-grid gap-2 mt-4">
                <button class="btn btn-primary btn-lg">가입하기</button>
                <a href="login.jsp" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="../footer.jsp" %>