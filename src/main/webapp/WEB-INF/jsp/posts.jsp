<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Posts</title>
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">
			<c:forEach items="${posts}" var="singlePost">
				<div class="postPreview">
					<b><a href="<%=request.getContextPath() %>/main/posts/singlePost?postId=${singlePost.postId}">
						 ${singlePost.title}.
					   </a>
					</b> 
					<em>
						${singlePost.description}
					</em> 	
					<%@ include file="menuPost.jspf" %>
				</div>
				<hr>
			</c:forEach>
			<p>
					<a href="<%=request.getContextPath() %>/main/posts/newPost?sightId=${sightId}">
						<img src="<%=request.getContextPath() %>/css/icons/add.png"/>
					</a>
					<a href="<%=request.getContextPath() %>/main/country?country_code=${country_code}">
						<img src="<%=request.getContextPath() %>/css/icons/back.png"/>
		  			</a>
			</p>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>