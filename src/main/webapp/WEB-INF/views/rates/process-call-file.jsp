<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form role="form" action="${context }/process-call-file" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="exampleInputFile">Upload call file</label> 
			<input type="file" id="exampleInputFile" name="calls" />
			<p class="help-block">Example block-level help text here.</p>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form:form>
</body>
</html>