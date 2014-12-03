<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>
		<img width=40 style='margin-right: 5px;' src='${context }/resources/img/users.png' alt='users'/>
		List of Customers
	</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>Name</td>
				<td>Phone number</td>
				<td>City</td>
				<td>Street</td>
				<td>Service</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="customer" items="${customerList}">
				<tr>
					<td>${customer.name }</td>
					<td>${customer.phoneNumber }</td>
					<td>${customer.city }</td>
					<td>${customer.street }</td>
					<td>${customer.countryService.service.name }
					</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>