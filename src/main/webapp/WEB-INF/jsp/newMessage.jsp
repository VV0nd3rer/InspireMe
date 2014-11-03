<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="include.jspf" %>
	<script src="<%=request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/editer.js"></script>

	 <script src="<%=request.getContextPath() %>/js/jquery-ui.js"></script>
	 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery-ui.css">
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
			                return{
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
			<div id="content">
			
				<%@ include file="userCabinetMenu.jspf" %>
				
				<div id="infoContent">
				<%@ include file="messagesMenu.jspf" %><br><br>
				To:<input id="toLogin" type="text" oninput="userSearch()"></input>
				   	<form:form id="newMessageForm" action="sendMessage" modelAttribute="message" method="POST">
			     	<form:hidden path="from_id" value="${message.from_id}"/>
			     	<form:hidden id="toId" path="to_id" />
							<p>
						Subject:<form:input path="subject"/>
						<p>Text:</p>
						<form:textarea class="edit" path="text" style="width:100%; height:800" htmlEscape="false"/>
						<p><form:button>Send</form:button></p>
					</form:form>
				</div>
			</div>
			<%@ include file="footer.jspf" %>
		</div>	
	</body>
</html>