$( document ).ready(function() {
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
	
		function fIn(field) {
		var errField ;
		switch(field) {
		case 'login':
			errField = $("#errorLogin");
			break;
		case 'password':
			errField = $("#errorPass");
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
			max = 10;
			min = 5;
			errField = $("#errorLogin");
			break;
		case 'password':
			max = 15;
			min = 8;
			errField = $("#errorPass");
			break;
		}
		minMax(min, max, chars, errField);
	}
	function minMax(_min, _max, _length, _errField) {
		if(_length > _max || _length < _min) {
			_errField.append("<p>Size of login must be between " + _min + " and " + _max + "</p>");
		}
	}
	