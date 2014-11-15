<!doctype html>
<html>
<head>
  <%@ include file="include.jspf" %>
  <meta charset="UTF-8">
  
  <title>My country</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/sights_common.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/sights_special.css">	
 
  	<script src="<%=request.getContextPath() %>/js/dialog-polyfill.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dialog-polyfill.css">
	<script type="text/javascript">
	 

	</script>
</head>
<body>
	<%@ include file="head.jspf" %>
		
	<button id="showNewSightDialog">Add new sight!</button>
	
	<!-- Modal dialogs for adding/deleting sights -->
	
  <dialog id="newSightDialog">
	<p>Adding a new sight</p>
	<div>
	 <form:form modelAttribute="sight" method="post" enctype="multipart/form-data">
		<div class="inputError"></div>
		<form:input path="title" placeholder="Title"/>
		<p><form:input path="description" placeholder="Description"/></p>
	    <p><form:input path="img_url" type="file" /></p>
		<input type="submit" value="Add sight" id="addSight">
    	<input type="button" id="close" value="Close" onclick="closeDialog()">
	 </form:form>
	</div>
 </dialog>

 <dialog id="removeSightDialog">
	<p>Are you sure to delete sight?</p>
	<p class="inputError"> All posts connected with this sight will be deleted too.</p>
	<div>
	    <input type="button"  id="closeAndProcess" value="Yes"  onclick="closeAndProcess()">
    	<input type="button" id="close" value="No" autofocus="autofocus" onclick="closeDialog()">
	
	</div>
 </dialog>

<!-- Content -->
	<div id="content">
	<input id="contextPath" type="hidden" value="<%=request.getContextPath() %>"/>
	   <div id="wrap">
    	  <div id="showcase" class="noselect">
           	<c:forEach items="${country_sigths}" var="s">
           	      <div class="card">
	     	    <h2>${s.sight_label}</h2>
	     	   	<img src="<%=request.getContextPath() %>/countryImg/countries_sights/${s.img_url}" onerror="imgError(this)"/>
	     		<p>${s.description}</p>
	     		<p><a href="<%=request.getContextPath() %>/main/posts/sightPosts?sightId=${s.sight_id}">Read more</a></p>
	     		<button type="button" value="removeSight?sightId=${s.sight_id}" onclick="confirmButton(this, 'removeSightDialog')">Delete</button>
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
  
  <script src="<%=request.getContextPath() %>/js/jquery.reflection.js"></script>
  <script src="<%=request.getContextPath() %>/js/dialogs.js"></script>
  <script src="<%=request.getContextPath() %>/js/jquery.cloud9carousel.js"></script>
  <script>
  var imgUrl;
  var check = 0;
  function pause(ms) {
	  ms += new Date().getTime();
	  while (new Date() < ms){}
	  }
  
  function imgError(image) {
	  if(check == 0){
	  imgUrl = image.src;
	  var contextPath = document.getElementById("contextPath").value;
	  image.style="background-image:url('"+contextPath+"/countryImg/countries_sights/loading.gif'); width:217px; height:153px;";
	  }
	  image.onerror="";
	  check = 1;
	  setTimeout(refreshImage, 3500, image);
	   
	  return true;
	}
  
  function refreshImage(image){
	  image.onerror="imgError(this)";
	  image.style="";
	  check = 0;
      image.src = imgUrl+"?rand="+new Date().getTime();
	  return true;
  }
  
     $(function() {
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
      }) 
      }); 
  </script>
</body>
</html>