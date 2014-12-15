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
					<h3>My requests</h3>
						<c:forEach items="${userRequests}" var="u">
			           	   <div class="user">
					     	    <h3>${u.username}</h3>
					     	      <a href="removeFriend?id=${u.userId}">Delete</a>
							</div>
	    	 			</c:forEach>
	    	 		 <h3>Requests from other people</h3>	
	    	 			<c:forEach items="${peopleRequests}" var="p">
			           	   <div class="user">
					     	    <h3>${p.username}</h3>
					     	    <a href="acceptFriend?id=${p.userId}">Accept</a>	 
								<a href="removeFriend?id=${p.userId}">Decline</a>
						   </div>
	    	 			</c:forEach>
    	 		</div>
			 </div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>