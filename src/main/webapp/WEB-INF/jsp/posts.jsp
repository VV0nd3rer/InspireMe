<!doctype html>
<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<c:url value="/resources/js/dialog-polyfill.js"/>"></script>
	<script src="<c:url value="/resources/js/dialogs.js"/>"></script>
	<title>Posts</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dialog-polyfill.css"/>">
	</head>
	<body id="sight_bg" style="background: url(<c:url value="/resources/countryImg/${country.imgPath}"/>) no-repeat top center fixed">
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="sightContent">
		  ${msg_code}
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
					<a href="<%=request.getContextPath() %>/posts/singlePost?postId=${singlePost.postId}">
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
					<a class="icon" href="<%=request.getContextPath() %>/posts/newPost?sightId=${sightId}">
						<img src="<c:url value="/resources/css/icons/add.png"/>"/>
					</a>
					<a class="icon" href="<%=request.getContextPath() %>/sights/country?country_code=${country_code}">
						<img src="<c:url value="/resources/css/icons/back.png"/>"/>
		  			</a>
			</p>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>