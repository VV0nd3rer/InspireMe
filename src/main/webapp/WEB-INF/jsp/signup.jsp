<%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %> --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/user.js"></script>
	<script src="<%=request.getContextPath() %>/js/jquery.passstrength.js"></script>
	<title>SignUp</title>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<h2>Sign Up</h2>
				<form:form action="${url}" modelAttribute="user">
					<form:errors path="*">
						<div><spring:message code="error.global" /></div>
				   	</form:errors>
				   	<div id="loginGroup">		
						<form:input path="login"/> 
						<p id="loginError" class="inputError">
							<form:errors path="login" htmlEscape="false" />
						</p>
					</div>
					<div id="emailGroup">		
						<form:input path="email"  placeholder="Email"/> 
						<p id="emailError" class="inputError">
							<form:errors path="email" htmlEscape="false" />
						</p>
					</div>
					<div id="passwordGroup">
						<form:password path="password"  placeholder="Password" />
						<p id="passwordError" class="inputError">
							<form:errors path="password" htmlEscape="false" />
						</p>
					</div>
					<div>
						<form:password path="confirmPassword" placeholder="Confirm password"/>
						<p id="confirmPasswordError" class="inputError">
							<form:errors path="confirmPassword" />
						</p>
					</div>
				
				 <div>
				    <form:input path="captcha" placeholder="Enter the code"/>
				    <p class="inputError">
				           <form:errors path="captcha" />
				    </p>
					<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
					<button name="RefreshButton" onclick="refresh()" type="button">Refresh</button>
				 </div>
				<p><input type="submit" id="register" value="Register"></input></p>
				</form:form>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	 <script type="text/javascript">
		function refresh() {
			var image = document.getElementById("captchaImg");
		    image.src = "<%=request.getContextPath()%>/jcaptcha.jpg?"+Math.floor(Math.random()*100)           
		}
	</script>
	</body>
</html>