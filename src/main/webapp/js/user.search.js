$( document ).ready(function() {
	userSearch();
});

function userSearch() {
	var login = $("#searchUserField").val();
	$.ajax({
		type: "POST",  
	    url: contexPath + "/user/peopleSearch",  
	    data: "fragment=" + login,
	    success: function(response){
	    	$("#users").empty();
	    	for(var r in response){
	    		var linkText="";
	    		if(response[r].second==1){
	    			linkText = "<p>Already in your requests.</p>";
	    		}
	    		else if(response[r].second==2){
	    			linkText = "<p>Already in your friends.";
	    		}
	    		else{
	    			linkText = '<a class="inline" href="addFriend?id='+response[r].first.userId+'">Add to friends</a>';
	    		}
	    		$("#users").append("<div class='user'>"+"<h3 class='inline'>"+response[r].first.username+"</h3>"
	    				+ linkText
	    				+"</div>");
	    	}
	    	
	    },
		error: function(error) {
			alert("In valid name error: " + error);
		}
	});
}