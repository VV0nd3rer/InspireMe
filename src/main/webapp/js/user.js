$( document ).ready(function() {
	tinyMCEInit();
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
	function tinyMCEInit() {
		tinymce.init({
		    selector: "textarea.edit",
		    theme: "modern",
		    plugins: [
		        "advlist autolink lists link image charmap print preview hr anchor pagebreak",
		        "searchreplace wordcount visualblocks visualchars code fullscreen",
		        "insertdatetime media nonbreaking save table contextmenu directionality",
		        "emoticons template paste textcolor colorpicker textpattern"
		    ],
		    toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
		    toolbar2: "print preview media | forecolor backcolor emoticons",
		    image_advtab: true,
		    templates: [
		        {title: 'Test template 1', content: 'Test 1'},
		        {title: 'Test template 2', content: 'Test 2'}
		    ]
		});
	}
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
	