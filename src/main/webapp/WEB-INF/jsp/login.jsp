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
			<div id="content" class="common_link">
				<h4><spring:message code="text.logIn"/></h4>
				<c:if test="${param.failed == true}">
				    <p class="inputError"><spring:message code="error.login"/></p>
				</c:if>
				<form id="loginForm" action="${postLoginUrl}" method="POST">
					<p><spring:message code="label.username"/></p>
					<p><input type="text" name="j_username"/></p>
					<p><spring:message code="label.password"/></p>
					<p><input type="password" name="j_password"/></p>
					<p>
					  <input type="checkbox" name="_spring_security_remember_me" />
					  <spring:message code="text.rememberMe"/>
					</p>
					<spring:message code="button.ok" var="btnLabel"/>
					<p><input type="submit" value="${btnLabel}" /></p>
					<a href="recoverPasswordPage"><spring:message code="text.forgotPass"/></a>
				</form>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>