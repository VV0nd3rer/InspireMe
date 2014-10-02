<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jspf" %>
<title>My friends</title>
</head>
<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<div id="cabinetMenu">
				<%@ include file="userCabinetMenu.jspf" %>
				</div>
				<a href="addFriendPage">Add a new friend!</a>
					<c:forEach items="${requestsList}" var="r">
		           	   <div class="user">
				     	    <h3>${r.username}</h3>
				     	    <a href="acceptFriend?id=${r.userId}">Accept</a>	 
							<a href="removeFriend?id=${r.userId}">Decline</a>
						</div>
    	 			</c:forEach>
    	 	
				</div>
			
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>