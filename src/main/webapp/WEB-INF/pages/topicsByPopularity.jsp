<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>welcome</title>

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
	<c:url value="/logout" var="logoutUrl" />
	<div id="wrapper">

		<!-- header -->
		<jsp:include page="header.jsp"></jsp:include>

		<%--         <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
               
                <div class="row col-md-12">
                <h1 class="page-header">
                            Customer Information
                        </h1>
                    <div class="col-md-4">
                        
                        <form:form role="form" action='${pageContext.request.contextPath}/NewCustomer'  method="post" class="registration-form" modelAttribute="customer">
                       
                       
                        <div class="inner-addon left-addon">
                       
                        </div>
                     
                        <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-asterisk"></i>
                         <form:input data-toggle="tooltip" title="Customer Name" type="text" class="form-control" style="" placeholder="Customer Name...." name="name" id="name" path="name"/>               
                         <c:if test="${ name != null}">
                         <div class="alert alert-danger" style="">
                              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                             <i class="fa fa-info-circle"></i>  <strong>${name}</strong>
                        </div>
                         </c:if>
                         <br>
                        </div>
                         <div class="inner-addon left-addon" disabled>
                         <i class="glyphicon glyphicon-calendar"></i>
                         <input type="text" class="form-control" style="" placeholder="${date}" name="datepicker" id="disabledInput" disabled>
                         <br>
                         </div>
                       <!--  <ol class="breadcrumb">
                            <li class="active">
                                <i class="glyphicon glyphicon-info-sign"></i> Project ID
                            </li>
                        </ol>  -->
                        <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-briefcase"></i>
                         <form:input data-toggle="tooltip" title="Project Name" type="text" class="form-control" style="" placeholder="Project Name...." name="projectName" id="projectName" path="projectName"/>
                         <c:if test="${projectName != null}">
                         <div class="alert alert-danger">
                              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                             <i class="fa fa-info-circle"></i>  <strong>${projectName}</strong>
                        </div>
                         </c:if>
                         <br>
                        </div>
                        <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-map-marker"></i>
                         <input data-toggle="tooltip" title="Customer Street Address" type="text" class="form-control" style="" placeholder="Customer Street Address...." name="address" id="address"/>
                         <br>
                        </div>
                       <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-map-marker"></i>
                         <input data-toggle="tooltip" title="City" type="text" class="form-control" style="" placeholder="City...." name="city" id="city"/>
                         <br>
                        </div>
                       <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-map-marker"></i>
                         <input data-toggle="tooltip" title="State" type="text" class="form-control" style="" placeholder="State...." name="state" id="state"/>
                         <br>
                        </div>
                        <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-map-marker"></i>
                         <input data-toggle="tooltip" title="Zip Code" type="text" class="form-control" style="" placeholder="Zip Code...." name="zip" id="zip"/>
                         <br>
                        </div>
                       <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-phone"></i>
                         <form:input data-toggle="tooltip" title="Phone" type="text" class="form-control" style="" placeholder="Phone...." name="phone" id="phone" path="phone"/>
                         <c:if test="${phone != null}">
                         <div class="alert alert-danger" style="">
                              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                             <i class="fa fa-info-circle"></i>  <strong>${phone}</strong>
                        </div>
                         </c:if>
                         <br>
                        </div>
                         <div class="inner-addon left-addon">
                         <i class="glyphicon glyphicon-envelope"></i>
                         <form:input data-toggle="tooltip" title="Email" type="text" class="form-control"  placeholder="Email...." name="emailID" id="emailID" path="emailID"/>
                         <c:if test="${email != null}">
                         <div class="alert alert-danger" style="">
                              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                             <i class="fa fa-info-circle"></i>  <strong>${email}</strong>
                        </div>
                         </c:if>
                         <br>
                        </div>
                        <input type="submit" class="btn btn-primary btn-lg" value="Next" style="margin-left:100px;margin-right:auto;display:block;margin-top:0%;margin-bottom:0%;width:120px">
                        <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
                        </form:form>
                    </div>
                  
              
                </div>
                <!-- /.row -->

                

            </div>
            <!-- /.container-fluid -->

        </div>
 --%>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
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
		src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

</body>

</html>
