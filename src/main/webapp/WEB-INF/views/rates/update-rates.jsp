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
	<div id='loading' class='hide'>
		<div id='background'></div>
		<div>
			<img src="${context }/resources/img/framely.gif" alt="loading"/>
		</div>
	</div>
	<form:form class="upload-form" role="form" action="${context }/update-rates" method="post" enctype="multipart/form-data">
		
		<fieldset>
			<legend>
				<img width=32 style='margin-right: 5px;vertical-align:text-bottom' src='${context }/resources/img/upload-folder.png' alt='upload call'/>
				Upload International calling rates
			</legend>
		</fieldset>
		<div style="margin-bottom: 5px;">
			<span>Date: </span>
			<input style="height:30px;border-radius:4px;border:1px solid #ccc;" id='date-input' type="date" name="date" />
		</div>
		<div class="upload-element">
			<img src='${context }/resources/img/upload.png' />
			<input type="file" id="uploadFile" name="rates" />
			<div id='attachedFile'>No file chosen</div>
		</div>
		<button id="upload" type="submit" class="btn btn-success">Upload and process</button>
		<button id="getSample" type="submit" class="btn btn-primary">Get sample file</button>
	</form:form>
	<script type="text/javascript">
		$(function(){
			$('form button').click(function(){
				if($(this).attr('id')=='upload'){
					$('#loading').toggleClass('hide');
					$('form').attr('action','${context}/update-rates').submit();
				}else{
					$('form').attr('action','${context}/getSampleCallRateExcel').submit();
				}
				return false;
			});
			$('#uploadFile').change(function(){ $('#attachedFile').html($(this).val()) });
		});
	</script>
</body>
</body>
</html>