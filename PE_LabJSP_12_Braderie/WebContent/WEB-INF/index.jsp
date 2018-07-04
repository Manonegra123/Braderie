<!DOCTYPE html>
<html lang="fr" >
<head>
	<meta charset="UTF-8">
	<title>Grande-Braderie-Authentification</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="wrapper">
		
		<div class="container">
			<h1>Bienvenue sur la grande braderie</h1>
			
			<p>
				<% if(null!=request.getAttribute("errorMessage")){ %>
						<div id="messageError">
							<%=request.getAttribute("errorMessage")%>
						</div>
				<% } %>
			</p>
			<form method="post" class="form" action="FC?action=login">
				<input type="text" placeholder="Identifiant" name="login">
				<input type="password" placeholder="Mot de passe" name="pwd">
				<button type="submit" id="login-button">Se connecter</button>
				<input type="reset" id="refresh-button">
			</form>	
		</div>
		
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
		
	</div>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="js/index.js"></script>
</body>
</html>
