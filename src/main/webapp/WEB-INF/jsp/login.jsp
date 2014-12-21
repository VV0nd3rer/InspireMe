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
			  <h3><spring:message code="text.logIn"/></h3>
			  <div id="form-style">
				<form id="loginForm" action="${postLoginUrl}" method="POST">
					<c:if test="${param.failed == true}">
				      <p class="inputError"><spring:message code="error.login"/></p>
					</c:if>
					<label><span><spring:message code="label.username"/></span>
					 <input type="text" name="j_username"/>
					</label>
					<label><span><spring:message code="label.password"/></span>
					 <input type="password" name="j_password"/>
					</label>
					<label path="username">
					  <input type="checkbox" name="_spring_security_remember_me" />
					  <span><spring:message code="text.rememberMe"/></span>
					</label>
					<spring:message code="button.ok" var="btnLabel"/>
					<p>
					<input type="submit" value="${btnLabel}" />
					<a href="recoverPasswordPage"><spring:message code="text.forgotPass"/></a>
					</p>
				</form>
			  </div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>