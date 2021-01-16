/**
 * 

아이디는 5~12자리 - 영어 대소문자,숫자,특수문자 허용
비밀번호는 8~15 - 영어 대소문자,숫자,특수문자 허용
비밀번호확인 8~15 - 이전 비밀번호와 동일
이름 : 2~4자리 허용
성별 : 무조건 하나 선택
이메일 : 이메일 검증


 */
$(function(){
	$("#regist").validate({
		rules:{
            userid:{
                required : true,
                validId : true,
				remote:{
					url:"/member/checkId",
					type:"post",
					data:{
						userid:function(){
							return $("#userid").val();
						}
					}
				}
            },
			password:{
                required : true,
                validPw : true
            },
			confirm_password:{
                required : true,
                validPw : true,
				equalTo:"#password"
            },
			name:{
				required : true,
				rangelength:[2,4]
			},
			gender:{
				required : true
			},
			email:{
                required : true,
                email:true
            }
		},
		messages:{
			userid:{
				required : "아이디는 필수요소 입니다.",
				remote:"아이디중복으로 인하여 사용 불가"
			},
			password:{
				required : "비밀번호는 필수요소 입니다."
			},
			confirm_password:{
				required : "비밀번호는 필수요소 입니다.",
				equalTo : "이전 비밀번호와 다릅니다."
			},
			name:{
				required : "이름은 필수요소 입니다.",
				rangelength : "이름을 2~4크기로 만들어주세요."
			},
			gender:{
				required : "성별을 선택해 주세요."
			},
			email:{
                required : "이메일은 필수 속성입니다."
            }
		},
		//errElement:"em", closest(현재 위치의 위로 가장가까운 "위치"를 찾는것),
        errorPlacement : function(error,element){
        	element.closest("form")
					.find("small[id='"+element.attr('id')+"']")
					.append(error);   
        },
		success: function(label){
			var name = label.attr('for');
			label.text(name+' is ok!');
		}
	});
})

$.validator.addMethod("validId",function(value){
	var reg = /(?=.*[A-Za-z])(?=.*[\d])[A-Za-z\d!@#$%&*]{5,12}$/;
	return reg.test(value);
},"아이디를 영대소문자,숫자의 조합으로 5~12자리로 만들어주세요.")
$.validator.addMethod("validPw",function(value){
	var reg = /(?=.*[A-Za-z])(?=.*[\d])[A-Za-z\d!@#$%&*]{8,15}$/;
	return reg.test(value);
},"비밀번호를 영대소문자,숫자의 조합으로 8~15자리로 만들어주세요.")
$.validator.addMethod("validName",function(value){
	var reg = /[A-Za-z가-힣]{2,4}$/;
	return reg.test(value);
},"이름을 2~4자리로 만들어주세요")
$.validator.addMethod("email",function(value){
	 var reg =  /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/; 
    return reg.test(value);
},"이메일을 확인해 주세요.")
/*$(function() {
		var check = ${passwordCheck};
		if(!check){
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
	})*/