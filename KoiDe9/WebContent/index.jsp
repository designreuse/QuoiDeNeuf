<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!doctype html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>QuoiDeNeuf</title>
        <meta name="QuoiDeNeuf" content="QuoiDeNeuf? SYSTEME DE MESSAGERIE INSTANTANEE">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/perso.css">
    </head>
    <body>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    ...
  </div>
</nav>


	<div class="clearfix container" id="main">

      <div class="col-md-4 col-md-offset-4" id="form-step-1">
        <form class="form" id="connexionForm" action="" name="connexionForm" autocomplete="off">
          <fieldset>
            <legend>

						<input type="radio" name="usrMode" id="chxConnex" value="chxConnex" checked>
						<label for="chxConnex" class="control-label"> Connexion </label>
						
						<input type="radio" name="usrMode" id="chxInsc" value="chxInsc">
						<label for="chxInsc" class="control-label"> Inscription </label>
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
          <input type="submit" id="submitBtn" class="btn btn-block btn-danger" value="S'inscrire">
        </form>
      </div>




</div>



<script src="./js/vendor/jquery-1.12.0.min.js"></script>
<script src="./js/vendor/jquery.validate.min.js"></script>
<script src="./js/vendor/bootstrap.min.js"></script>
        <script>
        
        $(document).ready(function() {


        	$("#connexionForm").validate({
        		rules: {
        			login: "required",
        			email: {
        				required: true,
        				email: true
        			},
        			mdp: {
        				required: true,
        				minlength:3
        			}
        		},
        		messages: {
        			login: "Le champ login est requis.",
        			email: "Le champ email est requis.",
        			mdp: {
        				required: "Le champ mot de passe est requis.",
        				minlength: "Taille minimale de 5."
        			}
        		},
        		errorClass: "invalid",

        		submitHandler: function(e) {
					console.log("toto " + e);

        		}
        	});


        });
        
        
		var submitBtn = document.getElementById("submitBtn");


		$("#chxConnex").on("click", function(){
			$("#infosDiv").slideUp(400).fadeOut(200);
			submitBtn.value = "Se connecter";
		});
		
		$("#chxInsc").on("click", function() {
			$("#infosDiv").slideDown(400).fadeIn(200);
			submitBtn.value = "S'inscrire";
		});
		

		$("#chxConnex").click();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        </script>











	</body>
</html>