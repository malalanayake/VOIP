<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
	<form:form class="form-inline" role="form" action="call-rates/pdf"
		method="POST">
		<div class="form-group">
			<select class="form-control " name="countryService" id="countryService">
				<option value="-1">Select Country Service</option>
				<c:forEach items="${countryServiceList}" var="countryService">
					<option value="${countryService.id }">${countryService.country.name } --> ${countryService.service.name }</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<input type="date" class="form-control" name="date" />
		</div>
		<button type="submit" class="btn btn-success">Generate Report</button>
		<button id="excel" type="submit" class="btn btn-success">Generate
			Excel</button>
	</form:form>
	<script type="text/javascript">
		$(function() {
			$('form button').click(
					function() {
						if ($(this).attr('id') == 'excel') {
							$('form').attr('action',
									'${context}/report/call-rates/excel')
									.submit();
						} else {
							$('form').attr('action',
									'${context}/report/call-rates/pdf')
									.submit();
						}
						return false;
					});
		});
	</script>
</body>
</html>