<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- saved from url=(0036)http://bootstrapk.com/examples/grid/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
  </head>

  <body>
  	
<%--   	<!-- 본문 시작 -->			
<h1>${boardNm }</h1>
<div id="bbs">
	<table>
	<tr>
		<th style="width: 60px;">NO</th>
		<th>TITLE</th>
		<th style="width: 84px;">DATE</th>
		<th style="width: 60px;">HIT</th>
	</tr>
	<!--  반복 구간 시작 -->
	<c:forEach var="article" items="${list }" varStatus="status">	
	<tr>
		<td style="text-align: center;">${no - status.index}</td>
		<td>
			<a href="javascript:goView('${article.articleNo }')">${article.title }</a>
			<c:if test="${article.attachFileNum > 0 }">
				<img src="../images/attach.png" alt="첨부파일" />
			</c:if>
			<c:if test="${article.commentNum > 0 }">
				<span class="bbs-strong">[${article.commentNum }]</span>
			</c:if>
		</td>
		<td style="text-align: center;">${article.writeDate }</td>
		<td style="text-align: center;">${article.hit }</td>
	</tr>
	</c:forEach>
	<!--  반복 구간 끝 -->
	</table>
		


	<div id="list-menu" style="text-align:  right;">
		<input type="button" value="새글쓰기" onclick="goWrite()" />
	</div>

<!-- <img src="../../assets/admin/layout/img/logo.png" alt="logo" class="logo-default"/> -->
<!-- <img src="../images/logo.png" alt="java-sch1123ool.net" /> -->
	<div id="search" style="text-align: center;">
		<form id="searchForm" action="./list" method="get" style="margin: 0;padding: 0;">
			<p style="margin: 0;padding: 0;">
				<input type="hidden" name="boardCd" value="${boardCd }" />
				<input type="text" name="searchWord" size="15" maxlength="30" />
				<input type="submit" value="검색" style="height: 25px;line-height: 25px;" />
			</p>	
		</form>
	</div>
	
</div>
<!--  본문 끝 -->
  	
  	
  	 --%>
    <div class="container">

      <div class="page-header">
        <h1>Bootstrap Grid Test</h1>
        <p class="lead">Basic grid layouts to get you familiar with building within the Bootstrap grid system.</p>
      </div>
      
      <h3>Three equal columns</h3>
      	<p>Get three equal-width columns <strong>starting at desktops and scaling to large desktops</strong>. On mobile devices, tablets and below, the columns will automatically stack.</p>
		
		
		      <c:forEach var="article" items="${list }" varStatus="status">
		      		<!-- <div class="row"> -->
		      			<div class="col-md-4">${no - status.index}</div>
		      			<div class="col-md-4">${article.title}</div>
		      			<div class="col-md-4">${article.hit}</div>
		      		<!-- </div> -->
			  </c:forEach>
				
				<div class="row">
				</div>
				<table class="table table-striped">
					<th>글번호</th>
					<th>글제목</th>
					<th>조회수</th>
				 <c:forEach var="article" items="${list }" varStatus="status">
					<tr>
						<td>${no - status.index}</td>
						<td>${article.title}</td>
						<td>${article.hit}</td>
					</tr>
				  </c:forEach>
				</table>
				
				<div class="row">
				</div>
				<div class="table-responsive">
				<table class="table table-striped table-hover">
					<th>글번호</th>
					<th>글제목</th>
					<th>조회수</th>
					<th>글번호</th>
					<th>글제목</th>
					<th>조회수</th>
				 <c:forEach var="article" items="${list }" varStatus="status">
					<tr class="danger">
						<td>${no - status.index}</td>
						<td>${article.title}</td>
						<td>${article.hit}</td>
						<td>${no - status.index}</td>
						<td>${article.title}</td>
						<td>${article.hit}</td>
					</tr>
				  </c:forEach>
				</table>
				</div>
				
				<div class="row">
				</div>
				<table class="table table-bordered">
					<th>글번호</th>
					<th>글제목</th>
					<th>조회수</th>
				 <c:forEach var="article" items="${list }" varStatus="status">
					<tr>
						<td>${no - status.index}</td>
						<td>${article.title}</td>
						<td>${article.hit}</td>
					</tr>
				  </c:forEach>
				</table>
		
				<!-- <form id="writeForm" action="write" method="post" onsubmit="goList()" >
				  <div class="form-group">
				    <label for="exampleInputEmail1">글제목</label>
				    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="이메일을 입력하세요">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">내용</label>
				    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="암호">
				  </div>				  
				  <button type="submit" class="btn btn-default">제출</button>
				</form> -->
				
				
				<input type="button" value="새글쓰기" class="btn btn-default" onclick="goWrite()" />

    </div> <!-- /container -->
	
	<!-- <div id="form-group" style="display: none;"> -->
	<form id="writeForm" action="./moveToWriteFormTest" method="post">
		<p>
			<input type="hidden" name="boardCd" value="${boardCd }" />
			<input type="hidden" name="curPage" value="${curPage }" />
			<input type="hidden" name="searchWord" value="${param.searchWord }" />
		</p>
	</form>
	<!-- </div> -->
	

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script type="text/javascript">
    
    function goWrite() {
		var form = document.getElementById("writeForm");
		//alert("1234");		
		form.submit();
	}
  
    </script>
  

</body></html>