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
			<div id="content" class="common_link">
			
				<%@ include file="userCabinetMenu.jspf" %>
				
				<div id="infoContent">
				<h4>Editing of profile.</h4>
				<c:set var="imgName" scope="session" value="${userData.avatarUrl}"/>
				<c:set var="noAvatar" scope="session" value="noavatar.jpg"/>
				<form:form action="editData" modelAttribute="userData" method="POST" enctype="multipart/form-data">
				<div class="right_block">
					<c:if test="${imgName==noAvatar}">
					<img src="<%=request.getContextPath()%>/usersAvatars/noavatar.jpg"><br/><br/>
					</c:if>
					<c:if test="${imgName!=noAvatar}">
					<img src="<%=request.getContextPath()%>/usersAvatars/${userData.avatarUrl}"><br/><br/>
					</c:if>
					Choose new avatar: <input type="file" name="usrAvatar"><p>
				</div>
				<div class="left_block">
					
						<form:hidden path="userId" value="${userData.userId}" />
						<form:hidden path="avatarUrl" value="${userData.avatarUrl}" />
					    
						<form:label path="firstName"><spring:message code="label.firstname"/></form:label> 
						<form:input path="firstName" value="${userData.firstName}" />
						<form:label path="lastName"><spring:message code="label.lastname"/></form:label>
						<form:input path="lastName" value="${userData.lastName}"/>
						<%-- E-mail: <form:input path="e_mail" value="${userData.e_mail}"/><p> --%>
						<form:label path="country"><spring:message code="label.country"/></form:label>
						<form:select path="country" value="${userData.country}"> 
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
						</form:select>
						<form:label path="about"><spring:message code="label.about"/></form:label>
						<form:textarea path="about"/>			
						<spring:message code="button.ok" var="btnLabel"/>		
						<p><input type="submit" value="${btnLabel}"/></p>
					</form:form>
				</div>							
				</div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>
	</body>
</html>