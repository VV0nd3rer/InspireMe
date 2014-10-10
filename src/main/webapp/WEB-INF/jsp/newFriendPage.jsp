<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jspf" %>
<script src="<%=request.getContextPath() %>/js/user.js"></script>
<title>Adding a new friend</title>
</head>
<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
		<%@ include file="userCabinetMenu.jspf" %>
			<div id="content">
			
				<input type="text" name="searchUserField" id="searchUserField" placeholder="Type for search" oninput="userSearch()">
				 <div class="users">
				<c:forEach items="${users}" var="u">
           	      <div class="user">
	     	    <h3>${u.first.username}</h3>
	     	   <c:set var="flag" scope="session" value="${u.second}"/>
	     	   <c:if test="${flag==1}"> 
	     	   	<p>Already in your friends/requests</p>
	     	   </c:if>
					<c:if test="${flag==0}"> 
	     	   	<a href="addFriend?id=${u.first.userId}">Add to friends</a>	
	     	   </c:if>
     	    	</div>
    	 	</c:forEach>
			</div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>