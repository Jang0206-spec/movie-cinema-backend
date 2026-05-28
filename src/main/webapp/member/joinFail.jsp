<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>

<div class="container mt-5 text-center">
    <div class="alert alert-danger p-5">
        <h2 class="fw-bold">회원가입 실패</h2>
        <p class="mt-3"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "알 수 없는 오류가 발생했습니다." %></p>
        <a href="joinForm.jsp" class="btn btn-secondary mt-3">다시 가입하기</a>
    </div>
</div>

<%@ include file="../footer.jsp" %>