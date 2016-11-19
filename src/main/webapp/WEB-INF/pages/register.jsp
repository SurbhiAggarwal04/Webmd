<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title >inSite</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form-elements.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/resources/ico/apple-touch-icon-57-precomposed.png">
    </head>

    <body>

		<!-- Top menu -->
	<!--	<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.html">Bootstrap Multi Step Registration Form Template</a>
				</div>
				 Collect the nav links, forms, and other content for toggling 
				
			</div>
		</nav>-->

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1 style="color:#FF8C00;">inSite<span style="color:black;"><br>  Registration</span></h1>
                            <div class="description">
                            	<p style="color:#FFFFF0;">
	                            	<strong>
                            	</strong>
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	
                        	<form:form role="form" action='${pageContext.request.contextPath}/register'  method="post" class="registration-form" modelAttribute="user" id="myForm">
                        		
                        		<fieldset>
		                        	<div class="form-top">
		                        		<div class="form-top-left">
		                        		<c:if test="${done == 0}" >
		                        			<p>Please complete the following registration information to proceed :</p>
		                        			<big><a href="${pageContext.request.contextPath}/login">Go back to Login</a></big>
		                        			</c:if>
		                        			<c:if test="${done == 1}" >
		                        			<p>Registration Successful  !!!!! Please click on the following link to proceed to Login page !!!!</p>
		                        			<a href="${pageContext.request.contextPath}/login"><strong>Login</strong></a>
		                        			</c:if>
		                        			<c:if test="${done == null}" >
		                        			<p>Please complete the following registration information to proceed :</p>
		                        			<big><a href="${pageContext.request.contextPath}/login">Go back to Login</a></big>
		                        			</c:if>
		                            		<!-- <p>Tell us who you are:</p> -->
		                        		</div>
		                        		
		                            </div>
								<div class="form-bottom">

									<div class="form-group">
										<label class="sr-only" for="form-username">Username</label>
										<form:input type="text" data-toggle="tooltip" title="Username" name="username"
											placeholder="Username..." class="form-username form-control"
											id="form-username" path="username"></form:input>
										<c:if test="${ usernameExists != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${usernameExists}</strong>
											</div>
										</c:if>
										<c:if test="${ username != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${username}</strong>
											</div>
										</c:if>
									</div>

									<div class="form-group">
										<label class="sr-only" for="form-password">Password</label>
										<form:input type="password" data-toggle="tooltip" title="Password" name="password"
											placeholder="Password..." class="form-password form-control"
											id="form-password" path="password" />
										<c:if test="${ password != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${password}</strong>
											</div>
										</c:if>
									</div>


									<div class="form-group">
										<label class="sr-only" for="form-repeat-password">Repeat
											password</label> <input type="password" data-toggle="tooltip" title="Repeat Password" name="form-repeat-password"
											placeholder="Repeat password..."
											class="form-repeat-password form-control"
											id="form-repeat-password"></input>
										<c:if test="${ repeatedPass != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${repeatedPass}</strong>
											</div>
										</c:if>
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-first-name">First
											name</label>
										<form:input data-toggle="tooltip" title="First Name" type="text" name="firstName"
											placeholder="First name..."
											class="form-first-name form-control" id="form-first-name"
											path="firstName" />
										<c:if test="${ firstName != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${firstName}</strong>
											</div>
										</c:if>
									</div>


									<div class="form-group">
										<label class="sr-only" for="form-last-name">Last name</label>
										<form:input data-toggle="tooltip" title="Last Name" type="text" name="lastName"
											placeholder="Last name..."
											class="form-last-name form-control" id="form-last-name"
											path="lastName" />
										<c:if test="${ lastName != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${lastName}</strong>
											</div>
										</c:if>
									</div>


									<div class="form-group">
										<label class="sr-only" for="form-email">Email</label>
										<form:input data-toggle="tooltip" title="Email" type="text" name="emailID"
											placeholder="Email..." class="form-email form-control"
											id="form-email" path="emailID"></form:input>
										<c:if test="${ emailID != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${emailID}</strong>
											</div>
										</c:if>
									</div>

									<div class="form-group">
										<label class="sr-only" for="form-phone">Phone</label>
										<form:input data-toggle="tooltip" title="Phone" type="text" name="phone"
											placeholder="Phone..." class="form-phone form-control"
											id="form-phone" min="1" max="5" maxlength="10" size="3"
											path="phone"></form:input>
										<c:if test="${ phone != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${phone}</strong>
											</div>
										</c:if>
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-address">Address</label>
										<form:input data-toggle="tooltip" title="Address" type="text" name="addressLines"
											placeholder="Address..." class="form-address1 form-control"
											id="form-address" min="1" max="5" size="3" path="addressLine"></form:input>
										<c:if test="${ addressLine != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${addressLine}</strong>
											</div>
										</c:if>
									</div>


									<div class="form-group">
										<label class="sr-only" for="form-city">City</label>
										<form:input data-toggle="tooltip" title="City" type="text" name="city" placeholder="City..."
											class="form-city form-control" id="form-city" min="1" max="5"
											path="city"></form:input>
										<c:if test="${ city != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${city}</strong>
											</div>
										</c:if>
									</div>


									<div class="form-group ">
										<label class="sr-only" for="form-state">State</label>
										<form:input data-toggle="tooltip" title="State" type="text" name="state"
											placeholder="State..." class="form-state form-control"
											id="form-state" min="1" max="5" maxlength="10" path="state"></form:input>
										<c:if test="${ state != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${state}</strong>
											</div>
										</c:if>
									</div>


									<div class="form-group">
										<label class="sr-only" for="form-zip">Zip</label>
										<form:input data-toggle="tooltip" title="Zip" type="text" name="zip" placeholder="Zip..."
											class="form-zip form-control" id="form-zip" min="1" max="5"
											maxlength="10" path="zip"></form:input>
										<c:if test="${ zip != null}">
											<div class="alert alert-danger" style="">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<i class="fa fa-info-circle"></i> <strong>${zip}</strong>
											</div>
										</c:if>
									</div>
									<input type="submit" class="btn btn-primary btn-lg"
										value="Sign me up!">
								</div>
							</fieldset>
		                    <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		                    </form:form>
		                    
                        </div>
                    </div>
                </div>
            </div>
            
       </div>


        <!-- Javascript -->
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.backstretch.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/retina-1.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
       <script type="text/javascript">
       document.getElementById("form-password").value = "";
       document.getElementById("form-username").value = "";
       document.getElementById("form-zip").value = "";
       document.getElementById("form-state").value = "";
       document.getElementById("form-city").value = "";
       document.getElementById("form-phone").value = "";
       document.getElementById("form-address").value = "";
       document.getElementById("form-email").value = "";
       document.getElementById("form-last-name").value = "";
       document.getElementById("form-first-name").value = "";
        $('input[type=text][name=username]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	}); 
        $('input[type=password][name=password]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	}); 
        $('input[type=password][name=form-repeat-password]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	}); 
        $('input[type=text][name=firstName]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	}); 
        $('input[type=text][name=lastName]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	}); 
        $('input[type=text][name=emailID]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	}); 
        $('input[type=text][name=phone]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	});
        $('input[type=text][name=addressLine]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	});
        $('input[type=text][name=city]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	});
        $('input[type=text][name=state]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	});
        $('input[type=text][name=zip]').tooltip({
    	    placement: "right",
    	    trigger: "focus"
    	});
        
       </script>
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>
</html>