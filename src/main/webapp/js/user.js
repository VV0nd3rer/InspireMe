$( document ).ready(function() {
 // alert("ready");
 /* $("#login").focusout(function() {
	  $("#info").append( "<p>Test</p>" );
	 //alert("focus out on login"); 
  });*/
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
	/*$('#default').addClass('active');
	  
	  $("li a").click(function(event){
		  event.preventDefault();
		  var url = $(this).attr('href');
     	  document.title = $(this).text();
     	   //alert(title);
     	   $("#content").load(url + " #content > *", function() {
     		   
     	   });
     	  $('#menu li').removeClass();
          $(this).parent().addClass('active');   
	  });*/
	  
	/*$("#login").focusout(function(){  
		var max = 6;
		var min = 4;
		var text = $(this).val();
		var chars = text.length;
		if(chars > max || chars < min) {
			//$("#info").empty();
			$("#info").append("<p>Size of login must be between 4 and 6 ");
		}
	});
	$("#login").focusin(function() {
		//alert("focusin");
		$("#info").empty();
	});*/
	
});
	/*function login() {

		 $.ajax({
			    url: contexPath + "/j_spring_security_check?ajax=true",
			    data: { j_username: j_username , j_password: j_username },
			    type: "POST",
			    success: function(data, textStatus, jqXHR) {
                    alert('Success!!!');
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('Fail!');
                }
			  });
			alert('login');
	}*/
	function validName() {
		var login = $("#login").val();
		$.ajax({
			type: "GET",  
		    url: contexPath + "/main/validName",  
		    data: "login=" + login,
		    success: function(response){
		    	if(response == "FALSE")
		    		$("#errorLogin").append("<p>This name is beasy. </p>");
		    },
			error: function(error) {
				alert("In valid name error: " + error);
			}
		});
	}
	function fIn(field) {
		var errField;
		switch(field) {
		case 'login':
			errField = $("#errorLogin");
			break;
		case 'password':
			errField = $("#errorPass");
			break;
		}
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
	