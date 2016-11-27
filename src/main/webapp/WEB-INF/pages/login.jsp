
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Webmd</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form-elements.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-57-precomposed.png">
<style type="text/css">
h1.heading {
	color: orange;
	font-weight: bold;
	font-family: Calibri;
	font-size: 20
}

h1.subhead {
	color: #FF0000;
	font-weight: bold;
	font-family: Tahoma;
	font-size: 20
}
</style>
</head>

<body>
	<div class="top-content">
		<div></div>
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<div class="description">
							<p style="color: #FFFFF0;">
								<strong> </strong>
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">

						<form name='loginForm' action="<c:url value='/login' />"
							method='POST' class="registration-form">
							<div class="form-top">
								<div class="form-top-left">
									<p>Home Screen</p>
									<p>Enter the details below:</p>
								</div>
								<div class="form-top-right"></div>
							</div>
							<div class="form-bottom">
								<div class="form-group">
									<label class="sr-only" for="form-first-name">Username</label> <input
										data-toggle="tooltip" title="Username" type="text"
										name="username" placeholder="Username..." class="form-control">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-about-yourself">Password</label>
									<input data-toggle="tooltip" title="Password" type="password"
										name="password" placeholder="Password..." class="form-control">
								</div>
								<input type="submit" class="btn btn-primary btn-lg"
									value="Login"">
								<%-- 				                        <input type="button" class="btn btn-primary btn-lg"  onclick="location.href='${pageContext.request.contextPath}/register'" value="Register">
  --%>
							</div>

						</form>

					</div>
				</div>
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">

						<div class="description"></div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.backstretch.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/retina-1.1.0.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>
<script type="text/javascript">
$('input[type=text][name=username]').tooltip({
    placement: "right",
    trigger: "focus"
});
$('input[type=password][name=password]').tooltip({
    placement: "right",
    trigger: "focus"
});
    </script>
</html>