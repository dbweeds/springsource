/**
 * 
 */

$(function(){
	$("#changePwd").validate({
		rules:{
			password:{
                required : true
            },
			new_password:{
				required : true,
				validPw : true,
				pwCh : true
			},
			confirm_password:{
				required : true,
				validPw : true,
				equalTo:"#new_password"
			}
		},
		messages:{
			password:{
                required : "현재 비밀번호를 입력해 주세요."
            },
			new_password:{
				required : "새로운 비밀번호를 입력해주세요.",
				equalTo : "이전 비밀번호와 같습니다."
			},
			confirm_password:{
				required : "새로운 비밀번호를 다시 입력해주세요.",
				equalTo : "이전 비밀번호와 다릅니다."
			}
		},
		errorPlacement : function(error,element){
        	element.closest("form")
					.find("small[id='"+element.attr('id')+"']")
					.append(error);   
        },
		success: function(label){
			var name = label.attr('for');
			label.text(name+' is ok!');
		}
	})
})
$.validator.addMethod("validPw",function(value){
	var reg = /(?=.*[A-Za-z])(?=.*[\d])(?=.*[!@#$%&*])[A-Za-z\d!@#$%&*]{8,15}$/;
	return reg.test(value);
},"비밀번호를 영대소문자,숫자의 조합으로 8~15자리로 만들어주세요.")
$.validator.addMethod("pwCh",function(value){
	var pw = $("#current_password").val();
	var ch = $("#new_password").val();
	consle.log(pw);
	consle.log(ch);
	return pw == ch;
},"현재 비밀번호와 동일합니다.")