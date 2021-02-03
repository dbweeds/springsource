<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>관리자 - admin</h1>
   <%--principal : 사용자를 증명하는 user(CustomUser => MemberVO) 를 직접 접근--%>
   <p>principal : <sec:authentication property="principal"/>
   <p>MemberVO : <sec:authentication property="principal.member"/>
   <p>사용자아이디 : <sec:authentication property="principal.member.userid"/>
   <p>사용자이름 : <sec:authentication property="principal.member.userName"/>
   <p>사용자권한리스트 : <sec:authentication property="principal.member.authList"/>
   <form action="/logout" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<button>로그아웃</button>
   </form>
</body>
</html>