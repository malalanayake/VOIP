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
	<div>
		<header>
		<div>
			<a href="${context}"><img
				src='${context}/resources/logo/voip_logo.jpg' alt='logo' /></a>
		</div>
		</header>
		<!-- flash message -->
		<c:if test="${success!=null}">
			<div class="alert alert-success" role="alert">${success}</div>
		</c:if>
		<c:if test="${error!=null}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<form id='account-form' class="form-horizontal" role="form"
			action="${context }/login" method="post">
			<fieldset>
				<div class="form-group">
					<div class="col-sm-12">
						<input class="form-control" id="inputEmail3" name="username"
							placeholder="username">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<input type="password" class="form-control" id="inputPassword3"
							name="password" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<button type="submit" class="col-sm-12 btn btn-success">Login</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>