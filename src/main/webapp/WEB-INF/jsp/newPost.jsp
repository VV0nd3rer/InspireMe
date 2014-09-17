<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
	<%@ include file="include.jspf" %>
	
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
					<form:textarea path="description" rows="5" cols="30"/> 
					<p>Text</p>
					<form:textarea class="edit" path="text" style="width:100%" htmlEscape="false"/>  
					<p><input type="submit" value="Ok"></p>
				</form:form>
		  </div>
		  <%@ include file="footer.jspf" %>
		</div>
	</body>
</html>