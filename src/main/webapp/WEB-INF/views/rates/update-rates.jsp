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
	<form:form role="form" action="${context }/update-rates" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="exampleInputFile">Upload rate file</label> 
			<input type="file" id="exampleInputFile" name="rates"/>
			<p class="help-block">Example block-level help text here.</p>
		</div>
		<button id="upload" type="submit" class="btn btn-default">Submit</button>
		<button id="getSample" type="submit" class="btn btn-default">Get Sample</button>
	</form:form>
	<script type="text/javascript">
		$(function(){
			$('form button').click(function(){
				if($(this).attr('id')=='upload'){
					$('form').attr('action','${context}/update-rates').submit();
				}else{
					$('form').attr('action','${context}/getSampleCallRateExcel').submit();
				}
				return false;
			});
		});
	</script>
</body>
</body>
</html>