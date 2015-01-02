<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Main</title>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<spring:message code="${msg_code}"/>
			</div>
		<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>