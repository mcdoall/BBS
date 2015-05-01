<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="로그인" />
<meta name="Description" content="로그인" />
<link rel="stylesheet" href="../css/screen.css" type="text/css" media="screen" />
<title>로그인</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js" ></script>
<script type="text/javascript">
$(document).ready(function(){
	
 $("button#test").click(function(){
	
	var id = $("input#id").val();
	var pw = $("input#pw").val();
	
	alert(id);
	alert(pw);

}); 

});
</script>        
</head>
<body>

<div id="wrap">

	<div id="header">
		<%@ include file="../inc/header.jsp" %>
	</div>

	<div id="main-menu">
		<%@ include file="../inc/main-menu.jsp" %>
	</div>

	<div id="container">
		<div id="content" style="min-height: 800px;">
			<div id="url-navi">회원</div>

<!-- 본문 시작 -->
<h1>로그인</h1>
<form id="loginForm" action="login" method="post" onsubmit="return login()">
<!-- <form id="loginForm" action="loginTo" method="post" > -->
<table>
<tr>
	<td style="width: 200px;">아이디</td>
	<td style="width: 390px"><input id="id" type="text" name="email" style="width: 99%;" /></td>
</tr>
<tr>
	<td>비밀번호(Password)</td>
	<td><input id="pw" type="password" name="passwd" style="width: 99%;" /></td>
</tr>
</table>
<div style="text-align: center;padding-bottom: 15px;">
	<input type="submit" value="확인" />
	<input type="button" value="회원가입" onclick="location.href='signUp'" />
	
</div>
</form>
<button id="test" name="aaaaa">Restore Menu</button>
<!--  본문 끝 -->

		</div><!-- content 끝 -->
	</div><!--  container 끝 -->
	
	<div id="sidebar">
		<%@ include file="notLoginUsers-menu.jsp" %>
	</div>
	
	<div id="extra">
		<%@ include file="../inc/extra.jsp" %>
	</div>

	<div id="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>

</div>

</body>
</html>