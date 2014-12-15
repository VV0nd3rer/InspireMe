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
			<div id="infoContent">
			<form:form action="${url}" modelAttribute="user" class="left_block">
				<form:errors path="*">
					<div>
						<spring:message code="error.global" />
					</div>
				</form:errors>
				<form:label path="login"><spring:message code="label.username"/></form:label>
				<form:input path="login" />
				<em id="loginError" class="inputError"></em>
				<form:label path="email"><spring:message code="label.email"/></form:label>
				<form:input path="email"/>
				<em id="emailError" class="inputError"></em>
				<form:label path="password"><spring:message code="label.password"/></form:label>
				<form:password path="password"/>
				<em id="passwordError" class="inputError"></em>
				<form:label path="confirmPassword"><spring:message code="label.confirmPassword"/></form:label>
				<form:password path="confirmPassword"/>
				<form:input path="captcha"/>
				<em id="captchaError" class="inputError"></em>
				<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
				<button name="RefreshButton" onclick="refreshCaptcha()" type="button">
				   <spring:message code="button.refresh"/>
				</button>
				<%-- <p> 
					<input type="text" name="jcaptchaResponse" value=""/>
					<em id="captchaError" class="inputError"></em> 
					<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
					<button name="RefreshButton" onclick="refreshCaptcha()" type="button">Refresh</button>
				</p>--%>
				<spring:message code="button.ok" var="btnLabel"/>
				<input type="submit" id="register" value="${btnLabel}"></input>
			</form:form>
			</div>
		</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>