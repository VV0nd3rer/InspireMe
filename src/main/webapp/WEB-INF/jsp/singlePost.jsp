<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/editer.js"></script>
	<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<title>Posts</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dialog-polyfill.css">
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">		
				<div id="singlePost">
					<h3>${singlePost.title}</h3>
					<p>${singlePost.sightId}</p>
					<div id="text">${singlePost.text}</div> 
				</div>
				<%@ include file="menuPost.jspf" %>
				 <p>
					<a href="<%=request.getContextPath() %>/main/posts/newPost?sightId=${singlePost.sightId}">
						<img src="<%=request.getContextPath() %>/css/icons/add.png"/>
					</a>
					<a href="<%=request.getContextPath() %>/main/posts/sightPosts?sightId=${singlePost.sightId}">
						<img src="<%=request.getContextPath() %>/css/icons/back.png"/>
		  			</a>
				</p>
		  <%@ include file="footer.jspf" %>
		  </div>
		</div>
	</body>
	
</html>