<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jspf" %>
<title>Adding a new friend</title>
</head>
<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="leftBar">
				<%@ include file="userCabinetMenu.jspf" %>
				</div>
			<div id="content">
				
				<c:forEach items="${users}" var="u">
           	      <div class="user">
	     	    <h3>${u.username}</h3>
	     	    <a href="addFriend?id=${u.userId}">Add to friends</a>	     	   	
	     		
     	    </div>
    	 	</c:forEach>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>