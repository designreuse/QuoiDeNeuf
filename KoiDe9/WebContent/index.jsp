<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty param.deconnexion}"> 
	<c:if test="${'oui' == param.deconnexion}"> 
 		<%session.invalidate();%>
	</c:if>
	<c:redirect url="./index.jsp"/>
</c:if>

<c:if test="${not empty sessionScope.dejaConnecte}"> 
  <c:redirect url="./app/main.jsp"/>
</c:if>

<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Connexion - QuoiDeNeuf</title>
    <meta name="QuoiDeNeuf" content="QuoiDeNeuf? SYSTEME DE MESSAGERIE INSTANTANEE">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/perso.css">
	<link rel="shortcut icon" href="./favicon.ico" />
</head>

<body>
	
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header"><a class="navbar-brand" href="./index.jsp">QuoiDeNeuf ?</a></div>
		</div>
	</nav>
				

	<div class="clearfix container main" id="mainConnex">
	
	<div id="errorZone"></div>
      <div class="col-md-4 col-md-offset-4" id="form-step-1">
      	
        <form class="form" id="connexionForm" name="connexionForm">
          <fieldset>
            <legend>
				<input type="radio" name="usrMode" id="chxConnex" value="chxConnex" checked="checked">
				<label for="chxConnex" class="control-label">Connexion </label>
						
				<input type="radio" name="usrMode" id="chxInsc" value="chxInsc">
				<label for="chxInsc" class="control-label">Inscription </label>
            </legend>


			<div class="form-group"> <label for="login">Login *</label> <input type="text" name="login" id="login" class="form-control" placeholder="Login" required="required"/> </div>
			<div  class="form-group"> <label for="mdp">Mot de passe *</label> <input type="password" name="mdp" id="mdp" class="form-control" placeholder="Mot de passe" required="required"/>  </div>
		
			<div id="infosDiv" style="display: none;">
				<div  class="form-group"><label for="nom">Nom</label><input type="text" name="nom" id="nom" class="form-control" placeholder="Nom" /></div>
				<div  class="form-group"> <label for="email">Email *</label> <input type="email" name="email" id="email" class="form-control" placeholder="Email" required="required"/> </div>
				<div  class="form-group"> <label for="description">Description</label> <input type="text" name="description" id="description" class="form-control" placeholder="description" /> </div>
			</div>
		
		</fieldset>
		<hr>
          <input type="submit" id="submitBtn" class="btn btn-block btn-success" value="Se connecter">
        </form>
      </div>

	</div>



<script src="./js/vendor/jquery-1.12.0.min.js"></script>
<script src="./js/vendor/jquery.validate.min.js"></script>
<script src="./js/vendor/bootstrap.min.js"></script>
<script src="./js/utils.js"></script>
<script src="./js/services.js"></script>
<script src="./js/pages/index.js"></script>


	</body>
</html>