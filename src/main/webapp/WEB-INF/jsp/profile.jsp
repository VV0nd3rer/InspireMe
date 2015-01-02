<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="include.jspf" %>
<script src="<c:url value="/resources/js/jquery-ui/jquery-ui.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui/jquery-ui.css"/>">
<title>My profile</title>
<script>
$(function() {
	$( document ).tooltip({
		items: "[title]", 
		content: function() {
			return "<spring:message code='profile.edit.hint'/>";
		}
	});
});
</script>
</head>
<body>
	<div id="wrapper">
		<%@ include file="head.jspf"%>
		<div id="content" class="common_link">
			<%@ include file="userCabinetMenu.jspf"%>
			<div id="infoContent">
			    <div class="right_block">
					<img src="<c:url value="/resources/usersAvatars/${curUserData.avatarUrl}"/>">
					<a title="" href="editProfilePage">Edit profile</a>
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