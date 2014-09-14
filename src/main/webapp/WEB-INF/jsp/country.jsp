<!doctype html>
<html lang="en">
	<head>
	<%@ include file="include.jspf" %>
	<meta charset="UTF-8">
  <title>Cloud 9 Carousel: JS / HTML5 / CSS3</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/sights_common.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/sights_special.css">
  
  
	</head>
	
	
	<body>
	 <%@ include file="head.jspf" %>
	 <div id="content">
	<div id="wrap">
    <div id="showcase" class="noselect">
           
     <c:forEach items="${country_sigths}" var="s">
     
     	<div class="card">
     	    <h2><a href="<%=request.getContextPath() %>/main/posts">${s.sight_label}</a></h2>
     		<img src="<%=request.getContextPath() %>/countryImg/countries_sights/${s.img_url}"/>
     		<p>${s.description}</p>
     	</div>
     </c:forEach>
     
    </div>
    
    <footer>
      <p id="item-title">&nbsp;</p>
      <div id="nav" class="noselect">
        <button class="left">
         <
        </button>
        <button class="right">
          >
        </button>
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
    $(function() {
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
  </script>
   		
	</body>
</html>