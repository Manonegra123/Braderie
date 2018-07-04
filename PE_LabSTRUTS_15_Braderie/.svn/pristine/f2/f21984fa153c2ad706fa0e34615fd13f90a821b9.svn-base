<!DOCTYPE html>
<html lang="fr">
<head>
	<s:head/>
	<meta charset="utf-8">
	<title>Page Article</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="assets/css/custom.css"/>
</head>
<body>
	<nav class="navbar">
		<div class="container">
			<s:a class="navbar-brand" href="logout"><s:text name="label.logout"></s:text></s:a>
			<div class="navbar-right">
				<s:a class="navbar-brand" href="restoreCaddie"><s:text name="caddie.restore"></s:text></s:a>
				<s:a class="navbar-brand" href="afficheCaddie"><s:text name="caddie.display"></s:text></s:a>
			</div>
		</div>
	</nav>
	
	<div class="container-fluid breadcrumbBox text-center">
		<ol class="breadcrumb">
			<li><s:a href="precedent"><s:text name="article.prev"></s:text></s:a></li>
			<li><s:a href="suivant"><s:text name="article.next"></s:text></s:a></li>
		</ol>
	</div>
	
	<h2>${user.login}</h2>
	<h2>ARTICLE n°: ${numArticle}</h2>
	<table>
		<tr>
			<td><s:text name="article.id"></s:text></td>
			<td>${article.id}</td>
		</tr>
		<tr>
			<td><s:text name="article.describe"></s:text></td>
			<td>${article.description}</td>
		</tr>
		<tr>
			<td><s:text name="article.marque"></s:text></td>
			<td>${article.marque}</td>
		</tr>
		<tr>
			<td><s:text name="article.price"></s:text></td>
			<td>${article.prixunitaire}</td>
		</tr>
		<tr>
			<td><s:text name="article.qtite"></s:text></td>
			<td>${qtite}</td>
	</table>
	<p>
		<s:a href="ajouterArticle">		
			<span class="order"><button type="submit"><s:text name="article.add"></s:text></button></span>
		</s:a>
	</p>
	
</body>
</html>