<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VOIP</title>
<link rel="stylesheet"
	href="${context}/resources/bootstrap-3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${context}/resources/css/login.css">
<script type="text/javascript"
	src="${context}/resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="${context}/resources/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<sitemesh:write property='head' />
</head>
<body class="container">
	<sitemesh:write property='body' />
</body>
</html>