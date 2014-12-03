<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form id='addCustomer' action="customers/add" method="POST" commandName="customer"
		class="form-horizontal" role="form">
		<fieldset>
			<legend>Add new customer</legend>
		</fieldset>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name</label>
			<div class="col-sm-4">
				<form:input path="name" name="name" cssClass="form-control"
					placeholder="Customer Name" />
			</div>
			<div>
				<form:errors path="name" cssClass="error" />
			</div>
			<label for="phoneNumber" class="col-sm-2 control-label">Phone
				number </label>
			<div class="col-sm-4">
				<form:input type="text" path="phoneNumber" name="phoneNumber"
					class="form-control" placeholder="Phone Number" />
			</div>
			<div>
				<form:errors path="phoneNumber" cssClass="error"></form:errors>
			</div>
		</div>
		<div class="form-group">
			<label for="street" class="col-sm-2 control-label">Street</label>
			<div class="col-sm-10">
				<form:input path="street" name="street" cssClass="form-control"
					placeholder="Street" />
			</div>
			<div>
				<form:errors path="street" cssClass="error" />
			</div>
		</div>

		<div class="form-group">
			<label for="city" class="col-sm-2 control-label">City</label>
			<div class="col-sm-2">
				<form:input path="city" name="city" cssClass="form-control"
					placeholder="City " />
			</div>
			<div>
				<form:errors path="city" cssClass="error" />
			</div>
		

			<label for="state" class="col-sm-2 control-label">State</label>
			<div class="col-sm-2">
				<form:input path="state" name="state" cssClass="form-control"
					placeholder="State" />
			</div>
			<div>
				<form:errors path="state" cssClass="error" />
			</div>
		
			<label for="zip" class="col-sm-2 control-label">Zip</label>
			<div class="col-sm-2">
				<form:input path="zip" name="zip" cssClass="form-control" />
			</div>
			<div>
				<form:errors path="zip" cssClass="error" />
			</div>
		</div>
		<div class="form-group">
			<label for="countryService" class="col-sm-2 control-label">Select
				Service</label>
			<div class="col-sm-4">
				<form:select path="countryService" name="countryService">
					<c:forEach items="${countryServiceList}" var="service">
						<option value="${service.id }">${service.country.name }'s
							${service.service.name }</option>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<form:errors path="countryService" cssClass="error" />
			</div>
			
			<label for="salesRep" class="col-sm-2 control-label">Select Sales Rep</label>
			<div class="col-sm-4">
				<select  name="salesRep">
					<c:forEach items="${salesRepList}" var="salesRep">
						<option value="${salesRep.id }">${salesRep.code }</option>
					</c:forEach>
				</select>
			</div>
			<div>
<%-- 				<form:errors path="countryService" cssClass="error" /> --%>
			</div>
		</div>
		
		<div class="form-group">
			<label for="commision" class="col-sm-2 control-label"> Commission</label>
			<div class="col-sm-4">
				<input type="text"  name="commision" class="form-control" />
			</div>
			<div>
<%-- 				<form:errors path="commision" cssClass="error"></form:errors> --%>
			</div>
		</div>
		
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<form:button type="submit" class="btn btn-success">Add new customer</form:button>
			</div>
		</div>
	</form:form>
</body>
</html>