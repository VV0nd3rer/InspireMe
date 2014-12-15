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
			<div id="content" class="common_link">	
				<%@ include file="userCabinetMenu.jspf" %>
				<div id="infoContent">
				<a href="addFriendPage">Add a new friend!</a>
					<c:forEach items="${friendList}" var="f">
		           	   <div class="user">
				     	    <h3>${f.username}</h3>
				     	    <a href="removeFriend?id=${f.userId}">Remove</a>	 					
						</div>
    	 			</c:forEach>
    	 	    </div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>