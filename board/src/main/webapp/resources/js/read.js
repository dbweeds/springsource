/**
 * read.jsp 자바스크립트
 */
$(function(){
	var form = $("#myform");
	$(".btn-default").click(function() {
		form.submit();
	})
	$(".btn-info").click(function(){
		form.find("input[name='bno']").remove();
		form.attr('action','list');
		form.submit();
	})	
})