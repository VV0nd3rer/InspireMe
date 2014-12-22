<!doctype html>
<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/dialog-polyfill.js"></script>
	<script src="<%=request.getContextPath() %>/js/dialogs.js"></script>
	<title>Posts</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dialog-polyfill.css">
	</head>
	<body id="sight_bg" style="background: url(<%=request.getContextPath() %>/countryImg/${country.imgPath}) no-repeat top center fixed">
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="sightContent">
		  <dialog id="removePost">
			<p>Are you sure to <em class="inputError">delete post</em>?</p>
			<div>
				<input type="button"  id="closeAndProcess" value="Yes"  onclick="closeAndProcess()">
			    <input type="button" id="close" value="No" autofocus="autofocus" onclick="closeDialog()">
			</div>
 		  </dialog>
			<c:forEach items="${posts}" var="singlePost">
				<div class="postPreview">
				  <div>
					<a href="<%=request.getContextPath() %>/main/posts/singlePost?postId=${singlePost.postId}">
						 ${singlePost.title}.
					</a>
					<em>
						${singlePost.description}
					</em> 
				  </div>
					<%@ include file="menuPost.jspf" %>	
				</div>
				<hr>
			</c:forEach>
			<p>
					<a class="icon" href="<%=request.getContextPath() %>/main/posts/newPost?sightId=${sightId}">
						<img src="<%=request.getContextPath() %>/css/icons/add.png"/>
					</a>
					<a class="icon" href="<%=request.getContextPath() %>/main/sights/country?country_code=${country_code}">
						<img src="<%=request.getContextPath() %>/css/icons/back.png"/>
		  			</a>
			</p>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>