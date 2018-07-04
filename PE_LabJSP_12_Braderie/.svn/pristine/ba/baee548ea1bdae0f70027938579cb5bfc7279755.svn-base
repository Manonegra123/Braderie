<jsp:useBean id="articleService" class="modele.services.impl.ArticleService" scope="request"/>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<title>Caddie</title>
</head>
<body>
	<a href="FC?action=logout">Se déconnecter</a>
	<h2>${user.login}</h2>
	<h2>Contenu du caddie</h2>
	<c:if test="${not empty caddie.articles}">
		<table>
			<tr>
				<th>N°</th>
				<th>Identifiant</th>
				<th>Désignation</th>
				<th>Marque</th>
				<th>Prix</th>
				<th>Quantité</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="entry" items="${caddie.articles}" varStatus="index">
			   	<tr>
			    	<td>${index.count}</td>
			    	<td>${entry.key}</td>
			    	<td>${articleService.findById(entry.key).description}</td> 
 			    	<td>${articleService.findById(entry.key).marque}</td>
 			    	<td>${articleService.findById(entry.key).prixunitaire}</td>
			    	<td>${entry.value}</td>
			    	<td><c:url value="FC?action=supprimer" var="front_controller">
			    			<c:param name="idArticle" value="${entry.key}"/>
			    			<c:param name="qtite" value="${entry.value}"/>
			   			</c:url>
			   			<a href = "${front_controller}"><button type="submit">Supprimer</button></a>
					</td>
			    </tr>
		    </c:forEach>
		</table>
	</c:if> 
	<c:if test="${empty caddie.articles}">
		<h3>Le caddie est vide</h3>			  
	</c:if>
	<c:if test="${not empty message}">
		<h3>${message}</h3>
	</c:if>
	<a href="FC?action=current">Retour à la sélection</a>
	<a href="FC?action=vider">Vider le contenu</a>
	<a href="FC?action=save">Sauvegarder le caddie</a>
	<a href="FC?action=restore">Restaurer le caddie</a>
</body>
</html>