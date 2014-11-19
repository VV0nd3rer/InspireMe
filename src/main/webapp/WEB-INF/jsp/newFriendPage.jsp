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
				 <div id="users">
				
				 </div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>