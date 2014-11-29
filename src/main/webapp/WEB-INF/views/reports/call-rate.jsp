<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form class="form-inline"  role="form" action="call-rates/pdf" method="POST">
		<div class="form-group">
			<select  class="form-control " name="country">
				<c:forEach items="${countries}" var="country">
					<option value="${country.code }">${country.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<select class="form-control " name="service">
				<c:forEach items="${services}" var="service">
					<option value="${service.id }">${service.name }</option>
				</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-success">Generate Report</button>
	</form:form>
</body>
</html>