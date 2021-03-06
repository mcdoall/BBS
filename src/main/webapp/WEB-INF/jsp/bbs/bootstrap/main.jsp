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
	
	<!-- DataTables CSS --><link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.6/css/jquery.dataTables.css">  
	
	
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
    <div class="container">

			<br>
			<br>
			<br>
			
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
				<table id=example>
				</table>						
				<input type="button" value="새글쓰기" class="btn btn-default" onclick="goWrite()" />
   		
   
    </div> 
	<!-- <div id="form-group" style="display: none;"> -->
	<form id="writeForm" action="./moveToWriteFormTest" method="post">
		<p>
			<input type="hidden" name="boardCd" value="${boardCd }" />
			<input type="hidden" name="curPage" value="${curPage }" />
			<input type="hidden" name="searchWord" value="${param.searchWord }" />
		</p>
	</form>
	<!-- </div> -->
	
	<!-- jQuery --><script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>  
	<!-- DataTables --><script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script type="text/javascript">
    
    function goWrite() {
		var form = document.getElementById("writeForm");
		//alert("1234");		
		form.submit();
	}
	
    $(document).ready( function () {    $('#table_id').DataTable();} );
    
    var data = [[        "Tiger Nixon",        "System Architect",        "Edinburgh",        "5421",        "2011/04/25",        "$3,120"    ],    
                [        "Garrett Winters",        "Director",        "Edinburgh",        "8422",        "2011/07/25",        "$5,300"    ]]
    
    $('#example').DataTable( {    data: data} );
    
    </script>
    <script type="text/javascript" src="./assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	
	
	
</body></html>