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
			<h2>Reset password</h2>
			<form:form action="${url}" modelAttribute="user">
				<form:errors path="*">
					<div>
						<spring:message code="error.global" />
					</div>
				</form:errors>
				<div>
					<form:input path="email" placeholder="Email" />
					<p id="errorEmail" class="inputError">
						<form:errors path="email" htmlEscape="false" />
					</p>
				</div>
				<div>
					<form:password path="password" placeholder="Password" />
					<p id="errorPass" class="inputError">
						<form:errors path="password" htmlEscape="false" />
					</p>
				</div>
				<div>
					<form:password path="confirmPassword"
						placeholder="Confirm password" />
				</div>
				<p>
					<input type="submit" value="Save"></input>
				</p>
			</form:form>
		</div>
		<%@ include file="footer.jspf"%>
	</div>
</body>
</html>