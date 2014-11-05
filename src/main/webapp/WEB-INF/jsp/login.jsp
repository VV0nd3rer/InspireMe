<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="postLoginUrl" value="/j_spring_security_check?ajax=true" />
<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>LogIn</title>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<h4>Please, login</h4>
				<c:if test="${param.failed == true}">
					<p class="inputError">Your login attempt failed. Please try again.</p>
				</c:if>
				<form id="loginForm" action="${postLoginUrl}" method="POST">
					<p><input type="text" name="j_username" placeholder="Username"/></p>
					<p><input type="password" name="j_password" placeholder="Password"/></p>
					<p><input type="checkbox" name="_spring_security_remember_me" />
					Remember me</p>
					<input type="submit" value="Log in" /></p>
					<a href="recoverPasswordPage">Forgot password?</a>
				</form>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>