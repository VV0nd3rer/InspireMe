<html>
<head>
<%@ include file="include.jspf"%>
<script src="<%=request.getContextPath()%>/js/user.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.passstrength.js"></script>
<title>SignUp</title>
</head>
<body>
	<div id="wrapper">
		<%@ include file="head.jspf"%>
		<div id="content">
			<h4><spring:message code="text.resetPassword"/></h4>
			<form:form action="${url}" modelAttribute="user">
				<form:errors path="*">
					<div>
						<spring:message code="error.global" />
					</div>
				</form:errors>
				<p><spring:message code="label.email"/></p>
				<p> 
					<form:input path="email"/>
					<em id="errorEmail" class="inputError">
						<form:errors path="email" htmlEscape="false" />
					</em>
				</p>
				<p><spring:message code="label.password"/></p>
				<p>
					<form:password path="password"/>
					<em id="errorPass" class="inputError">
						<form:errors path="password" htmlEscape="false" />
					</em>
				</p>
				<p><spring:message code="label.confirmPassword"/></p>
				<p>
					<form:password path="confirmPassword"/>
				</p>
				<spring:message code="button.ok" var="btnLabel"/>
				<p>
					<input type="submit" value="${btnLabel}"></input>
				</p>
			</form:form>
		</div>
		<%@ include file="footer.jspf"%>
	</div>
</body>
</html>