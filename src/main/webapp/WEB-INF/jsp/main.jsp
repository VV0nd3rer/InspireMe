<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Main</title>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<p>${content['allUsersMessage']}</p> 
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>