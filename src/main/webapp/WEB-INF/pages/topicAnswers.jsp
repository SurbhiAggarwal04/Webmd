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
<script>
	$(document).ready(function () {
  var bubbleChart = new d3.svg.BubbleChart({
    supportResponsive: true,
    //container: => use @default
    size: 600,
    //viewBoxSize: => use @default
    innerRadius: 600 / 3.5,
    //outerRadius: => use @default
    radiusMin: 50,
    //radiusMax: use @default
    //intersectDelta: use @default
    //intersectInc: use @default
    //circleColor: use @default
    data: {
        ${json	},
      eval: function (item) {return item.count;},
      classed: function (item) {return item.text.split(" ").join("");}
    },
    plugins: [
      {
        name: "lines",
        options: {
          format: [
            {// Line #0
              textField: "count",
              classed: {count: true},
              style: {
                "font-size": "28px",
                "font-family": "Source Sans Pro, sans-serif",
                "text-anchor": "middle",
                fill: "white"
              },
              attr: {
                dy: "0px",
                x: function (d) {return d.cx;},
                y: function (d) {return d.cy;}
              }
            },
            {// Line #1
              textField: "text",
              classed: {text: true},
              style: {
                "font-size": "14px",
                "font-family": "Source Sans Pro, sans-serif",
                "text-anchor": "middle",
                fill: "white"
              },
              attr: {
                dy: "20px",
                x: function (d) {return d.cx;},
                y: function (d) {return d.cy;}
              }
            }
          ],
          centralFormat: [
            {// Line #0
              style: {"font-size": "50px"},
              attr: {}
            },
            {// Line #1
              style: {"font-size": "30px"},
              attr: {dy: "40px"}
            }
          ]
        }
      }]
  });
});
  </script>

<style>
.bubbleChart {
	min-width: 100px;
	max-width: 700px;
	height: 700px;
	margin: 0 auto;
	
}

.bubbleChart svg {
	background: #000000;
}
</style>
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

</head>

<body style="background: black">
	<spring:url value="/resources/${csv}" var="csvFile" />

	<div id="wrapper">

		<!-- header -->
		<jsp:include page="header.jsp"></jsp:include>

		

			<div class="container-fluid">

				<!-- Page Heading -->


				<h4 class="page-header" style="color: white" >${questionName} : Top
					Rated Answers</h4>
				<c:if test="${not empty answerSet}">

					<c:forEach items="${answerSet}" var="answer">
						<span style="color: white">${answer.getAnswerContent()}</span>
						<br>
						<a class="btn btn-default glyphicon glyphicon-hand-right"></a>
						<span style="color: fuchsia;">Vote
							${answer.getAnswerVoteNum()}</span> | <span style="color: olive;">
							Helpful Vote ${answer.getAnswerHelpfulNum()}</span>
						<br>
						<br>
					</c:forEach>
				</c:if>


				<div class="col-md-4">
					<div class="bubbleChart"></div>

				</div>



				<!-- /.row -->



			</div>
			<!-- /.container-fluid -->

		

		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
</body>

</html>