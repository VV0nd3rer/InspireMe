<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jspf" %>
<title>My profile</title>
</head>
<body>
	<div id="wrapper">
		<%@ include file="head.jspf"%>
		<div id="content" class="common_link">
			<%@ include file="userCabinetMenu.jspf"%>
			<div id="infoContent">
			    <div class="right_block">
					<img src="<%=request.getContextPath()%>/usersAvatars/${curUserData.avatarUrl}">
					<a href="editProfilePage">Edit profile</a>
				</div>
				<div id="form-style">
					<p>${curUserData.firstName} ${curUserData.lastName}</p>
					<%-- <p>e-mail: ${curUserData.e_mail}</p> --%>
					<p>Country:<br> ${curUserData.country}</p>
					<p class="text_block">About me:<br>
					${curUserData.about}</p>	
				</div>
			</div>
		</div>
		<%@ include file="footer.jspf"%>
	</div>
</body>
</html>