<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Test Page</title>
</head>
<body>

	<div>
	<label for="svc">Service : </label>
	<select name="svc" id="svc">
  		<option value="EnregistrerUtilisateur">EnregistrerUtilisateur</option> 
  		<option value="VerifierUtilisateur" selected>VerifierUtilisateur</option>
  		<option value="LireUtilisateur">LireUtilisateur</option>
  		<option value="RechercherUtilisateur">RechercherUtilisateur</option>
  		<option value="EnregistrerGroupe">EnregistrerGroupe</option>
  		<option value="GetListeGroupe">GetListeGroupe</option>
  		<option value="GetListeUtilisateursGroupe">GetListeUtilisateursGroupe</option>
  		<option value="AjouterGroupeUtilisateurService">AjouterGroupeUtilisateurService</option>
  		<option value="EnvoyerMessageUtilisateur">EnvoyerMessageUtilisateur</option>
  		<option value="RecupererMessageUtilisateur">RecupererMessageUtilisateur</option>
	</select>
	<input type="button" value="envoyer" id="btn"/>
	</div>

	


	<h2>Service Test Page</h2>
	<div>
		<h3>in:</h3>
		<textarea id="in" rows="10" cols="150"></textarea>
	</div>
	
	<div>
		<h3>out:</h3>
		<textarea id="out" rows="10" cols="150"></textarea>
	</div>

<script src="js/vendor/jquery-1.12.0.min.js"></script>
<script>


$("#btn").on("click", function(){
	var obj = { 
			nomService: $( "#svc" ).val(), 
/* 			data: {
				libelle : "",
				numu: 1,
				nom: "",
				login: "kaiji3", 
				mdp: "aaa",
				photo: "moche",
				description : "alors",
				recherche : "23",
				uparent : 1,
				idgrp: 4
			} */
			data : {
				uexp : 1,
				udest : 3,
				contenu : "Hello my friend!"
				
			}
	}; 

	
	var json = JSON.stringify(obj);
	$("#in").val(json);
	
	$.ajax({
	    type: 'post', 
	    url: './coresvc',
	    dataType: 'json',
	    data: json,
	    complete: function(data) {
	    	
	        $("#out").val(data.responseText);
	        console.log(data.responseText);
	        
	    }
	});
	
	
});

 







</script>




</body>
</html>