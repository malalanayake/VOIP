<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<script type="text/javascript">
	function showTraffic() {
		var date = $("#date").val();
		var path = '${context}' + '/report/monthlyTraffic/list/' + "/"+ date;
		$('#loading').toggleClass('hide');
		$.ajax({
			url :path ,
			type : 'GET',
			success : function(msg) {
				$('#loading').toggleClass('hide');
				var header = "<thead><tr><td>Service</td><td>Source Country</td><td>Destination Country</td><td>Total Calls(mins)</td></tr></thead>";
				$("#monthlyTrafiTable").html(header + msg)
				$("#monthlyTraffic").show();
				$("#download").show();
			},
			error : function(a, b, c) {
				$('#loading').toggleClass('hide');
				alert(a + ", " + ", " + c);
			}
		});
		$('#download').click(function(){
			$('#loading').toggleClass('hide');
			$('form').submit();
			return false;
		});
	}
</script>

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
	<form method="POST" action="monthly-traffic/excel" class="form-inline">
		<fieldset>
			<legend>
				<img width=32 style='margin-right: 5px;vertical-align:text-bottom' src='${context }/resources/img/custom-reports.png' alt='upload call'/>
				Generate monthly traffic report
			</legend>
		</fieldset>
		<div class="form-group" >
			<div class="input-append date" id="datepicker" data-date="2014-12"
				data-date-format="yyyy-mm">

				<input type="text" class="" readonly="readonly" name="date" id="date"> <span
					class="add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
		<button type="button" class="btn btn-success" onclick=" showTraffic();">Generate Report</button>
		<button type="submit" id="download" class="btn btn-success">Download Report</button>
	</form>
	<script type="text/javascript">
		$(function() {
			$("#datepicker").datepicker({
				format : "yyyy-mm",
				viewMode : "months",
				minViewMode : "months"
			});
		});
	</script>
	
	<div style="display: none;" id="monthlyTraffic">
		<table class="table table-bordered" id="monthlyTrafiTable">
				<thead>
					<tr>
						<td>Service</td>
						<td>Source Country</td>
						<td>Destination Country</td>
						<td>Total Calls(mins)</td>
					</tr>
				</thead>
			</table>
		</div>
</body>
</html>