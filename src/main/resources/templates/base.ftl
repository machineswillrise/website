<#macro base requestCount=0>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Machines Will Rise</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<div class="container">
			<#nested>
			<p class="footer">Total requests served: ${requestCount}</p>
			<p class="footer">This website is proudly served with <span class="orange-text">plain Java</span> with no frameworks.</p>
		</div>
	</body>
</html>
</#macro>
