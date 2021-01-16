/**
 * modify.jsp 스크립트
 */
$(function() {
		var form = $("#myform");
		
		$("button").click(function(e) {
			e.preventDefault();
			var oper = $(this).data("oper")
			if(oper == 'remove'){
				form.attr("action","remove");
				form.submit();
			}else if(oper == 'modify'){
				$("form[role='form']").submit();
				//$("#modifyForm").submit();
			}else if(oper == 'list'){
				form.attr("action","list")
					.attr("method","get");
				form.find("input[name='bno']").remove();
				form.submit();
			}
		})	
	})