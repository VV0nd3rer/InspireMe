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
		    url: contexPath + "/sights/addSight", 
		    data: formData,
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
			    	  errorField.show('slow');
			      }	      
			    },  
			 error: function(e){  
			    alert('Error: ' + e);  
			 }  
		  });
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