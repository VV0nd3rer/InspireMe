<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/user.js"></script>
	<title>SignUp</title>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
			<h4><spring:message code="text.signUp"/></h4>
			<form:form action="${url}" modelAttribute="user">
				<form:errors path="*">
					<div>
						<spring:message code="error.global" />
					</div>
				</form:errors>
				<p><spring:message code="label.username"/></p>
				<p>
					<form:input path="login" />
					<em id="loginError" class="inputError"></em>
				</p>
				<p><spring:message code="label.email"/></p>
				<p>
					<form:input path="email"/>
					<em id="emailError" class="inputError"></em>
				</p>
				<p><spring:message code="label.password"/></p>
				<p>
					<form:password path="password"/>
					<em id="passwordError" class="inputError"></em>
				</p>
				<p><spring:message code="label.confirmPassword"/></p>
				<p><form:password path="confirmPassword"/></p>
				<p>
					<form:input path="captcha"/>
					<em id="captchaError" class="inputError"></em>
					<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
					<button name="RefreshButton" onclick="refreshCaptcha()" type="button"><spring:message code="button.refresh"/></button>
			    </p>
				<%-- <p> 
					<input type="text" name="jcaptchaResponse" value=""/>
					<em id="captchaError" class="inputError"></em> 
					<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
					<button name="RefreshButton" onclick="refreshCaptcha()" type="button">Refresh</button>
				</p>--%>
				<spring:message code="button.ok" var="btnLabel"/>
				<p>
					<input type="submit" id="register" value="${btnLabel}"></input>
				</p>
			</form:form>
		</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>