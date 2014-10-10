<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jspf" %>
<title>Edit profile</title>
</head>
<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
			
				<%@ include file="userCabinetMenu.jspf" %>
				
				<div id="infoContent">
				<h3>Edition of profile.</h3>
				<img src="<%=request.getContextPath()%>/usersAvatars/${userData.avatarUrl}"><br/><br/>
				
				<form:form action="editData" modelAttribute="userData" method="POST">
				<form:hidden path="userId" value="${userData.userId}" />
				<form:hidden path="avatarUrl" value="mPylypets.jpg" />
					First Name: <form:input path="firstName" value="${userData.firstName}" /><p>
					Last name: <form:input path="lastName" value="${userData.lastName}"/><p>
					E-mail: <form:input path="e_mail" value="${userData.e_mail}"/><p>
					Country: <form:select path="country" value="${userData.country}"> 
					<c:set var="code" scope="session" value="${userData.country}"/>
							<c:forEach items="${countryList}" var="s">
								<c:set var="res" scope="session" value="${s.getCountryCode()}" />
								<c:choose>
								<c:when test="${code==res}">
									<form:option selected="true" value="${s.getCountryCode()}">${s.getCountryName()}</form:option>
								</c:when>
								<c:otherwise>
									<form:option value="${s.getCountryCode()}">${s.getCountryName()}</form:option>
								</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select><p>
					About: <form:textarea path="about"/>					
					<p><input type="submit" value="Save"></input></p>
				</form:form>
											
				</div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>