<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://bootstrapk.com/favicon.ico">

    <title>euani</title>

    <!-- Bootstrap core CSS -->
    <link href="http://bootstrapk.com/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://bootstrapk.com/examples/grid/grid.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./Grid Template for Bootstrap_files/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="게시판 새글쓰기" />
<meta name="Description" content="게시판 새글쓰기" /> -->
<link rel="stylesheet" href="../css/screen.css" type="text/css" media="screen" />
<title>${boardNm }</title>
<script type="text/javascript">
//<![CDATA[
	function check() {
		var form = document.getElementById("writeForm");
		//유효성 검사로직 추가
		return true;
	}
	
	function goList() {
		var form = document.getElementById("listForm");
		form.submit();
	}
	
	function goView() {
		var form = document.getElementById("viewForm");
		if (form.articleNo.value != 0) {
			form.submit();
		}
	}
//]]>
</script>




</head>
<body>

<div id="wrap">

	<div id="container">
		<div id="content" style="min-height: 800px;">
			<div id="url-navi">BBS</div>

				<!-- 본문 시작 -->
				<h6>${boardNm }</h6>
				<!-- <div id="bbs">
				<h2>글쓰기</h2> -->
				<%-- <form id="writeForm" action="write" method="post" onsubmit="return check()">
				<p style="margin: 0;padding: 0;">
				<input type="text" name="boardCd" value="${param.boardCd }" />
				</p>
				<table id="write-form">
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" size="50" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="content" rows="17"></textarea>
					</td>
				</tr>
				</table>
				<div style="text-align: center;padding-bottom: 15px;">
					<input type="submit" value="전송" />
					<c:if test="${!empty param.articleNo }">
					<input type="button" value="상세보기" onclick="goView()" />
					</c:if>
					<input type="button" value="목록" onclick="goList()" />
				</div>				
				</form> --%>
				
				<form id="writeForm" action="write" method="post" onsubmit="return check()" >
				<input type="hidden" name="boardCd" value="${param.boardCd }" />
				  <div class="form-group">
				    <label for="exampleInputEmail1">글제목</label>
				    <input type="text" class="form-control" name="title" placeholder="글제목을입력하세요">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">내용</label>
				    <textarea class="form-control" name="content" placeholder="글내용" rows="3"></textarea>
				  </div>				  
				  <button type="submit" class="btn btn-default">제출</button>
				</form>
				
				
				</div><!-- bbs 끝 -->
				<!--  본문 끝 -->

		</div><!-- content 끝 -->
	</div><!--  container 끝 -->
	

</div>

<%-- <div id="form-group" style="display: none;">
	<form id="listForm" action="list" method="get">
		<p>
		<input type="hidden" name="boardCd" value="${param.boardCd }" />
		<input type="hidden" name="curPage" value="${param.curPage }" />
		<input type="hidden" name="searchWord" value="${param.searchWord }" />
		</p>
	</form>
	<form id="viewForm" action="view" method="get">
		<p>
		<input type="hidden" name="articleNo" value="${param.articleNo }"/>
		<input type="hidden" name="boardCd" value="${param.boardCd }" />
		<input type="hidden" name="curPage" value="${param.curPage }" />
		<input type="hidden" name="searchWord" value="${param.searchWord }" />
		</p>
	</form>
</div> --%>

</body>
</html>