<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>
<style>
svg {
	font: 12px sans-serif;
	background-color: white;
	margin-left: 20%
}

text {
	pointer-events: none;
}

.inner_node rect {
	pointer-events: all;
}

.inner_node rect.highlight {
	stroke: #315B7E;
	stroke-width: 2px;
}

.outer_node circle {
	fill: #fff;
	stroke: steelblue;
	stroke-width: 1.5px;
	pointer-events: all;
}

.outer_node circle.highlight {
	stroke: #315B7E;
	stroke-width: 2px;
}

.link {
	fill: none;
}
</style>
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

<!-- 		<div id="page-wrapper"
			style="margin-top: 0px; background-color: #222;"> -->

			<div>

				<!-- Page Heading -->
				<form name="searchForm" id="searchForm" method="get">
					<input type="text" name="searchKeyword" id="searchKeyword"
						placeholder="Enter keywords" style="width: 400px" />
					<button class="glyphicon glyphicon-search" onclick="formSubmit();"
						style="height: 28px"></button>
						<span style="color: white;">&nbsp; &nbsp;&nbsp;   Example keywords: drugs, cough, fever, pregnancy etc.</span>

				</form>
				<br>				
			</div>
			<br> <br>
			<%-- 					<c:if test="${not empty setOfConceptMapObjects}">
						
							<c:forEach items="${setOfConceptMapObjects}" var="questionLinks">
								<a href="${pageContext.request.contextPath}/" style="color: gray">${questionLinks.questionTitle}</a>
								<hr>
							</c:forEach>
						
					</c:if>
 --%>
 			<c:if test="${empty setOfConceptMapObjects}">
				<span style="color: white">No Results Found!</span>
			</c:if>
			<c:if test="${not empty setOfConceptMapObjects}">
				<script>



var data = ${json};



var outer = d3.map();
var inner = [];
var links = [];

var outerId = [0];

data.forEach(function(d){
	
	if (d == null)
		return;
	
	i = { id: 'i' + inner.length, name: d[0], related_links: [],click: d[2] };
	i.related_nodes = [i.id];
	inner.push(i);
	
	if (!Array.isArray(d[1]))
		d[1] = [d[1]];
	
	d[1].forEach(function(d1){
		
		o = outer.get(d1);
		
		if (o == null)
		{
			o = { name: d1,	id: 'o' + outerId[0], related_links: [] };
			o.related_nodes = [o.id];
			outerId[0] = outerId[0] + 1;	
			
			outer.set(d1, o);
		}
		
		// create the links
		l = { id: 'l-' + i.id + '-' + o.id, inner: i, outer: o }
		links.push(l);
		
		// and the relationships
		i.related_nodes.push(o.id);
		i.related_links.push(l.id);
		o.related_nodes.push(i.id);
		o.related_links.push(l.id);
	});
});

data = {
	inner: inner,
	outer: outer.values(),
	links: links
}

// sort the data -- TODO: have multiple sort options
outer = data.outer;
data.outer = Array(outer.length);


var i1 = 0;
var i2 = outer.length - 1;

for (var i = 0; i < data.outer.length; ++i)
{
	if (i % 2 == 1)
		data.outer[i2--] = outer[i];
	else
		data.outer[i1++] = outer[i];
}

console.log(data.outer.reduce(function(a,b) { return a + b.related_links.length; }, 0) / data.outer.length);


// from d3 colorbrewer: 
// This product includes color specifications and designs developed by Cynthia Brewer (http://colorbrewer.org/).
var colors = ["#a50026","#d73027","#f46d43","#fdae61","#fee090","#ffffbf","#e0f3f8","#abd9e9","#74add1","#4575b4","#313695"]
var color = d3.scale.linear()
    .domain([100000, 6000000])
    .range([colors.length-1, 0])
    .clamp(true);

var diameter = 960;
var rect_width = 50;
var rect_height = 14;

var link_width = "1px"; 

var il = data.inner.length;
var ol = data.outer.length;

var inner_y = d3.scale.linear()
    .domain([0, il])
    .range([-(il * rect_height)/2, (il * rect_height)/2]);

mid = (data.outer.length/2.0)
var outer_x = d3.scale.linear()
    .domain([0, mid, mid, data.outer.length])
    .range([15, 170, 190 ,355]);

var outer_y = d3.scale.linear()
    .domain([0, data.outer.length])
    .range([0, diameter / 2 - 120]);


// setup positioning
data.outer = data.outer.map(function(d, i) { 
    d.x = outer_x(i);
    d.y = diameter/3;
    return d;
});

data.inner = data.inner.map(function(d, i) { 
    d.x = -(rect_width / 2);
    d.y = inner_y(i);
    return d;
});


function get_color(name)
{
    var c = Math.round(color(name));
    if (isNaN(c))
        return '#dddddd';	// fallback color
    
    return colors[c];
}



function projectX(x)
{
    return ((x - 90) / 180 * Math.PI) - (Math.PI/2);
}

var diagonal = d3.svg.diagonal()
    .source(function(d) { return {"x": d.outer.y * Math.cos(projectX(d.outer.x)), 
                                  "y": -d.outer.y * Math.sin(projectX(d.outer.x))}; })            
    .target(function(d) { return {"x": d.inner.y + rect_height/2,
                                  "y": d.outer.x > 180 ? d.inner.x : d.inner.x + rect_width}; })
    .projection(function(d) { return [d.y, d.x]; });


var svg = d3.select("body").append("svg")
    .attr("width", diameter)
    .attr("height", diameter)
  .append("g")
    .attr("transform", "translate(" + diameter / 2 + "," + diameter / 2 + ")");
    

// links
var link = svg.append('g').attr('class', 'links').selectAll(".link")
    .data(data.links)
  .enter().append('path')
    .attr('class', 'link')
    .attr('id', function(d) { return d.id })
    .attr("d", diagonal)
    .attr('stroke', function(d) { return get_color(d.inner.name); })
    .attr('stroke-width', link_width);

// outer nodes

var onode = svg.append('g').selectAll(".outer_node")
    .data(data.outer)
  .enter().append("g")
    .attr("class", "outer_node")
    .attr("transform", function(d) { return "rotate(" + (d.x - 90) + ")translate(" + d.y + ")"; })
    .on("mouseover", mouseover)
    .on("mouseout", mouseout);
  
onode.append("circle")
    .attr('id', function(d) { return d.id })
    .attr("r", 4.5);
  
onode.append("circle")
    .attr('r', 20)
    .attr('visibility', 'hidden');
  
onode.append("text")
	.attr('id', function(d) { return d.id + '-txt'; })
    .attr("dy", ".31em")
    .attr("text-anchor", function(d) { return d.x < 180 ? "start" : "end"; })
    .attr("transform", function(d) { return d.x < 180 ? "translate(8)" : "rotate(180)translate(-8)"; })
    .text(function(d) { return d.name; });
  
// inner nodes
  
var inode = svg.append('g').selectAll(".inner_node")
    .data(data.inner)
  .enter().append("g")
    .attr("class", "inner_node")
    .attr("transform", function(d, i) { return "translate(" + d.x + "," + d.y + ")"})
    .on("mouseover", mouseover)
    .on("mouseout", mouseout);
  
inode.append('rect')
    .attr('width', rect_width)
    .attr('height', rect_height)
    .attr('id', function(d) { return d.id; })
    .attr('fill', function(d) { return get_color(d.name); })
    .on("click",function(d){
		var url = "${pageContext.request.contextPath}/getQuestionAnswers?searchKeyword=${searchKeyword}&id="+d.name+"&name="+d.click;

	$(location)
			.attr(
					'href',
					url);
	window.location = url;

    	
    })
  
inode.append("text")
	.attr('id', function(d) { return d.id + '-txt'; })
    .attr('text-anchor', 'middle')
    .attr("transform", "translate(" + rect_width/2 + ", " + rect_height * .75 + ")")
    .text(function(d) { return d.name; });

inode.append("title")
      .text(function(d) { return d.click; });

// need to specify x/y/etc

d3.select(self.frameElement).style("height", diameter - 150 + "px");

function mouseover(d)
{
	// bring to front
	d3.selectAll('.links .link').sort(function(a, b){ return d.related_links.indexOf(a.id); });	
	
    for (var i = 0; i < d.related_nodes.length; i++)
    {
        d3.select('#' + d.related_nodes[i]).classed('highlight', true);
        d3.select('#' + d.related_nodes[i] + '-txt').attr("font-weight", 'bold');
		
    }
    
    for (var i = 0; i < d.related_links.length; i++){
		d3.select('#' + d.related_links[i]).attr('stroke-width', '5px');
		
	}
        
}

function mouseout(d)
{   	
    for (var i = 0; i < d.related_nodes.length; i++)
    {
        d3.select('#' + d.related_nodes[i]).classed('highlight', false);
        d3.select('#' + d.related_nodes[i] + '-txt').attr("font-weight", 'normal');
    }
    
    for (var i = 0; i < d.related_links.length; i++)
        d3.select('#' + d.related_links[i]).attr('stroke-width', link_width);
}

</script>


			</c:if>





			<!-- /.row -->



			<!-- </div> -->
			<!-- /.container-fluid -->
		<!-- </div> -->


		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
</body>

</html>