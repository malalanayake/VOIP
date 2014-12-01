<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="monthly-traffic/excel" class="form-inline">
		<div class="form-group">
			<input type="date" class="form-control" name="date" />
		</div>
		<button type="submit" class="btn btn-success">Generate Report</button>
	</form>
</body>
</html>