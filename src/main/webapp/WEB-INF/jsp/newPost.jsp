<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/editer.js"></script>
	<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<title>Posts</title>
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">		
				<form:form action="${url}" modelAttribute="newPost">
				  <form:errors path="*">
						<div class="inputError"><spring:message code="error.post" /></div>
				  </form:errors>
					<p>Title</p>
					<form:input path="title" />
					<p>Description</p>
					<form:textarea path="description"/> 
					<p>Text</p>
					<form:textarea class="edit" path="text" style="width:100%" htmlEscape="false"/>  
					<p><input type="submit" value="Ok"></p>
				</form:form>
				<a href="<%=request.getContextPath() %>/main/posts/sightPosts?sightId=${newPost.sightId}">
					<img src="<%=request.getContextPath() %>/css/icons/back.png"/>
				</a>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>