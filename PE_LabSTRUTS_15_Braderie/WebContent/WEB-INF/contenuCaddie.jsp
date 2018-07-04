<jsp:useBean id="articleService" class="modele.services.impl.ArticleService" scope="request"/>

<!DOCTYPE html>
<html lang="fr">
<head>
	<s:head/>
	<meta charset="UTF-8">
	<title>Panier de ${user.login}</title>
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
				<div class="container minicart"></div>
			</div>
		</div>
	</nav>
	
	<div class="container-fluid breadcrumbBox text-center">
		<ol class="breadcrumb">
			<li><s:a href="currentArticle"><s:text name="label.back"></s:text></s:a></li>
			<li class="active"><a href="#">Panier</a></li>
			<li><a href="#">Paiement</a></li>
		</ol>
	</div>
	
	<div class="container text-center">

		<div class="col-md-5 col-sm-12">
			<div class="bigcart"></div>
			<h2><s:text name="caddie.title"></s:text></h2>
		</div>
		<div class="col-md-7 col-sm-12 text-left">
			<ul>
				<c:if test="${not empty caddie.articles}">
					<li class="row list-inline columnCaptions">
						<span><s:text name="article.qtite"></s:text></span>
						<span><s:text name="article.id"></s:text></span>
						<span><s:text name="article.describe"></s:text></span>
						<span><s:text name="article.marque"></s:text></span>
						<span><s:text name="article.price"></s:text></span>
						<span><s:text name="article.actions"></s:text></span>
					</li>
					<c:forEach var="entry" items="${caddie.articles}" varStatus="index">
				   		<li class="row">
				    		<span class="quantity">${entry.value}</span>
						   	<span class="itemName">${entry.key}</span>
						   	<span class="itemName">${articleService.findById(entry.key).description}</span> 
			 			   	<span class="itemName">${articleService.findById(entry.key).marque}</span>
			 			   	<span class="itemName">${articleService.findById(entry.key).prixunitaire}</span>
						   	<span class="popbtn">
					    		<s:url value="delArticle" var="front_controller" namespace="/">
					    			<s:param name="idArticle">${entry.key}</s:param>
					    			<s:param name="qtite">${entry.value}</s:param>
					   			</s:url>
					   			<s:a href = "%{front_controller}"><s:text name="article.del"></s:text></s:a>
							</span>
					    </li>
				    </c:forEach>
				    <li class="row totals">
				    	<span class="order"><s:a href="viderCaddie"><s:text name="caddie.empty"></s:text></s:a></span>
						<span class="order"><s:a href="saveCaddie"><s:text name="caddie.save"></s:text></s:a></span>
						<span class="order"><s:a href="restoreCaddie"><s:text name="caddie.restore"></s:text></s:a></span>
				    </li>	
				</c:if> 
				<c:if test="${empty caddie.articles}">
					<h3><s:text name="caddie.vide"/></h3>			  
				</c:if>
				<c:if test="${not empty message}">
					<h3><s:text name="caddie.restored"/></h3>
				</c:if>
			</ul>
		</div>
	</div>
	
	<!-- The popover content -->
	<div id="popover" style="display: none">
		<a href="${front_controller}"><span class="glyphicon glyphicon-remove"></span></a>
	</div>
	
	<!-- JavaScript includes -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/customjs.js"></script>
</body>
</html>