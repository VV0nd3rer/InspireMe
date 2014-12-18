<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/editer.js"></script>
	<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/dialog-polyfill.js"></script>
	<script src="<%=request.getContextPath() %>/js/dialogs.js"></script>
	<title>Posts</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dialog-polyfill.css">
	</head>
	<body id="sight_bg" style="background: url(<%=request.getContextPath() %>/countryImg/${country.imgPath}) no-repeat top center fixed">
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">	
		  <dialog id="removePost">
			<p>Are you sure to <em class="inputError">delete post</em>?</p>
			<div>
				<input type="button"  id="closeAndProcess" value="Yes"  onclick="closeAndProcess()">
			    <input type="button" id="close" value="No" autofocus="autofocus" onclick="closeDialog()">
			</div>
 		  </dialog>	
		  <div id="singlePost">
			  <h3 class="title">${singlePost.title}</h3>
			  <p>${singlePost.stampCreated}</p>
			  <div class="text">${singlePost.text}</div> 
		  </div>
				<%@ include file="menuPost.jspf" %>
				 <p>
					<a class="imgLinks" href="<%=request.getContextPath() %>/main/posts/newPost?sightId=${singlePost.sightId}">
						<img src="<%=request.getContextPath() %>/css/icons/add.png"/>
					</a>
					<a class="imgLinks" href="<%=request.getContextPath() %>/main/posts/sightPosts?sightId=${singlePost.sightId}">
						<img src="<%=request.getContextPath() %>/css/icons/back.png"/>
		  			</a>
				</p>
		  <%@ include file="footer.jspf" %>
		  </div>
		</div>
	</body>
	
</html>