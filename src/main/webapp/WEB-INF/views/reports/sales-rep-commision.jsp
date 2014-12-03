<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:set var="context" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function showCommissions() {
		var salesRepId = $('#salesRep option:selected').text();
		alert(salesRepId);
		var date = $("#date").val();
		var path = '${context}' + '/report/salesCommision/list/' + salesRepId + "/"+ date;
		$.ajax({
			url :path ,
			type : 'GET',
			success : function(msg) {
				$("#commissionTable").html($("#commissionTable").html() + msg)
				$("#salesCommissionList").show();
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

<form method="POST" action="sales-commission/pdf" class="form-inline">
		<div class="form-group">
			<select name="salesRep" class="form-control " id="salesRep">
				<c:forEach items="${salesRepList }" var="salesRep">
					<option value="${salesRep.id }">${salesRep.code }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<input type="date" class="form-control" name="date" id="date"/>
		</div>
		<button type="button" class="btn btn-success" onclick=" showCommissions();">Generate Report</button>
		<button type="submit" style="display: none;" class="btn btn-success" id="download">Download Report</button>
	</form>
	<div style="display: none;" id="salesCommissionList">
		<table class="table table-bordered" id="commissionTable">
				<thead>
					<tr>
						<td>Customer</td>
						<td>Service</td>
						<td>Cost</td>
						<td>Commission</td>
					</tr>
				</thead>
			</table>
		</div>
</body>
</html>