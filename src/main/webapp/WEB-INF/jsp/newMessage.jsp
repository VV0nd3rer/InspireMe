<!doctype html>
<html>
<head>
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/editer.js"></script>

	 <script src="<%=request.getContextPath() %>/js/jquery-ui/jquery-ui.js"></script>
	 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery-ui/jquery-ui.css">
	 <title>New message</title>
     <script>	
	 function userSearch() {
		var login = $("#toLogin").val();	
		$( "#toLogin" ).autocomplete({
		  source:function( request, response ) { 
		     $.ajax({
			    type: "POST",  
				url: contexPath + "/main/peopleSearch",  
				data: "fragment=" + login,
				success: function(data){
					response( $.map( data, function( item ) {
			        return {
			                label: item.first.username,
			                value: item.first.userId
			               }
			            }));
				},
			    error: function(error) {
					alert("Invalid name error: " + error);
					}
			 });
		  },
		  select: function( event, ui ) {
		      $( "#toLogin" ).val( ui.item.label );
		      $("#toId").val(ui.item.value);
		       return false;
		    }
		  });
	}
	</script>
</head>
<body>
	<div id="wrapper">
    <%@ include file="head.jspf" %>
	 <div id="content" class="common_link">
	  <%@ include file="userCabinetMenu.jspf" %>
		<div id="infoContent">
		 <%@ include file="messagesMenu.jspf" %><br>
		 <div class="left_block">
			 <label><spring:message code="label.msg_to"/></label>
			 <input id="toLogin" type="text" oninput="userSearch()"></input>
			 <form:form id="newMessageForm" action="sendMessage" modelAttribute="messageToSend" method="POST">
				 <form:hidden path="from_id" value="${messageToSend.from_id}"/>
				 <form:hidden id="toId" path="to_id" />
				 <form:label path="subject"><spring:message code="label.msg_subject"/></form:label>
				 <form:input path="subject"/>
				 <form:label path="text"><spring:message code="label.msg_text"/></form:label>
				 <form:textarea class="edit" path="text" style="width:100%; height:800" htmlEscape="false"/>
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