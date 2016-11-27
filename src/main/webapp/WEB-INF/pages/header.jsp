
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">User:${user}</a>
	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<li><a href="${pageContext.request.contextPath}/welcome"><i
				class="fa fa-home fa-2x"></i></a></li>
		<li><a href="${logoutUrl}"><i class="fa fa-fw fa-power-off"></i>
				Log Out</a></li>
	</ul>
	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	<jsp:include page="leftMenu.jsp"></jsp:include>
	<!-- /.navbar-collapse -->
</nav>