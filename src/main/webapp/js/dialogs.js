var deleteLink;
var dialog;
$(function() {
	//Modal dialogs
	$("#showNewSightDialog").click(function() {
		addNewSight();
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
	$.ajax({
		type: "POST",  
	    url: contexPath + "/main/sights/newSight",  
	    success: function(response){
	    	alert(response);
	    	
	    	dialog = document.getElementById('newSightDialog');
          	dialogPolyfill.registerDialog(dialog);
          	dialog.showModal(); 
	    },
		error: function(error) {
			alert("In valid name error: " + error);
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