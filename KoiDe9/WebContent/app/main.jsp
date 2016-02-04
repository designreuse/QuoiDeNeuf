<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="services.beans.Utilisateur"%>
<!doctype html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>QuoiDeNeuf - Main</title>
<meta name="QuoiDeNeuf"
	content="QuoiDeNeuf? SYSTEME DE MESSAGERIE INSTANTANEE">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/perso.css">
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="../index.jsp">QuoiDeNeuf?</a>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li></li>
					<li><a href="../index.jsp?deconnexion=oui"> <span
							id="seDeco"></span> Se déconnecter
					</a></li>
				</ul>

			</div>
		</div>
	</nav> <!-- Fin navbar -->


	<div class="clearfix container main mainDiag">
		<button type="button" class="col-md-2 btn btn-success" name="showprofil" id="showprofil"><span class="glyphicon glyphicon-user"></span> Mon Profil</button>
		<button type="button" class="col-md-2 btn btn-success" name="showGrps" id="showGrps" value="Mes groupes" ><span class="glyphicon glyphicon glyphicon-list-alt"></span> Mes groupes</button>
		<button type="button" class="col-md-3 btn btn-success" name="addnewusers" id="addnewusers" ><span class="glyphicon glyphicon glyphicon-search"></span> Trouver des utilisateurs</button>
		<div class="mainc">
			<div id="card">
				<div class="col-xs-12 front" id="front">
					<div class="mainUserPhoto">
						<img src='../img/avatars/${sessionScope["dejaConnecte"].photo}'
							alt="Avatar de l'utilisateur" />
						<blockquote>
							<p id='numu_${sessionScope["dejaConnecte"].numu}'>
								Bonjour, <strong>${sessionScope["dejaConnecte"].nom}</strong>
							</p>
							<footer>${sessionScope["dejaConnecte"].description}</footer>
						</blockquote>
					</div>
				</div>
				<div class="col-xs-12 back" id="backDetailUsr" style="display: none;">
					<div id="errorZone"></div>
					<form class="form" id="modUserForm" name="modUserForm">
						<fieldset>
							<legend>Détails Utilisateur</legend>
							<div class="col-md-12" id="userDetails">
								<div class="col-md-4 text-center">
									<div class="form-group">
										<h1>${sessionScope["dejaConnecte"].login}</h1>
									</div>
									<div class="form-group img-thumbnail">
										<img id="usrAvatar" class="img-thumbnail"
											src='../img/avatars/${sessionScope["dejaConnecte"].photo}'
											alt="Avatar de l'utilisateur" />
									</div>
									<div class="form-group">
										<input type="file" name="fileSelect" id="btnH" class="hidden" />
										<div class="input-group btnShown">
											<span class="input-group-btn"><input type="button"
												id="btnP" class="btn btn-primary" value="Parcourir&#8230;" /></span>
											<input type="text" id="fileName" class="form-control"
												disabled />

										</div>
									</div>

								</div>

								<div class="col-md-8" id="inputsUserData">
									<div class="form-group">
										<label for="nom">Nom</label><input type="text" name="nom"
											id="nom" class="form-control"
											value='${sessionScope["dejaConnecte"].nom}' />
									</div>
									<div class="form-group">
										<label for="mdp">Mot de passe *</label> <input type="password"
											name="mdp" id="mdp" class="form-control"
											placeholder="Mot de passe" value='xxxxxxxx'
											required="required" />
									</div>
									<div class="form-group">
										<label for="email">Email *</label> <input type="email"
											name="email" id="email" class="form-control"
											value='${sessionScope["dejaConnecte"].email}'
											required="required" />
									</div>
									<div class="form-group">
										<label for="description">Description</label> <input
											type="text" name="description" id="description"
											class="form-control"
											value='${sessionScope["dejaConnecte"].description}' />
									</div>
									<br />
									<div class="form-group text-right">
										<input type="button" id="btnReset" class="btn btn-default" value="Reset" />
										<input type="submit" id="btnAppliquer" class="btn btn-warning" value="Appliquer" />
									</div>
								</div>
							</div>

						</fieldset>
					</form>
				</div> <!-- fin back1 -->
				
					<div class="col-md-12 back" id="backRecherche" style="display: none;">
					<div id="errorZoneRecherche"></div>
		<div class="input-group">
			<input type="search" class="form-control" id="nomRecherche" placeholder="Nom ou login d'un utilisateur">
			<span class="input-group-btn">
				<button id="rechercherBtn" class="btn btn-primary" type="button">Rechercher</button>
			</span>
		</div>
	<br />
	<br />
	<div class="col-md-12" id="resultatRechZone">
		<div id="errorZone"></div>
		<table class="table table-hover table-condensed" id="tableUsers"></table>
	</div><!-- fin back2 -->
	</div>


			</div>
		</div> <!-- fin div detail user -->
		
		
	</div>
	<div class="container main2 mainDiag" id="mygroups" style="display: none;">
		<div class="clearfix" id="groupeZone">
			<div id="errorZoneGrp"></div>
			<div class="col-md-12">
				<h4>Mes groupes</h4>
				<div id="groupeListe"></div>
			</div>
			<div class="col-md-12" id="detailZone" style="display: none;"></div>
		</div>
	</div>



<script src="../js/vendor/jquery-1.12.0.min.js"></script>
<script src="../js/vendor/jquery.validate.min.js"></script>
<script src="../js/vendor/bootstrap.min.js"></script>
<script src="../js/utils.js"></script>
<script src="../js/services.js"></script>

<script>
	$('#modUserForm').submit(false);
	var submitBtn = $("#btnAppliquer");		
	var rechercherUsrBtn = $("#addnewusers");
	var afficherProfilBtn = $("#showprofil");
	var rechercherBtn = $("#rechercherBtn");
	var listeGroupes = [];
	var uparent = '${sessionScope["dejaConnecte"].numu}';
	
	

	var getListeGroupes = function(){
		submitBtn.disabled = true;
		var obj = {
			nomService : "GetListeGroupe",
			data : { numu : '${sessionScope["dejaConnecte"].numu}' }
		};
		
		services.call(obj, function(data) {
			submitBtn.disabled = false;	
			var reponse = JSON.parse(data.responseText);
			
			if(reponse){
				if(reponse.returnCode === 8){
					services.showErrorAlert("errorZoneGrp", reponse.message);
				}else{
					var res = "<table class='table'>";
					if(reponse.returnCode === 4){
						services.showErrorAlert("errorZoneGrp", reponse.message);						
					}
					listeGroupes = reponse.response;
					$.each(reponse.response, function(i, obj){
						res += "<tr><td><strong>" +obj.libelle+ "</strong></td><td id=lsUsrs_" +obj.idgrp+ "></td></tr>";	
						getListeUsrGroupes(obj.idgrp);
						
							
					});
					res += "</table>";
					$("#groupeListe").html(res).hide().fadeIn(600);
					
				}
			}
		}, true);
	};


	var getListeUsrGroupes = function(id){
		var obj = {
			nomService : "GetListeUtilisateursGroupe",
			data : {
				uparent : '${sessionScope["dejaConnecte"].numu}',
				idgrp	: id
			}
		};
		
		services.call(obj, function(data) {
			var resp = JSON.parse(data.responseText);
			
			if(resp){
				if(resp.returnCode === 8){
					services.showErrorAlert("errorZoneGrp", resp.message);
				}else{
					if(resp.returnCode === 4){
						$("#lsUsrs_"+id).html(resp.message);
												
					}else{
						
					var res = "";
					$.each(resp.response, function(i, obj){
						var nom = obj.nom || obj.login;
						var idUsr = "usr_" +id+ "_" +obj.numu;
						var img = "../img/avatars/" +obj.photo;
						$.get("tmpl/user.tmpl").success(function(contenu){
							res += contenu.replace(/##idUsr##/g, idUsr)
							.replace(/##img##/g, img)
							.replace(/##nom##/g, nom)
							.replace(/##description##/g, obj.description);
						
							$("#lsUsrs_"+id).html(res);
	
						});
					});
					}
				}
			}
		}, true);
	};

	
//Update du profil
	var updateprofil = function(){
		submitBtn.disabled = true;
		var obj = {
		nomService : "UpdateUtilisateur",
			data : {
				nom : encodeHtmlEntity($("#login").val()),
				mdp : encodeHtmlEntity($("#mdp").val()),
				email : encodeHtmlEntity($("#email").val()),
				description : encodeHtmlEntity($("#description").val())
			}
		};
		services.showErrorAlert("errorZone", "TODO Update utilisateur");
		submitBtn.disabled = false;
		/*
		services.call(obj, function(data) {
			submitBtn.disabled = false;	
			var resp = JSON.parse(data.responseText);
		    //$("#out").val(data.responseText);
			console.log(data.responseText + " | " + resp);
			if(resp){
				if(resp.returnCode == 8){
					services.showErrorAlert("errorZone", resp.message);
				}else{
					location.replace("./app/main.jsp");		    			
				}
			}
		    
		});
		 */

	};

	//Validation avant update profil	
	var validerFormprofil = function(){
		$("#modUserForm").validate(
				{
					rules : {
						email : { required : true, email : true },
						mdp 	: { required : true, minlength : 3 }
					},
					messages : {
						email : "Le champ email est requis.",
						mdp 	: {
							required : "Le champ mot de passe est requis.",
							minlength : "Taille minimale de 3."
						}
					},
					errorClass : "invalid",
					submitHandler : updateprofil
				})
	}; 	
					
					
					
			


		

		
		

		
		
		
			
			
		


			

		var msgUsr = function(id){
			var idgrp = id.split("_")[1];
			var numu = id.split("_")[2];
			
			alert("message pour l'utilisateur " +numu+ " du groupe " + idgrp);
		};
		
		var viewUsr = function(id){
			var idgrp = id.split("_")[1];
			var numu = id.split("_")[2];
			
			var obj = {
					nomService : "LireUtilisateur",
					data : {
						numu : numu
					}
				};
				services.call(obj, function(data) {
					var resp = JSON.parse(data.responseText);
					
					if(resp){
						if(resp.returnCode === 8 || resp.returnCode === 4){
							services.showErrorAlert("errorZoneGrp", resp.message);
						}else{
							var infos = resp.response[0];
							var res ="";
							console.log(infos);
							$.get("tmpl/userDetail.tmpl").success(function(contenu){
								res += contenu.replace(/##usrLogin##/g, infos.login)
								.replace(/##usrPhoto##/g, infos.photo)
								.replace(/##usrNom##/g, infos.nom)
								.replace(/##usrDescription##/g, infos.description);
									
								$("#detailZone").html(res);
								$("#detailZone").slideToggle(400, "swing");
							});
							
						}	
					}
				}, true);

			
		};
		
		var delUsr = function(id){
			var obj = {
					nomService : "SupprimerUtilisateurDunGroupeService",
					data : {
						uparent : uparent,
						ufils : id.split("_")[2],
						idgrp : id.split("_")[1]
					}
				};
				services.call(obj, function(data) {
					var resp = JSON.parse(data.responseText);
					
					if(resp){
						if(resp.returnCode === 8 || resp.returnCode === 4){
							services.showErrorAlert("errorZoneGrp", resp.message);
						}else{
							//services.showErrorAlert("errorZoneGrp", resp.message, "success");
							getListeGroupes();
						}	
					}
				}, true);
		};
		
		
		
		
		
		var addUsr = function(usrToAdd){
			var sel = $("#sel_"+usrToAdd);
			var idgrp = sel.val();
			
			var obj = {
					nomService : "AjouterUtilisateurDansGroupe",
					data : {
						uparent : uparent,
						ufils : usrToAdd,
						idgrp : sel.val()
					}
				};
				services.call(obj, function(data) {
					var resp = JSON.parse(data.responseText);
					
					if(resp){
						if(resp.returnCode === 8 || resp.returnCode === 4){
							services.showErrorAlert("errorZoneRecherche", resp.message);
						}else{
							services.showErrorAlert("errorZoneRecherche", resp.message, "success");
							getListeGroupes();
						}	
					}
				}, true);
			
			
			
			
			
		};
		
		
		
	
		
		
		
		
		
		// Gestion Recherche
 var rechercherUtilisateurs = function(){
	var obj = {
      nomService: "RechercherUtilisateur",
      data: {
          nom: $('#nomRecherche').val()
      }
		};
	
rechercherBtn.prop('disabled', true);	
services.call(obj,function(data){
  var reponse = JSON.parse(data.responseText);
  $("#errorZone").html("");
  $("#tableUsers").html("");
  if(reponse){
   	rechercherBtn.prop('disabled', false);
      if(reponse.returnCode == 8){
          services.showErrorAlert("errorZone", reponse.message);
      }else if(reponse.returnCode == 4){    
          services.showErrorAlert("errorZone", reponse.message, "warning");
      }else{
      	
      	
      	
          var table=$("#tableUsers");
          var res = "<tr><th></th><th class='text-center'>Login</th><th class='text-center'>Nom</th><th class='text-center'>Email</th><th></th></tr>";
          $.each(reponse.response,function(i,o){
          	
          	$.get("./tmpl/rowRecherche.tmpl").success(function(contenu){
				res = contenu.replace(/##idUsr##/g, o.numu)
							.replace(/##login##/g, o.login)
							.replace(/##nom##/g, o.nom)
							.replace(/##email##/g, o.email)
							.replace(/##photo##/g, "../img/avatars/" + o.photo);
				
				
				
				$("#tableUsers").append(res);
				$.each(listeGroupes, function(key, value) {  
					console.log(key + " : " + value);
			     $('#sel_'+o.numu)
			         .append($("<option></option>")
			         .attr("value",value.idgrp)
			         .text(value.libelle)); 
				});
			});
          	
          });
				
          $("#tableUsers").html(res);
          
      }
  }
},true);
};

rechercherBtn.on("click",rechercherUtilisateurs);
$("#nomRecherche").on("keyup",rechercherUtilisateurs);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
	// Selection d'une image pour l'upload
	$("#btnP").on("click", function() {
		$("#btnH").click();
	});

	$("#btnH").on("change", function() {
		$("#fileName").val($("#btnH").val());
	});

			
			


	$("#showGrps").on("click", function() {
		$("#showGrps").toggleClass("btn-success"); 
		$("#mygroups").slideToggle(200, "swing");
	});

	
	
	
	// Flip zone detail utilisateur
	afficherProfilBtn.on("click", function() {
		afficherProfilBtn.toggleClass("btn-success"); 
		$("#card").toggleClass("flipped");
		if($("#card").hasClass("flipped")){
			$("#backRecherche").hide();
			$("#backDetailUsr").hide();
			afficherProfilBtn.prop('disabled', false);
			rechercherUsrBtn.prop('disabled', true);
		}else{
			afficherProfilBtn.prop('disabled', false);
			rechercherUsrBtn.prop('disabled', false);
		}
		
		
		
		$("#backDetailUsr").slideToggle(200, "swing");
		$("#front").toggle(400, "swing");
	});
	
	rechercherUsrBtn.on("click", function(){
		rechercherUsrBtn.toggleClass("btn-success"); 
		$("#card").toggleClass("flipped");
		if($("#card").hasClass("flipped")){
			$("#backRecherche").hide();		
			$("#backDetailUsr").hide();
			afficherProfilBtn.prop('disabled', true);
			rechercherUsrBtn.prop('disabled', false);
		}else{
			afficherProfilBtn.prop('disabled', false);
			rechercherUsrBtn.prop('disabled', false);
		}
		$("#backRecherche").slideToggle(200, "swing");
		$("#front").toggle(400, "swing");

	});
	
	
	
	
	$(document).ready(function() {
		
		validerFormprofil();
		getListeGroupes();
	});
	
	
	
	
</script>





</body>
</html>