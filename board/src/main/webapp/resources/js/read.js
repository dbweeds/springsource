/**
 * read.jsp 자바스크립트
 */
$(function(){
	//댓글 리스트 함수호출
	var pageNum = 1;
	showList(pageNum);

	
	var form = $("#myform");
	$(".btn-default").click(function() {
		form.submit();
	})
	$(".btn-info").click(function(){
		form.find("input[name='bno']").remove();
		form.attr('action','list');
		form.submit();
	})
	
	//댓글작업
	//댓글 삽입 - bno,reply,replyer
	$("#addReplyBtn").click(function(){
		//기본디자인에서 댓글 입력을 위한 화면 변경
		modal.find("input").val("");
		//readonly속성 없애기
		modalInputReplyer.prop("readonly","");
		
		//작성일자 요소 숨기기
		modalInputReplyDate.closest("div").hide();
		//등록,닫기 버튼만 보여주기
		modal.find("button[id!='modalCloseBtn']").hide();
		modalRegisterBtn.show();
		
		modal.modal("show");
		
	})
	
	modalRegisterBtn.click(function(){
		
		var reply = {bno:bnoVal,reply:modalInputReply.val(),replyer:modalInputReplyer.val()};
		
		replyService.add(reply,function(result){
			if(result){
				//alert("result : "+result);
				
				//1. 현재 모달장에 있는 내용 모두 지우기
				modal.find("input").val("");
				//2. 모달창 닫기
				modal.modal("hide");
				//3. 리스트 보여주기
				showList(-1);
			}
		});		
		
	})
	
	//댓글 리스트 가져오기 - 1401/1
	function showList(page){
		replyService.getList({bno:bnoVal,page:page},function(total,data){
			console.log(data);
			//새글 작성시 http://localhost:8080/replies/pages/-1
			if(page == -1){//마지막 페이지를 알아내기 위한 작업
				pageNum=Math.ceil(total/10.0);
				showList(pageNum);
				return;
			}
			
			
			
			//보여줄 댓글이 없다면
			if(data==null || data.length==0){
				replyUl.html("");
				return			
			}
			
			
			
			//댓글이 존재 한다면
			let str="";
			for(var i =0,len=data.length||0;i<len;i++){
				str+='<li class="left clearfix" data-rno="'+data[i].rno+'">';
				str+='<div><div class="header">';
				str+='<strong class="primary-font">'+data[i].replyer+'</strong>';
				str+='<small class="pull-right text-muted">'+replyService.displayTime(data[i].replyDate)+'</small>';
				str+='<p>'+data[i].reply+'</p>';
				str+='</div></div></li>';
			}
				replyUl.html(str);
				showReplyPage(total);
		});//getlist end*/
	}
	//댓글 페이지 영역
	function showReplyPage(total){
		console.log("total: "+total);
		
		//마지막 페이지 계산
		var endPage = Math.ceil(pageNum/ 10.0) * 10;
		//시작 페이지 계산
		var startPage = endPage - 9;
		
		var prev = startPage!=1;
		
		var next = false;
		
		if(endPage*10 >= total){
			endPage = Math.ceil(total/10.0);
		}
		if(endPage*10 < total){
			next=true;
		}
		var str="<ul class='pagination pull-right'>";
		if(prev){
			str+="<li class='page-item'><a class='page-link' href='"+(startPage-1)+"'>";
			str+="Previous</a></li>";
		}
		for(var i=startPage;i<=endPage;i++){
			var active = pageNum==i ? "active":"";
			str+="<li class='page-item "+active+"'>";
			str+="<a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		if(next){
			str+="<li class='page-item'><a class='page-link' href='"+(endPage-1)+"'>";
			str+="next</a></li>";
		}
		str+="</ul>";
		replyPageFooter.html(str);
	
	}
	//댓글 페이지 나누기 번호 클릭시 동작
	replyPageFooter.on("click","li a",function(e){
		e.preventDefault();
		
		pageNum=$(this).attr("href");
		showList(pageNum);
	})
	
	
	
	//댓글 삭제
	
	$(modalRemoveBtn).click(function(){
		replyService.remove(modal.data("rno"),function(result){
			if(result){
				//alert("result : "+result);
				
				modal.modal("hide");
				showList(pageNum);			
			}})//remove end
	})
	//댓글 수정하기
	
	$(modalModBtn).click(function(){
		let reply = {rno:modal.data("rno"),reply:modalInputReply.val()};
		replyService.update(reply,function(result){
			if(result){
				//alert("result : "+result);
				modal.modal("hide");
				showList(pageNum);			
			}})//update end
	})
	//댓글 하나 가져오기
	//이벤트 위임:li태그는 나중에 생기는 요소이기 때문에 현재 있는 요소에 이벤트를 걸고
	//		   나중에 li 태그에 넘겨주는 방식
	$(replyUl).on("click","li",function(){
		
		//햔재 클릭된li 요소의 rno가져오기
		var rno = $(this).data("rno");
		console.log("rno - "+rno);
		
		replyService.get(rno,function(data){
			if(data!=null){
				console.log(data);
				
				//요청한 댓글 화면에 보여주기
				modalInputReply.val(data.reply);
				modalInputReplyer.val(data.replyer).attr("readonly","readonly");
				modalInputReplyDate.val(replyService.displayTime(data.replyDate)).attr("readonly","readonly");
				modal.data("rno",data.rno);
				
				//숨겨졌던 작성 날짜 영역 보여주기
				modalInputReplyDate.closest("div").show();
				modal.find('button').show();
				modal.find("button[id='modalRegisterBtn']").hide();
				modal.modal("show");				
			}
		});
			
	})
})