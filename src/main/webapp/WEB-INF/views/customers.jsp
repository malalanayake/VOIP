<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="customers/add" method="POST" modelAttribute="customer"
		class="form-horizontal" role="form">
		<div class="form-group">
			<label for="phoneNumber" class="col-sm-2 control-label">Phone number
			</label>
			<div class="col-sm-10">
				<form:input type="text" path="phoneNumber" name="phoneNumber"
					class="form-control" placeholder="Phone Number" />
			</div>
			<div>
				<form:errors path="phoneNumber" cssClass="error"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<label for="countryService" class="col-sm-2 control-label">Select
				Service</label>
			<div class="col-sm-10">
				<form:select path="countryService" cssClass="form-control" >
				</form:select>
			</div>
			<div>
				<form:errors path="countryService" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name</label>
			<div class="col-sm-10">
				<form:input path="name" name="name" cssClass="form-control" placeholder="Customer Name" />
			</div>
			<div>
				<form:errors path="name" cssClass="error" />
			</div>
		</div>

		<div class="form-group">
			<label for="city" class="col-sm-2 control-label">City</label>
			<div class="col-sm-10">
				<form:input path="city" name="city" cssClass="form-control" placeholder="City " />
			</div>
			<div>
				<form:errors path="city" cssClass="error" />
			</div>
		</div>

		<div class="form-group">
			<label for="state" class="col-sm-2 control-label">State</label>
			<div class="col-sm-10">
				<form:input path="state" name="state" cssClass="form-control" placeholder="State"/>
			</div>
			<div>
				<form:errors path="state" cssClass="error" />
			</div>
		</div>

		<div class="form-group">
			<label for="zip" class="col-sm-2 control-label">Zip</label>
			<div class="col-sm-10">
				<form:input path="zip" name="zip" cssClass="form-control" />
			</div>
			<div>
				<form:errors path="zip" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<form:button type="submit" class="btn btn-default">Submit</form:button>
			</div>
		</div>
	</form:form>
</body>
</html>