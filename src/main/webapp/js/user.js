$(function() {
	$("body").on("focus", "#login", function() {
		if(!$.isEmptyObject($("#loginError"))) 
			$("#loginError").empty();
	});
	$("body").on("focus", "#email", function() {
		if(!$.isEmptyObject($("#emailError"))) 
			$("#emailError").empty();
	});
	$("body").on("blur", "#login", function() {
		nameValidation();
	});
	$("body").on("blur", "#email", function() {
		emailValidation();
	})
	$("#register").click(function(event) {
		event.preventDefault();
		addUser();
	});
});
function addUser() {
	var formData = new FormData(document.getElementById("user"));
	$.ajax({
		url: contexPath + "/main/addUser", 
		data: formData,
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(response){
			if(response.status == "SUCCESS"){
				window.location.href = contexPath + "/main/" + response.result;
			}else{
				showErrors(response.result);
				/*$.each(response.result, function(field, msg) {
					var errorField = $('#' + field + "Error");
					errorField.html(msg);
				});*/
				refreshCaptcha();
			}	      
		},  
		error: function(e){  
			alert('Error: ' + e);  
		}  
});
}
function refreshCaptcha() {
	var image = document.getElementById("captchaImg");
    image.src = contexPath+"/jcaptcha.jpg?"+Math.floor(Math.random()*100)           
}
	function nameValidation() {
		var login = $("#login").val();
		$.ajax({
			type: "GET",   
		    url: contexPath + "/main/checkName",  
		    data: "login=" + login,
		    success: function(response){
		    	if(response.status == "ERROR") {
		    		showErrors(response.result);
		    		/*$.each(response.result, function(field, msg) {
						var errorField = $('#' + field + "Error");
						errorField.html(msg);
					});*/
		    	}
		    },
			error: function(error) {
				alert("In valid name error: " + error);
			}
		});
	}
	function emailValidation() {
		var email = $("#email").val();
		$.ajax({
			type: "GET",
			url: contexPath + "/main/checkEmail",
			data: "email=" + email,
			success: function(response){
				if(response.status == "ERROR") {
					showErrors(response.result);
					/*$.each(response.result, function(field, msg) {
						var errorField = $('#' + field + "Error");
						errorField.html(msg);
					});*/
				}		
			},
			error: function(error) {
				alert("Erro in process of check email.")
			}
		})
		
	}
	//Get map of errors from server and show them in correspond error fields
	function showErrors(errors) {
		$.each(errors, function(field, msg) {
			var errorField = $('#' + field + "Error");
			errorField.html(msg);
		});
	}
	//
	function userSearch() {
		var login = $("#searchUserField").val();
		$.ajax({
			type: "POST",  
		    url: contexPath + "/main/peopleSearch",  
		    data: "fragment=" + login,
		    success: function(response){
		    	$("div.users").empty();
		    	for(var r in response){
		    		$("div.users").append("<div class='user'>"+response[r].first.username+"</div>");
		    	}
		    			    	
		    },
			error: function(error) {
				alert("In valid name error: " + error);
			}
		});
	}
	