<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Posts</title>
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">
			<c:forEach items="${posts}" var="post">
				<p>
					<h4>${post.title}</h4>
					${post.text}
				</p>
				<hr>
			</c:forEach>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>