var deleteLink;
var dialog;
$(function() {
	//Modal dialogs
	$("#showNewSightDialog").click(function() {
   		dialog = document.getElementById('newSightDialog');
          	dialogPolyfill.registerDialog(dialog);
          	dialog.showModal(); 
	})
       
	$(".delPost").click(function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		confirmHref(href, 'removePost');
	})
});
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