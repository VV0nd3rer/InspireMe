<!doctype html>
<html>
<head>
  <%@ include file="include.jspf" %>
  <meta charset="UTF-8">
  
  <title>My country</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/sights_common.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/sights_special.css">	
 
  	<script src="../js/dialog-polyfill.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/dialog-polyfill.css">
</head>
<body>
	<%@ include file="head.jspf" %>
		
	<button id="showNewSightDialog">Add new sight!</button>
	
	<!-- Modal dialogs for adding/deleting sights -->
	
	<dialog id="newSightDialog">
	<p>Adding a new sight</p>
	<div>
	<form action="sights/newSight" method="post" enctype="multipart/form-data">
	 
		<input type="text" value="" name="title"  placeholder="Title">
		<p>
			<input type="text" value="" name="description" placeholder="Description">
			<p>
		<input type="file" value="" name="img_url" id="img_url">
		<p>
		<input type="submit" value="Add sight">
    	<input type="button" id="close" value="Close" onclick="closeDialog()">
	</form>
	</div>
  
		</dialog>

<dialog id="removeSightDialog">
	<p>Are you sure to delete sight?</p>
	<p style="color:red; font-weight:bold"> All posts connected with this sight will be deleted too.</p>
	<div>
	    <input type="button"  id="closeAndProcess" value="Yes"  onclick="closeAndProcess()">
    	<input type="button" id="close" value="No" autofocus="autofocus" onclick="closeDialog()">
	
	</div>
</dialog>

<!-- Content -->
	<div id="content">
	   <div id="wrap">
    	  <div id="showcase" class="noselect">
           	<c:forEach items="${country_sigths}" var="s">
           	      <div class="card">
	     	    <h2>${s.sight_label}</h2>
	     	   	<img src="<%=request.getContextPath() %>/countryImg/countries_sights/${s.img_url}"/>
	     		<p>${s.description}</p>
	     		<p><a href="<%=request.getContextPath() %>/main/posts/sightPosts?sightId=${s.sight_id}">Read more</a></p>
	     		<%-- <p><a href="sights/removeSight?sightId=${s.sight_id}&countryCode=${s.country_code}" onclick="return confirm('M, suka?')">Delete</a></p> --%>
	     		<button type="button" value="sights/removeSight?sightId=${s.sight_id}" onclick="confirmation(this)">Delete</button>
     	    </div>
    	 	</c:forEach>
    	 	
     	  </div>
    	  <footer>
      		<p id="item-title">&nbsp;</p>
      		<div id="nav" class="noselect">
        	 <button class="left"> < </button>
             <button class="right"> > </button>
      		</div>
      		<hr>
     	  </footer>
  	   </div>
    </div>
  <%@ include file="footer.jspf" %> 
  	
  
  <script src="<%=request.getContextPath() %>/js/jquery.js"></script>
  <script src="<%=request.getContextPath() %>/js/jquery.reflection.js"></script>
  <script src="<%=request.getContextPath() %>/js/jquery.cloud9carousel.js"></script>
  <script>
  var dialog;
  var deleteLink;
    $(function() {
    	
    	//Modal dialogs
    	    	
	   	document.querySelector('#showNewSightDialog').onclick = function() {
	   		dialog = document.getElementById('newSightDialog');
	          	dialogPolyfill.registerDialog(dialog);
	          	dialog.showModal(); 
	            };
	               
	    	             
    	//------------------------------------
    	
      var showcase = $("#showcase"), title = $('#item-title')

      showcase.Cloud9Carousel( {
        yOrigin: 42,
        yRadius: 40,
        itemClass: "card",
        buttonLeft: $("#nav > .left"),
        buttonRight: $("#nav > .right"),
        bringToFront: true,
        onRendered: rendered,
        onLoaded: function() {
          showcase.css( 'visibility', 'visible' )
          showcase.css( 'display', 'none' )
          showcase.fadeIn( 1500 )
        }
      } )

      function rendered( carousel ) {
        title.text( carousel.nearestItem().element.getElementsByTagName( "h2" )[0].innerHTML )

        // Fade in based on proximity of the item
        var c = Math.cos((carousel.floatIndex() % 1) * 2 * Math.PI)
        title.css('opacity', 0.5 + (0.5 * c))
      }

      //
      // Simulate physical button click effect
      //
      $('#nav > button').click( function( e ) {
        var b = $(e.target).addClass( 'down' )
        setTimeout( function() { b.removeClass( 'down' ) }, 80 )
      } )

      $(document).keydown( function( e ) {
        //
        // More codes: http://www.javascripter.net/faq/keycodes.htm
        //
        switch( e.keyCode ) {
          /* left arrow */
          case 37:
            $('#nav > .left').click()
            break

          /* right arrow */
          case 39:
            $('#nav > .right').click()
        }
      } )
      
      
    })
    
        
  function confirmation(objButton) {
		         	dialog = document.getElementById('removeSightDialog');
		                	dialogPolyfill.registerDialog(dialog);
		                	dialog.showModal();
		                	deleteLink=objButton.value;
		               }
    
    function closeAndProcess() {
        dialog.close();
        window.location.href = deleteLink;
      }
      
      function closeDialog() {
          dialog.close();
        }
     
   
  </script>
</body>
</html>