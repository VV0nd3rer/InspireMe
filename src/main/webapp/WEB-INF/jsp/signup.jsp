<%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %> --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>SignUp</title>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<h2>Sign Up</h2>
				<form:form action="addUser" modelAttribute="user">
					<form:errors path="*">
						<div><spring:message code="error.global" /></div>
				   	</form:errors>
				   	<div>
						
							<form:input path="login"  placeholder="Login"/> 
						
						<p id="errorLogin" class="inputError">
							<form:errors path="login" htmlEscape="false" />
						</p>
					</div>
					<div>
				
						    <form:password path="password"  placeholder="Password" />
						<p id="errorPass" class="inputError">
							<form:errors path="password" htmlEscape="false" />
						</p>
					</div>
					<div>
						    <form:password path="confirmPassword" placeholder="Confirm password"/>
					</div>
					
					 <div>
					 Enter the code
   					 <input type="text" name="jcaptchaResponse" value="" />
   					 <img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
     				 <button name="RefreshButton" onclick="refresh()" type="button">Refresh</button>
   					 </div>
   					 
				<p><input type="submit" value="Register"></input></p>
				</form:form>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
		<script type="text/javascript">
			function refresh() 
			{
			var image=document.getElementById("captchaImg");
			image.src="<%=request.getContextPath()%>/jcaptcha.jpg?"+Math.floor(Math.random()*100)           
			}
</script>
	</body>
</html>