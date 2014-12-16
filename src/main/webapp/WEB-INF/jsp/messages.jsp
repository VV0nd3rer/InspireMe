<!doctype html>
<html>
<head>
<%@ include file="include.jspf" %>
<title>Messages</title>
</head>
<body>
  <div id="wrapper">
   <%@ include file="head.jspf" %>
	<div id="content" class="common_link">
	<%@ include file="userCabinetMenu.jspf" %>
	  <div id="infoContent">
	   <%@ include file="messagesMenu.jspf" %>
	   <table id="msg_table">
		 <tr>
		   <td>Subject</td>
		   <c:set var="referer" scope="session" value="${referer}"/>
		   <c:set var="comp" scope="session" value="messages"/>
		   <c:if test="${referer==comp}"> 
		      <td>From</td>
		   </c:if>
		   <c:if test="${referer!=comp}"> 
			  <td>To</td>
		   </c:if> 		
		   <td>Date</td>
		   <td>Delete</td>
		 </tr>
		 <c:forEach items="${messageHeaderList}" var="mhl">
			<c:set var="status" scope="session" value="${mhl.status}"/>
		    <c:if test="${status==0}"> 
		       <tr class="unread">
		    </c:if>
		    <c:if test="${status==1}"> 
		       <tr class="messageHeader">
		    </c:if>
	           <td><a href="singleMessage?id=${mhl.messageId}&sender=${mhl.from}">${mhl.subject}</a></td>
		       <td>${mhl.from}</td>
		       <td>${mhl.date}</td>
		     
		       <td><a href="deleteMessage?id=${mhl.messageId}">Delete</a></td>
     	       </tr>
    	 </c:forEach> 
    	</table>
	   </div>
      </div>
     <%@ include file="footer.jspf" %>
    </div>
</body>
</html>