<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet"
		href="${context}/resources/js/slider/jquery.bxslider.css">
</head>
<body>
<h1 style='text-align: center;border-bottom:1px solid #ccc;'>
	VOIP Inc  
</h1>
<div>
	<div style="margin:0 auto;width:940px;">
		<ul class="bxslider">
		  <li><img src="${context }/resources/img/1.jpg" /></li>
		  <li><img src="${context }/resources/img/2.jpg" /></li>
		  <li><img src="${context }/resources/img/3.jpg" /></li>
		</ul>
	</div>
	<script type="text/javascript" src='${context }/resources/js/highchart/js/highcharts.js'></script>
	<script type="text/javascript" src='${context }/resources/js/highchart/js/modules/exporting.js'></script>
	
	<script type="text/javascript" src='${context }/resources/js/slider/jquery.bxslider.min.js'></script>
	
	<script type="text/javascript">
	$(function () {
		$('.bxslider').bxSlider();
	});
	</script>
</div>
</body>
</html>
