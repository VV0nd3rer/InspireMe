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
				<div class="postPreview">
				  <div>
					<b><a href="<%=request.getContextPath() %>/main/posts/singlePost?postId=${post.postId}">
						 ${post.title}.
					   </a>
					</b> 
					<em>
					${post.description}
					</em> 
				  </div>
					<a href="<%=request.getContextPath() %>/main/posts/updatePost?postId=${post.postId}">
						<img src="<%=request.getContextPath() %>/css/icons/edit.png"/>
					</a> 
					<a href="<%=request.getContextPath() %>/main/posts/deletePost?postId=${post.postId}">
						<img src="<%=request.getContextPath() %>/css/icons/delete.png"/>
					</a>
				</div>
				<hr>
			</c:forEach>
			<p>
				<a href="<%=request.getContextPath() %>/main/posts/newPost">
					<img src="<%=request.getContextPath() %>/css/icons/add.png"/>
				</a>
			</p>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>