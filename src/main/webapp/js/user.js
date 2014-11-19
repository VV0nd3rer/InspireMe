$( document ).ready(function() {
	userSearch();
	$("#password").passStrengthify();

	//$("#confirmPassword").passStrengthify();
	$("body").on("focus", "input", function() {
		fIn($(this).attr('id'), $(this).val());
	});
	$("body").on("blur", "input", function() {
		if($(this).attr('id') == 'login')
			{
			//alert($(this).attr('id').val());
			validName();
			}
			
		fOut($(this).attr('id'), $(this).val());
	});
});
	function validName() {
		var login = $("#login").val();
		$.ajax({
			type: "GET",  
		    url: contexPath + "/main/validName",  
		    data: "login=" + login,
		    success: function(response){
		    	if(response == "FALSE")
		    		$("#errorLogin").append("<p>This name is busy. </p>");
		    },
			error: function(error) {
				alert("In valid name error: " + error);
			}
		});
	}
	
	function userSearch() {
		var login = $("#searchUserField").val();
		$.ajax({
			type: "POST",  
		    url: contexPath + "/main/peopleSearch",  
		    data: "fragment=" + login,
		    success: function(response){
		    	$("#users").empty();
		    	for(var r in response){
		    		var linkText="";
		    		if(response[r].second==0){
		    			linkText = "<p>Already in your friends/requests</p>";
		    		}else linkText = '<a href="addFriend?id='+response[r].first.userId+'">Add to friends</a>';
		    		
		    		$("#users").append("<div class='user'>"+"<h3>"+response[r].first.username+"</h3>"
		    				+ linkText
		    				+"</div>");
		    	}
		    	
		    },
			error: function(error) {
				alert("In valid name error: " + error);
			}
		});
	}
	
		function fIn(field) {
		var errField ;
		switch(field) {
		case 'login':
			errField = $("#errorLogin");
			break;
		case 'password':
			errField = $("#errorPass");
			break;
		case 'email':
			errField = $("#errorEmail");
			break;
		}
		if(!$.isEmptyObject(errField)) 
			errField.empty();
	}
	function fOut(field, text) {
		var max;
		var min;
		var chars = text.length; 
		var errField;
		switch (field) {
		case 'login':
			minMax(5, 50, chars, $("#errorLogin"));
			break;
		case 'password':
			minMax(6, 64, chars, $("#errorPass"));
			break;
		case 'email':
			validEmail(text, $("#errorEmail"));
			break;
		}
	}
	function minMax(_min, _max, _length, _errField) {
		if(_length > _max || _length < _min) {
			_errField.append("<p>Size of login must be between " + _min + " and " + _max + "</p>");
		}
	}
	function validEmail(_text, _errField) {
		var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!regex.test(_text))
			_errField.append("<p>Incorrect email.</p>");
	}
	