<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Posts</title>
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">		
				<p>
					<h3>${singlePost.title}</h3>
					    ${singlePost.text} 
				</p>
				<i><a href="#">Edit</a> <a href="#">Delete</a></i>
				<a href="<%=request.getContextPath() %>/main/newPost">New post</a>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>