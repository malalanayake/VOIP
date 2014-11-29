<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form id='account-form' class="form-horizontal" role="form"
		action="${context }/login" method="post">
		<fieldset>
			<legend>Please login. Username:admin, password:admin</legend>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-1 control-label">Username</label>
				<div class="col-sm-6">
					<input class="form-control" id="inputEmail3" name="username"
						placeholder="username">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-1 control-label">Password</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="inputPassword3"
						name="password" placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-6">
					<button type="submit" class="btn btn-success">Login</button>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>