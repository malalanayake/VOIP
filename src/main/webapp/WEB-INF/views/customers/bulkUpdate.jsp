<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form class="upload-form" action='${context }/customers/bulkUpdate' role="form" method="post" enctype="multipart/form-data">
	<fieldset>
			<legend>
				<img width=32 style='margin-right: 5px;vertical-align:text-bottom' src='${context }/resources/img/upload-folder.png' alt='upload call'/>
				Upload Customers list
			</legend>
		</fieldset>
		<div class="upload-element">
			<img src='${context }/resources/img/upload.png' />
			<input type="file" id="uploadFile" name="file" />
			<div id='attachedFile'>No file chosen</div>
		</div>
		<button type="submit" class="btn btn-success">Update and proccess</button>
	</form:form>
	<script type="text/javascript">
		$(function(){
			$('#uploadFile').change(function(){ $('#attachedFile').html($(this).val()) });
		});
	</script>
</body>
</html>