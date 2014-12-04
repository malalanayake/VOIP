<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form id='addCustomer' action="${context}/add-sale-rep" method="POST" commandName="salesRep"
		class="form-horizontal" role="form">
		<fieldset>
			<legend><img width=40 style='margin-right: 5px' src='${context }/resources/img/add-user-icon.png' alt='add customer'/>Add new sales rep</legend>
		</fieldset>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Code</label>
			<div class="col-sm-4">
				<form:input path="code" name="code" cssClass="form-control"
					placeholder="Sales Rep Code" />
			</div>
			<div>
				<form:errors path="code" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<form:button type="submit" class="btn btn-success">Add new sales rep</form:button>
			</div>
		</div>
	</form:form>
</body>
</html>