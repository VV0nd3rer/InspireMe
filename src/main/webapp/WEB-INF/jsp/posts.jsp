<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Home</title>
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">
			<c:forEach items="${posts}" var="post">
				<h4>${post.title}</h4>
				<p>${post.text}</p>
			</c:forEach>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>