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
				   <div id="form-style">
					<form:form action="resultAjax" modelAttribute="user" method="POST">
						<input type="hidden" id="msg_code" name="msg_code" value=""/>
						<form:label path="login"><span><spring:message code="label.username"/><span class="required">*</span></span>
							<form:input path="login"/>
							<em id="loginError" class="inputError"></em>
						</form:label>
						<form:label path="email"><span><spring:message code="label.email"/><span class="required">*</span></span>
							<form:input path="email"/>
							<em id="emailError" class="inputError"></em>
						</form:label>
						<form:label path="password"><span><spring:message code="label.password"/><span class="required">*</span></span>
							<form:password path="password"/>
							<em id="passwordError" class="inputError"></em>
						</form:label>
						<form:label path="confirmPassword"><span><spring:message code="label.confirmPassword"/><span class="required">*</span></span>
							<form:password path="confirmPassword"/>
						</form:label>
						<div class="captcha">
							<form:input path="captcha"/>
							<em id="captchaError" class="inputError"></em>
							<img class="word" src="/InspireMe/jcaptcha.jpg" id="captchaImg">
							<a class="icon" onclick="refreshCaptcha()" >
							   <img src="<%=request.getContextPath() %>/css/icons/refresh.png"/>
							</a>
						</div>
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