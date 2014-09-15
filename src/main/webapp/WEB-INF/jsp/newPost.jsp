<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
	<%@ include file="include.jspf" %>
	
	<title>Posts</title>
	</head>
	<body>
		<div id="wrapper">
		  <%@ include file="head.jspf" %>
		  <div id="content">		
				<form:form action="createPost" modelAttribute="newPost">
				  <form:errors path="*">
						<div><spring:message code="error.global" /></div>
				  </form:errors>
					<p>Title</p>
					<form:input path="title" />
					<p>Description</p>
					<form:textarea path="description" rows="5" cols="30"/> 
					<p>Text</p>
					<form:textarea path="text" rows="5" cols="30" />  
					<p><input type="submit" value="Ok"></p>
				</form:form>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>