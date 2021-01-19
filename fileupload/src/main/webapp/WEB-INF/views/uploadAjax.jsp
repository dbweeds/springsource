<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Upload Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" id="" multiple/>
	</div>
	<button>upload</button>
	<div class="uploadResult">
		<ul></ul>
	</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
	$(function() {
		$("button").click(function() {
			console.log("업로드 호출");
			
			var inputFile = $("input[name='uploadFile']");
			console.log(inputFile);
			
			//첨부 파일 목록
			var files = inputFile[0].files;
			
			//<form> ~ </form> 대체로 ajax로 데이터를 쉽게 전송할 수 있도록 해줌
			var formData = new FormData();
			//객체 안에 요소 추가
			for(var i = 0;i<files.length;i++){
				formData.append("uploadFile",files[i]);
			}
			
			/* for(let key of formData.keys()){
				console.log(key);
			}
			for(let value of formData.values()){
				console.log(value)
			} */
			$.ajax({
				url:'/uploadAjax',
				type:'post',
				processData:false,//데이터를 query string 형태로 보낼것인지 결정(기본값은 application/x-www-form-urlencoded임)
				contentType:false,//기본값은 application/x-www-form-urlencoded임(파일첨부임으로 multipart/form-data로 보내야 함)
				data:formData,
				success:function(result){
					console.log(result);
					showUploadedFile(result);					
				},
				error:function(xhr,statues,error){
					console.log(status);
				}
			})
		})
		
		function showUploadedFile(uploadResultArr) {
			//결과를 보여줄 영역 가져오기
			var uploadResult = $(".uploadResult ul");
			var str = "";
			$(uploadResultArr).each(function(idx,obj) {var i=0
				str+="<li>"+obj.fileName+"</li>";
			});
			uploadResult.append(str);
		}
	})
</script>
</body>
</html>