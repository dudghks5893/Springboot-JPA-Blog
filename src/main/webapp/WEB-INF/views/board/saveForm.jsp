<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더  -->
<%@ include file="../layout/header.jsp"%>
<!-- 바디 시작-->
<div class="container">

	<form>
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter username" id="title">
		</div>

		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">글쓰기 완료</button>

</div>
<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<!-- 바디 끝 -->
<!-- 푸터 -->
<%@ include file="../layout/footer.jsp"%>


