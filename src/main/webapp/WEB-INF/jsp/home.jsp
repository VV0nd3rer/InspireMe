<html>
	<head>
	<%@ include file="include.jspf" %>
	<title>Home</title>
	<link href="/InspireMe/css/jqvmap.css" media="screen" rel="stylesheet" type="text/css" />
    
    <script src="/InspireMe/js/jquery.js"></script>
    <script src="/InspireMe/js/jquery.vmap.js" type="text/javascript"></script>
    <script src="/InspireMe/js/jquery.vmap.europe.js" type="text/javascript"></script>
    
	<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery('#vmap').vectorMap({
		    map: 'europe_en',
		    enableZoom: false,
		    showTooltip: true,
		    onRegionClick: function(element, code, region){
		    	//window.location.href = "http://stackoverflow.com";	
		    	window.location.replace("country?country_code="+code);
		    }
		});
	});
	</script>
	</head>
	<body>
		<div id="wrapper">
			<%@ include file="head.jspf" %>
			<div id="content">
				<p>It is a secret page. It's shown only for authorization users.</p>
			
			<div>
			<form action="country" method="get">
			<input type="text" list="countryList" name="country_code">
			<input type="submit" value="Go!">
			</form>
			</div>
			
			
			
			 <datalist id="countryList">
			 <option value="uk" label="United Kingdom">
			 <option value="cz" label="Czech Republic">
			 
			</datalist>

		<div align="center" id="vmap" style="width: 680px; height: 520px;"></div>
			
			</div>
			<%@ include file="footer.jspf" %>
		</div>
		
		
	</body>
</html>