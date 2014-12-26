var deleteLink;
var dialog;
$(function() {
	//Modal dialogs
	
	$("#showNewSightDialog").click(function() {
		addNewSight();
	});
	$("#addSight").click(function(event) {

		event.preventDefault();
		addSight();
	});
   	/*document.querySelector('#showNewSightDialog').onclick = function() {
   		dialog = document.getElementById('newSightDialog');
          	dialogPolyfill.registerDialog(dialog);
          	dialog.showModal(); 
    }; */         
	$(".delPost").click(function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		confirmHref(href, 'removePost');
	})
});
function addNewSight() {
  	   dialog = document.getElementById("newSightDialog");
       dialogPolyfill.registerDialog(dialog);
       dialog.showModal(); 
}
function addSight() {
	var errorField = $(".inputError");
	var formData = new FormData(document.getElementById("sight"));
	formData.append("file", img_file.files[0]);
	 $.ajax({
		    url: contexPath + "/main/sights/addSight", 
		    data: formData,
		    //dataType: 'json',
		    processData: false,
		    contentType: false,
		    type: 'POST',
		    success: function(response){
		    	 if(response.status == "SUCCESS"){
		    		 deleteLink = contexPath + response.result;
		    		 errorField.hide('slow');
		    		 closeAndProcess();
			      }else{
			    	  var errorInfo = "";
			    	  for(i =0 ; i < response.result.length ; i++){
			    		  errorInfo += "<br>" + (i + 1) +". " + response.result[i].code;
			    	  }
			    	  errorField.html("Please correct following errors: " + errorInfo);
			    	  //$('#info').hide('slow');
			    	  errorField.show('slow');
			      }	      
			    },  
			 error: function(e){  
			    alert('Error: ' + e);  
			 }  
		  });
	/*var str = $("#sight").serialize();
	alert(str);
	$.ajax({
		type: "POST",  
		data:str,
	    url: contexPath + "/main/sights/addSight",  
	    dataType: 'multipart/form-data',
	    success: function(response){
	    	alert(response);
	    	if(response == "ok")
	    		dialog.close();
	    	else
	    		$(".inputError").append("<p>Error! </p>");
	    	dialog = document.getElementById('newSightDialog');
	      	dialogPolyfill.registerDialog(dialog);
	      	dialog.showModal(); 
	    },
		error: function(error) {
			alert("In valid name error: " + error);
		}
	});*/
}
function confirmHref(objHref, id) {
	deleteLink=objHref;
	confirmDialog(id);
}
function confirmButton(objButton, id) {
	deleteLink=objButton.value;
	confirmDialog(id);
}
function confirmDialog(id) {
	dialog = document.getElementById(id);
	dialogPolyfill.registerDialog(dialog);
	dialog.showModal();
}
function closeAndProcess() {
    dialog.close();
    window.location.href = deleteLink;
    
}
function closeDialog() {
    dialog.close();
} 