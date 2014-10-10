<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jspf" %>
<title>My profile</title>
</head>
<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
			
				<%@ include file="userCabinetMenu.jspf" %>
				
				<div id="infoContent">
				<img src="<%=request.getContextPath()%>/usersAvatars/${curUserData.avatarUrl}">
				
					<p>${curUserData.firstName} ${curUserData.lastName}</p>
					<p>e-mail: ${curUserData.e_mail}</p>
					<p>Country: ${curUserData.country}</p>
						<div id="about">
						<p>About me:</p>
						${curUserData.about}
						</div>
						<a href="editProfilePage">Edit profile</a>
				</div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>