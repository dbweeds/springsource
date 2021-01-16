<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
	<h3>도서정보</h3>
	<input type="text" id="path" />
	<button type="button" id="all">도서 정보 전체</button>
	<button type="button" id="get">특정 도서 정보</button>
	<button type="button" id="remove">도서 정보 삭제</button>
	<button type="button" id="update">도서 정보 수정</button>
	<button type="button" id="input">도서 정보 입력</button>
	<div id="result">
		<table class="table"></table>
	</div>
</div>
<script>
	$(function() {
		//전체 가져오기
		$("#all").click(function() {
			$.getJSON({
				url:'/list',
				success:function(data){
					console.log(data);
					let str="";
					$(data).each(function(idx,item) {
						str+="<tr><td>";
						str+=item.code+"</td><td>"+item.title+"</td>";
						str+="<td>"+item.writer+"</td>"+"<td>"+item.price+"</td></tr>";
					})
					$(".table").html(str);
				}
			})
		}) 
		//도서 정보 하나 가져오기
		$("#get").click(function() {
			var result = $("#path").val();
			$.getJSON({
				url:'/'+result,
				success:function(item){
					console.log(item);
					let str="";
					
					str+="<tr><td>";
					str+=item.code+"</td><td>"+item.title+"</td>";
					str+="<td>"+item.writer+"</td>"+"<td>"+item.price+"</td></tr>";
					
					$(".table").html(str);
				}
			}) 
		}) 
			//도서정보 삭제
		$("#remove").click(function() {	
			var result = $("#path").val();
			$.ajax({
				url:'/'+result,
				type:'delete',
				success:function(data){
					$(".table").html(data);
				},
				error:function(xhr,txtStatus,error){
					$(".table").html(xhr.responseText);
				}
			})
		}) 
		$("#update").click(function() {	
			var result = $("#path").val();
			//코드,가격
			let param = {
					code : result,
					price : 200000
			};
			$.ajax({
				url:'/update',
				type:'put',
				contentType:"application/json",//application/x-www-from-urlencoded;charset=UTF-8
				data:JSON.stringify(param),
				success:function(data){
					$(".table").html(data);
				},
				error:function(xhr,txtStatus,error){
					$(".table").html(xhr.responseText);
				}
			})
		}) 
		$("#input").click(function() {
			var result = $("#path").val();
			let param = {
					code : result,
					title : '아이쿠',
					writer : 'kim',
					price : 100000
			};
			
			$.ajax({
				url:'/new',
				type:'post',
				contentType:"application/json",//application/x-www-from-urlencoded;charset=UTF-8
				data:JSON.stringify(param),
				success:function(data){
					$(".table").html(data);
				},
				error:function(xhr,txtStatus,error){
					$(".table").html(xhr.responseText);
				}
			})
		}) 
	})
</script>
</body>
</html>