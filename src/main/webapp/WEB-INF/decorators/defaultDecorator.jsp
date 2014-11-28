<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VOIP</title>
<link rel="stylesheet"
	href="${context}/resources/bootstrap-3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${context}/resources/css/main.css">
<script type="text/javascript"
	src="${context}/resources/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="${context}/resources/bootstrap-3.2.0/js/bootstrap.min.js"></script>
<sitemesh:write property='head' />
</head>
<body class="container">
	<header>
	<div>
		<a href="${context}"><img
			src='${context}/resources/logo/voip_logo.jpg' alt='logo' /></a>
	</div>
	</header>
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${context}">Home</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Rates
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${context}/update-rates">Update
								international calling rates</a></li>
						<li class="divider"></li>
						<li><a href="${context}/output-rates">Output current
								international calling rates</a></li>
						<li class="divider"></li>
						<li><a href="${context}/process-call-file">Process
								calling files</a></li>
						<li class="divider"></li>
						<li><a href="${context}/create-rate-sheet">Create rate
								sheets</a></li>
					</ul></li>


				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Customers
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${context }/customers">Add a Customer</a></li>
						<li class="divider"></li>
						<li><a href="${context }/customers/bulkUpdate">Upload
								Customer List</a></li>
						<li class="divider"></li>
						<li><a href="${context }/customers/list">Show Customers</a>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Reports
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${context }/report/callrates">Call Rate</a></li>
						<li class="divider"></li>
						<li><a href="${context }/report/monthlybills">Monthly Bill</a></li>
						<li class="divider"></li>
						<li><a href="${context }/report/monthlytraffic">Monthly Traffic</a>
						<li class="divider"></li>
						<li><a href="${context }/report/salesrepcommision">Sell_Rep Commision</a>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<sitemesh:write property='body' />
	<footer> </footer>
</body>
</html>