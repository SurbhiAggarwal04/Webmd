<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

<script src="http://d3js.org/d3.v3.min.js"></script>
<script
	src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/adminjs/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/adminjs/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/adminjs/plugins/morris/raphael.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/adminjs/plugins/morris/morris.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/adminjs/plugins/morris/morris-data.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/jquery/dist/jquery.min.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/d3/d3.min.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/d3-transform/src/d3-transform.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/cafej/src/extarray.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/cafej/src/misc.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/cafej/src/micro-observer.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/microplugin/src/microplugin.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/bubble-chart/src/bubble-chart.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/bubble-chart/src/plugins/central-click/central-click.js"></script>
<script
	src="http://phuonghuynh.github.io/js/bower_components/bubble-chart/src/plugins/lines/lines.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>${pageTitle}</title>
<link
	href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,600,200italic,600italic&subset=latin,vietnamese'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/admincss/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/admincss/sb-admin.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link
	href="${pageContext.request.contextPath}/resources/admincss/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/adminfont-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
	function formSubmit() {
		if (document.getElementById("searchKeyword").value != '') {
			document.getElementById('searchForm').action = "${pageContext.request.contextPath}/searchKeyword";
			document.getElementById("searchForm").submit();
		}

	}
</script>
</head>

<body style="background: #222">
	<spring:url value="/resources/${csv}" var="csvFile" />

	<div id="wrapper">

		<!-- header -->
		<jsp:include page="header.jsp"></jsp:include>

		<div id="page-wrapper"
			style="margin-top: 0px; background-color: #222;">

			<div class="container-fluid">

				<!-- Page Heading -->
				<form name="searchForm" id="searchForm" method="get">
					<input type="text" name="searchKeyword" id="searchKeyword"
						placeholder="Enter keywords" style="width: 400px" />
					<button class="glyphicon glyphicon-search" onclick="formSubmit();"
						style="height: 28px"></button>

				</form>
				<br><br>
<%-- 					<c:if test="${not empty setOfConceptMapObjects}">
						
							<c:forEach items="${setOfConceptMapObjects}" var="questionLinks">
								<a href="${pageContext.request.contextPath}/" style="color: gray">${questionLinks.questionTitle}</a>
								<hr>
							</c:forEach>
						
					</c:if>
 --%>

				



				<!-- /.row -->



			</div>
			<!-- /.container-fluid -->
		</div>


		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
</body>

</html>