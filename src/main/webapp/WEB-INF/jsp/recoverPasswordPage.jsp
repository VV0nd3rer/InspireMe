<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Recovering password</title>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<form:form action="recoverPassword" modelAttribute="parameters">
				   <p>Enter your email</p>
				   <form:input path="email" placeholder="Email"/> 
				   <p id="errorEmail" class="inputError">
					  <form:errors path="email" htmlEscape="false" />
				   </p>
				    <form:errors path="*">
					<div class="inputError">
						<spring:message code="error.captcha" />
					</div>
					</form:errors>
				   <div>
					Enter the code <input type="text" name="jcaptchaResponse" value="" />
					<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
					<button name="RefreshButton" onclick="refresh()" type="button">Refresh</button>
				   </div>
				   <p><input type="submit" value="Ok"/></p>
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