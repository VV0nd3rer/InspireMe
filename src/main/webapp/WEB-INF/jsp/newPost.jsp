<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/editer.js"></script>
	<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<title>Posts</title>
	</head>
	<body id="sight_bg" style="background: url(<%=request.getContextPath() %>/countryImg/${country.imgPath}) no-repeat top center fixed">
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="sightContent">		
				<form:form action="${url}" modelAttribute="newPost">
				  <form:errors path="*">
						<div class="inputError"><spring:message code="error.post" /></div>
				  </form:errors>
					<p>Title <em class="inputError">*</em></p>
					<form:input path="title" size="50"/>
					<p>Description <em class="inputError">*</em></p>
					<form:textarea path="description" rows="4" cols="50"/> 
					<p>Text <em class="inputError">*</em></p>
					<form:textarea class="edit" path="text" style="height:800px" htmlEscape="false"/>  
					<p><input type="submit" value="Ok"></p>
				</form:form>
				<a class="imgLinks" href="<%=request.getContextPath() %>/main/posts/sightPosts?sightId=${newPost.sightId}">
					<img src="<%=request.getContextPath() %>/css/icons/back.png"/>
				</a>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>