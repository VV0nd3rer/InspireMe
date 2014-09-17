<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Posts</title>
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">		
				<div id="singlePost">
					<h3>${singlePost.title}</h3>
					<div id="text">${singlePost.text}</div> 
				</div>
				<a href="<%=request.getContextPath() %>/main/posts/updatePost?postId=${post.postId}">
					<img src="<%=request.getContextPath() %>/css/icons/edit.png"/>
				</a> 
				<a href="<%=request.getContextPath() %>/main/posts/deletePost?postId=${post.postId}">
					<img src="<%=request.getContextPath() %>/css/icons/delete.png"/>
				</a>
				<p>
					<a href="<%=request.getContextPath() %>/main/posts/newPost">
						<img src="<%=request.getContextPath() %>/css/icons/add.png"/>
					</a>
					<a href="<%=request.getContextPath() %>/main/posts/sightPosts?sightId=${sightId}">
						<img src="<%=request.getContextPath() %>/css/icons/back.png"/>
					</a>
				</p>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>