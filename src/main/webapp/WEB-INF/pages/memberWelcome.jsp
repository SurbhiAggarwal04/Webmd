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

		<div id="page-wrapper" style="margin-top: 0px;background-color: #222;">

			<div class="container-fluid">

				<!-- Page Heading -->

				<div>
					<h4 class="page-header" style="margin-top: 0%;color: white">${pageTitle}</h4>
										<span style="color: white">Various topics discussed on the
						Webmd site has been categorized into:
						<ul>
							<li>Most Popular Topics ( No of Questions >= 125 )</li>
							<li>Mediocre Topics ( No of Questions < 125 and >=40 )</li>
							<li>Least Popular Topics ( No of Questions <40 )</li>
						</ul>
					</span>

					
					<div class="col-md-4">
						<script>
						var margin = {top: 10, right: 50, bottom: 20, left: 300};
						var diameter = 1000-margin.left-margin.right; //max size of the bubbles
											  var  color    = d3.scale.category20(); //color category
											var bubble = d3.layout.pack()
											    .sort(null)
											    .size([diameter, diameter])
											    .padding(50);
											var svg = d3.select("body")
											    .append("svg")
											    .attr("width", 1000)
											    .attr("height", 1000)
											    .attr("class", "bubble");
											d3.csv("${csvFile}", function(error, data){
											    //convert numerical values from strings to numbers
											    data = data.map(function(d){ d.value = +d["value"]; return d; });
											    //bubbles needs very specific format, convert data to this.
											    var nodes = bubble.nodes({children:data}).filter(function(d) { return !d.children; });
											    //setup the chart
											    var bubbles = svg.append("g")
											        .attr("transform","translate(" + margin.left + "," + margin.top + ")")
											        .selectAll(".bubble")
											        .data(nodes)
											        .enter();
											    //create the bubbles
											    bubbles.append("circle")
											        .attr("r", function(d){ return d.r; })
											        .attr("cx", function(d){ return d.x; })
											        .attr("cy", function(d){ return d.y; })
											        .style("fill", function(d) { return color(d.value); })
											        .on("click", function(d) {	
											        	var url;
											        	if(d.id.match(/Most.*/))
												        	url = "${pageContext.request.contextPath}/memberMostPopularTopics";
												        if(d.id.match(/Mediocre.*/))
													        url = "${pageContext.request.contextPath}/memberMediocrePopularTopics";
													    if(d.id.match(/Least.*/))
														    url = "${pageContext.request.contextPath}/memberLeastPopularTopics";											    		
									                    $(location).attr('href', url);
									                    window.location = url;
											           });
											    //format the text for each bubble
											    bubbles.append("text")
											        .attr("x", function(d){ return d.x; })
											        .attr("y", function(d){ return d.y + 5; })
											        .attr("text-anchor", "middle")
											        .html(function(d){ return d["id"]; })
											        .style({
											            "fill":"white", 
											            "font-family":"Helvetica Neue, Helvetica, Arial, san-serif",
											            "font-size": "12px"
											        });
											            bubbles.append("text")
											        .attr("x", function(d){ return d.x; })
											        .attr("y", function(d){ return d.y + 20; })
											        .attr("text-anchor", "middle")
											        .html(function(d){ return "No. of Topics " + d["value"]; })
											        .style({
											            "fill":"white", 
											            "font-family":"Helvetica Neue, Helvetica, Arial, san-serif",
											            "font-size": "12px"
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