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
					<b><a href="<%=request.getContextPath() %>/main/singlePost?postId=${post.postId}">
						 ${post.title}.
					   </a>
					</b> 
					     ${post.description} 
					     <i><a href="#">Edit</a> <a href="#">Delete</i></a>
				</p>
				<hr>
			</c:forEach>
			<a href="<%=request.getContextPath() %>/main/newPost">New post</a>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>