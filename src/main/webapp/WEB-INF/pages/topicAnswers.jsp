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



<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>${pageTitle}</title>

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
</head>

<body>
	<spring:url value="/resources/${csv}" var="csvFile" />

	<div id="wrapper">

		<!-- header -->
		<jsp:include page="header.jsp"></jsp:include>

		<!-- 		<div id="page-wrapper" style="margin-top: 0px"> -->

		<div class="container-fluid">

			<!-- Page Heading -->
			<h3 class="page-header" style="color: white">${questionName}Top
				Rated Answers</h3>
			<c:if test="${not empty answerSet}">

				<c:forEach items="${answerSet}" var="answer">
						<span style="color:white">${answer.getAnswerContent()}</span>
					 <a class="btn btn-default glyphicon glyphicon-hand-right"></a><span style="color: fuchsia;">Vote ${answer.getAnswerVoteNum()}</span> | <span style="color:olive;"> Helpful Vote ${answer.getAnswerHelpfulNum()}</span>
					<br><br>
				</c:forEach>
			</c:if>




			<!-- </div> -->
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