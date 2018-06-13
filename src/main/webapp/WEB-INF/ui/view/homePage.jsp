<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body ng-app="myApp">
	<div ng-controller="ProductController as ctrl">
		<div ng-repeat="u in ctrl.products">
			<span ng-bind="u.productId"></span>
			<span ng-bind="u.productName"></span>
			<span ng-bind="u.description"></span>
			<span ng-bind="u.productPrice"></span>
			<span ng-bind="u.category"></span>
			<button type="button" >Add to Cart</button>
		</div>
		
	</div>
	<%@ include file = "signUp.jsp" %>
	<h1><a href="/shopng/login">Login</a></h1>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/resources/js/app.js' />"></script>
      <script src="<c:url value='/resources/js/services/product_service.js' />"></script>
      <script src="<c:url value='/resources/js/controller/product_controller.js' />"></script>
</body>

</html>