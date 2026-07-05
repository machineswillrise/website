<#macro base requestCount=0>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Machines Will Rise</title>
		<link rel="stylesheet" href="/style.css">
	</head>
	<body>
		<div class="container">
			<#nested>
			<p class="footer">Total requests served today: ${requestCount}</p>
			<p class="footer">This website is powered by <span class="orange-text">Java!</span></p>
			<p class="footer">Kill Flock cameras, anti right-to-repair laws, and gun control.</p>
		</div>
	</body>
</html>
</#macro>
