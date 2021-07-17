<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더  -->
<%@ include file="layout/header.jsp"%>
<!-- 바디 시작-->
<div class="container">
<c:forEach var="board" items="${boards}">
	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">${board.title}</h4>
			<a href="#" class="btn btn-primary">상세보기</a>
		</div>
	</div>
</c:forEach>
</div>
<!-- 바디 끝 -->
<!-- 푸터 -->
<%@ include file="layout/footer.jsp"%>


