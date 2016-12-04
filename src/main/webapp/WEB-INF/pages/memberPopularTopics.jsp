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
	href="${pageContext.request.contextPath}/resources/css/custom.css">

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
<style type="text/css">
</style>
</head>

<body>

	<spring:url value="/resources/${csv}" var="csvFile" />
	<div id="wrapper">

		<!-- header -->
		<jsp:include page="header.jsp"></jsp:include>

		<div id="page-wrapper"
			style="margin-top: 0px; background-color: #222;">

			<div class="container-fluid">

				<!-- Page Heading -->

				<div>
					<a href="${pageContext.request.contextPath}/memberTopicsByPopularity"
						style="margin-top: 0%; color: white;">Back</a>

					<h4 class="page-header" style="margin-top: 0%; color: white">${pageTitle}</h4>
					<div class="col-md-4">
						<script>
							var margin = {
								top : 0,
								right : 0,
								bottom : 20,
								left : 300
							};
							var diameter = 1100 - margin.left - margin.right; //max size of the bubbles
							var tip = d3
									.tip()
									.attr('class', 'd3-tip')
									.offset([ -10, 0 ])
									.html(
											function(d) {
												return "<strong>"
														+ d.name
														+ "</strong> <span style='color:red'> | "
														+ d.value + "</span>";
											})
							var color = d3.scale.category20(); //color category

							var bubble = d3.layout.pack().sort(null).size(
									[ diameter, diameter ]).padding(3);

							var svg = d3.select("body").append("svg").attr(
									"width", 1100).attr("height", 1100).attr(
									"class", "bubble");
							svg.call(tip);
							d3
									.csv(
											"${csvFile}",
											function(error, data) {

												//convert numerical values from strings to numbers
												data = data.map(function(d) {
													d.value = +d["value"];
													return d;
												});

												//bubbles needs very specific format, convert data to this.
												var nodes = bubble.nodes({
													children : data
												}).filter(function(d) {
													return !d.children;
												});

												//setup the chart
												var bubbles = svg
														.append("g")
														.attr(
																"transform",
																"translate("
																		+ margin.left
																		+ ","
																		+ margin.top
																		+ ")")
														.selectAll(".bubble")
														.data(nodes).enter();

												//create the bubbles
												bubbles
														.append("circle")
														.attr("r", function(d) {
															return d.r;
														})
														.attr("cx",
																function(d) {
																	return d.x;
																})
														.attr("cy",
																function(d) {
																	return d.y;
																})
														.style(
																"fill",
																function(d) {
																	return color(d.value);
																})
														.on('mouseover',
																tip.show)
														.on('mouseout',
																tip.hide)
														.on(
																"click",
																function(d) {
																	var url = "${pageContext.request.contextPath}/membersByTopics?url=${url}&id="
																			+ d.id
																			+ "&name="
																			+ d.name;
																	$(location)
																			.attr(
																					'href',
																					url);
																	window.location = url;
																});

												//format the text for each bubble
												bubbles
														.append("text")
														.attr("x", function(d) {
															return d.x;
														})
														.attr("y", function(d) {
															return d.y + 5;
														})
														.attr("text-anchor",
																"middle")
														.html(function(d) {
															return d["name"];
														})
														.style(
																{
																	"fill" : "white",
																	"font-family" : "Helvetica Neue, Helvetica, Arial, san-serif",
																	"font-size" : "12px"
																});
											})
						</script>

					</div>


				</div>
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
