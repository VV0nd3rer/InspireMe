<html>
<head>
<%@ include file="include.jspf"%>
<title>Recovering password</title>
</head>
<body>
	<div id="wrapper">
		<%@ include file="head.jspf"%>
		<div id="content">
		  <div id="form-style">
			<form:form action="recoverPassword" modelAttribute="parameters">
				<label><span><spring:message code="label.email"/></span>
					<form:input path="email" />
					<em id="errorEmail" class="inputError"> 
					<form:errors path="email" htmlEscape="false" />
					</em>
				</label>
				<div class="captcha">
					<form:input path="captcha"/>
					<em id="errorCaptcha" class="inputError">
					 <form:errors path="captcha" htmlEscape="false" />
					</em>
					<img class="word" src="<c:url value="/resources/jcaptcha.jpg"/>" id="captchaImg">
					<a class="icon" onclick="refreshCaptcha()" >
					  <img src="<c:url value="/resources/css/icons/refresh.png"/>"/>
					</a>
			    </div>
			    <spring:message code="button.ok" var="btnLabel"/>
				<input type="submit" value="${btnLabel}" />
			</form:form>
		  </div>
		</div>
		<%@ include file="footer.jspf"%>
	</div>
	<script type="text/javascript">
		function refreshCaptcha() {
			var image = document.getElementById("captchaImg");
		    image.src = "<c:url value="/resources/jcaptcha.jpg?"/>"+Math.floor(Math.random()*100)           
		}
	</script>
</body>
</html>