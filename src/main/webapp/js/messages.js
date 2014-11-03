$( document ).ready(function() {
	tinyMCEInit();
	$('#replyForm').hide();
});
var check = 1;
$(function() {

	$("#reply").click(function(event) {
		event.preventDefault();
		if(check==0){
			return false;
		}
		else {
			editor_id = $("#replyText").attr('id');
			$('#replyForm').show();
			tinymce.get(editor_id).show();
			
			check = 0;
		}
	});
	
	$("#cancel").click(function(event) {
		event.preventDefault();
		editor_id = $("#replyText").attr('id');
		tinymce.get(editor_id).hide();  
		$('#replyForm').hide();
		check = 1;
			});
	
});

function tinyMCEInit() {
	tinymce.init({
	    selector: "textarea.edit",
	    setup: function(ed) {
	        ed.on('init', function(e) {
	            e.target.hide();
	        });},
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