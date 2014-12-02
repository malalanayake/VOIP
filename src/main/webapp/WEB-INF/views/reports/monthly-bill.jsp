<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function showBillList() {
		var customerId = $("#customer").val();
		var date = $("#date").val();
		var path = '${context}' + '/report/monthlyBill/list/' + customerId + "/"+ date;
		$.ajax({
			url :path ,
			type : 'GET',
			success : function(msg) {
				$("#monthlyBilllTable").html($("#monthlyBilllTable").html() + msg)
				$("#monthlyBillList").show();
				$("#download").show();
			},
			error : function(a, b, c) {
				alert(a + ", " + ", " + c);
			}
		});
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="monthly-bill/pdf" class="form-inline">
		<div class="form-group">
			<select id="customer" name="customer" class="form-control ">
				<c:forEach items="${customerList }" var="customer">
					<option value="${customer.phoneNumber }">${customer.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
		
			<input type="date" class="form-control" name="date" id="date" />
		</div>
		<button type="button" class="btn btn-success" onclick=" showBillList();">Generate Report</button>
		<button type="submit" class="btn btn-success" id="download">Download Report</button>
	</form>
	<div style="display: none;" id="monthlyBillList">
		<table class="table table-bordered" id="monthlyBilllTable">
				<thead>
					<tr>
						<td>Date</td>
						<td>Time</td>
						<td>Duration</td>
						<td>Country</td>
						<td>Phone No </td>
						<td>Cost</td>
					</tr>
				</thead>
			</table>
		</div>
</body>
</html>