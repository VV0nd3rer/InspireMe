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
				<h2>Sign Up</h2>
			<form:form action="${url}" modelAttribute="user">
				<form:errors path="*">
					<div>
						<spring:message code="error.global" />
					</div>
				</form:errors>
				<p>
					<form:input path="login" />
					<em id="loginError" class="inputError"></em>
				</p>
				<p>
					<form:input path="email" placeholder="Email" />
					<em id="emailError" class="inputError"></em>
				</p>
				<p>
					<form:password path="password" placeholder="Password" />
					<em id="passwordError" class="inputError"></em>
				</p>
				<p>
				    <div class='pwdwidgetdiv' id='thepwddiv'></div>
				</p>
				<p>
					<form:password path="confirmPassword"
						placeholder="Confirm password" />
				</p>
				<p>
					<form:input path="captcha" placeholder="Enter the code" />
					<em id="captchaError" class="inputError"></em> 
					<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
					<button name="RefreshButton" onclick="refreshCaptcha()"
						type="button">Refresh</button>
				</p>
				<p>
					<input type="submit" id="register" value="Register"></input>
				</p>
			</form:form>
		</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>