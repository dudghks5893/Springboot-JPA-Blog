let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{ 
			this.save();
		});
		$("#btn-delete").on("click", ()=>{ 
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{ 
			this.update();
		});
		$("#btn-reply-save").on("click", ()=>{ 
			this.replySave();
		});
	},
	// 글 등록
	save:function(){
		let data = {
			title:$("#title").val(),
			content:$("#content").val(),
		};
		
		$.ajax({
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8", 
			dataType:"json"
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	// 글 삭제
	deleteById:function(){
		let id = $("#id").text(); // 벨류 값이 아닌 텍스트 값을 뽑아옴
		
		$.ajax({
			type:"DELETE",
			url:"/api/board/"+id,
			dataType:"json"
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	// 글 수정
	update:function(){
		let id = $("#id").val();
		
		let data = {
			title:$("#title").val(),
			content:$("#content").val(),
		};
		
		$.ajax({
			type:"PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8", 
			dataType:"json"
		}).done(function(resp){
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	// 댓글 등록
	replySave:function(){
		let data = {
			userId:$("#userId").val(),
			boardId:$("#boardId").val(),
			content:$("#reply-content").val()
		};
		
		console.log(data);
		
		$.ajax({
			type:"POST",
			url:`/api/board/${data.boardId}/reply`, // 파라메터로 게시글 아이디 받기
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8", 
			dataType:"json"
		}).done(function(resp){
			alert("댓글작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	// 댓글 삭제
	replyDelete:function(boardId, replyId){
		$.ajax({
			type:"DELETE",
			url:`/api/board/${boardId}/reply/${replyId}`, 
			dataType:"json"
		}).done(function(resp){
			alert("댓글삭제 성공.");
			location.href = `/board/${boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	
	
}

index.init();