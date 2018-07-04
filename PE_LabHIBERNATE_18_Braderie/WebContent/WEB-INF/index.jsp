<!DOCTYPE html>
<html lang="fr" >
<head>
	<s:head/>
	<meta charset="UTF-8">
	<title>Authentification</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div class="wrapper">
		
		<div class="container">
			<h1><s:text name="Welcome.message"/></h1>
			<s:actionerror cssClass="messageError"/>
			<div class="containerForm">
			<s:form cssClass="form" method="post" action="login">
				<s:textfield cssClass="input" name="user.login" placeholder="%{getText('label.username')}"></s:textfield>
				<s:password cssClass="input" name="user.password" placeholder="%{getText('label.password')}"></s:password>
				<s:token />
				<s:submit cssClass="button" key="label.login"></s:submit>
				<s:reset cssClass="button" key="label.reset"></s:reset>
				<br>
				<s:url  var="fr" action="lang" namespace="/">
					<s:param name="request_locale">fr</s:param>
				</s:url>
				<s:a href="%{fr}"><img src="${pageContext.request.contextPath}/pic/france.png"/></s:a>
				<s:url var="en" action="lang" namespace="/">
					<s:param name="request_locale">en</s:param>
				</s:url>
				<s:a href="%{en}"><img src="${pageContext.request.contextPath}/pic/united-kingdom.png"/></s:a>
			</s:form>
			</div>
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
