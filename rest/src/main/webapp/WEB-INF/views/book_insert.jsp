<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    
<form method='post' action=''>	
  <div class="form-group">
    <label for="code">Code</label>
    <input type="text" class="form-control" name="code" placeholder="code" required="required">    
  </div>
  <div class="form-group">
    <label for="title">Title</label>
    <input type="text" class="form-control" name="title" placeholder="title" required="required">
  </div>
  <div class="form-group">
    <label for="writer">Writer</label>
    <input type="text" class="form-control" name="writer" placeholder="writer" required="required">
  </div>
  <div class="form-group">
    <label for="price">Price</label>
    <input type="number" class="form-control" name="price" placeholder="price" required="required">
  </div>
  <button type="submit" class="btn btn-primary">입력</button>
  <button type="reset" class="btn btn-secondary">취소</button>	
</form>	
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
	$(function() {
		$(".btn-primary").click(function(e) {
			e.preventDefault();
			/* var result = $("#path").val();
			let param = {
					code : $("input[name='code']").val(),
					title : $("input[name='title']").val(),
					writer : $("input[name='writer']").val(),
					price : $("input[name='price']").val()
			}; */
			
			$.ajax({
				url:'/insert',
				type:'post',
				data:$("form").serialize(),
				success:function(data){
					alert(data);
				},
				error:function(xhr,txtStatus,error){
					alert(xhr.responseText);
				}
			})
		}) 
	})
</script>



