<!doctype html>
<html>

<head>
	<%@ include file="include.jspf" %>
	<script src="<c:url value="/resources/js/tinymce/tinymce.min.js"/>"></script>
	<script src="<c:url value="/resources/js/messages.js"/>"></script>

<title>Messages</title>
</head>
<body>
	<div id="wrapper">
	   <%@ include file="head.jspf" %>
	   <div id="content" class="common_link">
		  <%@ include file="userCabinetMenu.jspf" %>
		  <div id="infoContent">
				<%@ include file="messagesMenu.jspf" %>
				<p>Subject: ${message.subject}</p>
				<c:set var="test" scope="session" value="${messageToSend.removed_by}"/>
				<c:if test="${test==0}"> 
		     	   		From: 
		     	   		</c:if>
		     	   		<c:if test="${test!=0}"> 
						To: 
		     	   		</c:if>
				${sender}
				<p>Date: ${message.date}</p>
					<p><a href="deleteMessage?id=${message.message_id}">Delete</a>
					<c:if test="${test==0}"> 
			     	   		<a href="#" id="reply">Reply</a>
			     	   		</c:if>
			     	</p>
				<p align="center">Message</p>
	            <div id="messageBody">
	            ${message.text}
	            </div>
	            <c:if test="${test==0}"> 
			     	<form:form id="replyForm" action="sendMessage" modelAttribute="messageToSend" method="POST">
			            <form:textarea id="replyText" class="edit" path="text" style="width:100%; height:800" htmlEscape="false"/>
						<form:hidden path="from_id" value="${message.to_id}"/>
						<form:hidden path="to_id" value="${message.from_id}"/>
						<form:hidden path="subject" value="RE:${message.subject}"/>
						<p><form:button>Send</form:button><a href="#" id="cancel">Cancel</a></p>
					</form:form>
			    </c:if>
		  </div>
	   </div>
	   <%@ include file="footer.jspf" %>
	</div>
</body>
</html>