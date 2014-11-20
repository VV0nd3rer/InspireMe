<html>
<head>
<%@ include file="include.jspf"%>
<title>Recovering password</title>
</head>
<body>
	<div id="wrapper">
		<%@ include file="head.jspf"%>
		<div id="content">
			<form:form action="recoverPassword" modelAttribute="parameters">
				<p><spring:message code="label.email"/></p>
				<p>
					<form:input path="email" />
					<em id="errorEmail" class="inputError"> 
					<form:errors path="email" htmlEscape="false" />
					</em>
				</p>
				<%--<form:errors path="*">
					<div class="inputError">
						<spring:message code="error.captcha" />
					</div>
				</form:errors>--%>
				<p>
					<form:input path="captcha"/>
					<em id="errorCaptcha" class="inputError">
					 <form:errors path="captcha" htmlEscape="false" />
					</em>
					<img src="/InspireMe/jcaptcha.jpg" id="captchaImg">
					<button name="RefreshButton" onclick="refreshCaptcha()" type="button"><spring:message code="button.refresh"/></button>
			    </p>
			     <spring:message code="button.ok" var="btnLabel"/>
				<p>
					<input type="submit" value="${btnLabel}" />
				</p>
			</form:form>
		</div>
		<%@ include file="footer.jspf"%>
	</div>
	<script type="text/javascript">
		function refreshCaptcha() {
			var image = document.getElementById("captchaImg");
		    image.src = "<%=request.getContextPath()%>/jcaptcha.jpg?"+Math.floor(Math.random()*100)           
		}
	</script>
</body>
</html>