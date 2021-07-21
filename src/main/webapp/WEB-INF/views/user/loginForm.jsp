<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더  -->
<%@ include file="../layout/header.jsp"%>
<!-- 바디 시작-->
<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<button type="submit" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=60981f706aca028b077444a457a8bb08&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
			<img height="40px" src="/image/kakao_login_button.png"/>
		</a>
	</form>

</div>
<!-- 바디 끝 -->
<!-- 푸터 -->
<%@ include file="../layout/footer.jsp"%>


