<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<c:url value="/resources/js/editer.js"/>"></script>
	<script src="<c:url value="/resources/js/tinymce/tinymce.min.js"/>"></script>
	<script src="<c:url value="/resources/js/dialog-polyfill.js"/>"></script>
	<script src="<c:url value="/resources/js/dialogs.js"/>"></script>
	<title>Posts</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dialog-polyfill.css"/>">
	</head>
	<body id="sight_bg" style="background: url(<c:url value="/resources/countryImg/${country.imgPath}"/>) no-repeat top center fixed">
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
			  <h3 class="postTitle">${singlePost.title}</h3>
			  <p>${singlePost.stampCreated}</p>
			  <div class="text">${singlePost.text}</div> 
		  </div>
				<%@ include file="menuPost.jspf" %>
				 <p>
					<a class="icon" href="<%=request.getContextPath() %>/posts/newPost?sightId=${singlePost.sightId}">
						<img src="<c:url value="/resources/css/icons/add.png"/>"/>
					</a>
					<a class="icon" href="<%=request.getContextPath() %>/posts/sightPosts?sightId=${singlePost.sightId}">
						<img src="<c:url value="/resources/css/icons/back.png"/>"/>
		  			</a>
				</p>
		  <%@ include file="footer.jspf" %>
		  </div>
		</div>
	</body>
	
</html>