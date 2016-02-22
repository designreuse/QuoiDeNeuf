<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="services.beans.Utilisateur"%>
<!doctype html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Main - QuoiDeNeuf</title>
<meta name="QuoiDeNeuf" content="QuoiDeNeuf? SYSTEME DE MESSAGERIE INSTANTANEE">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/normalize.css">
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
					<li><a href="../index.jsp?deconnexion=oui"> <span id="seDeco"></span> Se déconnecter </a></li>
				</ul>

			</div>
		</div>
	</nav> <!-- Fin navbar -->
	

	<div class="clearfix container main mainDiag">
		<button type="button" class="col-md-2 btn btn-primary" name="showprofil" id="showprofil"><span class="glyphicon glyphicon-user"></span> Mon Profil</button>
		<button type="button" class="col-md-2 btn btn-primary" name="showGrps" id="showGrps" value="Mes groupes" ><span class="glyphicon glyphicon-list-alt"></span> Mes groupes</button>
		<button type="button" class="col-md-3 btn btn-primary" name="addnewusers" id="addnewusers" ><span class="glyphicon glyphicon-search"></span> Trouver des utilisateurs</button>
		<div class="mainc">
			<div id="card">
				<div class="col-xs-12 front" id="front">
					<div class="col-md-12 mainUserPhoto">
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
					<form method="POST" id="myAvatarform" action="../upload" enctype="multipart/form-data">
							<input type="file" name="fichier" id="btnH" type="file" name="fichier" class="hidden" />
					</form>
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
										<div class="input-group btnShown">
											<span class="input-group-btn"><input type="button" id="btnP" class="btn btn-primary" value="Parcourir&#8230;" /></span>
											<input type="text" id="fileName" class="form-control" disabled />

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
										<input type="reset" id="btnReset" class="btn btn-default" />
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
		<div class="clearfix innerZone" >
			<div class="col-md-12" id="errorZoneGrp"></div>
			<div class="col-md-11"><h4> Mes groupes</h4></div>
			<div class="col-md-1 text-right"><button type="button" id="showAddGrp" class="btn btn-default btn-xs btnxss "><span id="arrow" class="glyphicon glyphicon-chevron-down"></span></button></div>	
			<div class="col-md-12 table-responsive">
				<div id="crGrpDiv" style="display: none;">
					<div class="input-group"> <span class='input-group-btn'> <button id='creerGrpBtn' class='btn btn-primary btn-sm' type='button'>Créer un nouveau groupe</button> </span> <input type='text' class='form-control input-sm' id='libelleGrp' placeholder='Libelle du groupe'> </div>
				</div>
				<table class="table" id="groupeListe"></table>
			</div>
			<div class="col-md-12" id="detailZone" ></div>
		</div>
	</div>

	<div class="container main2 mainDiag" id="myChat">
			<div class="clearfix innerZone" >
			<div class="col-md-11"><h4> Mes Discussions</h4></div>
		<div id="chatZone"></div>
		</div>
	</div>
	<div class="col-md-12" id="viewImg" ></div>	
	
	
	<div>
	    <div id="myImgModal" class="modal fade">
        <div class="modal-dialog modal-lg">
            <div class="clearfix modal-content text-center">
                    <div class="modal-header success">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Aperçu Image</h4>
        </div>
            
            
            	<div class="col-md-12 modal-body">
	               <img class="col-md-12" id="prevImg" alt="" src="">
            	</div>
            </div>
        </div>
    </div>
	</div>
	
	
	
<a href="#0" class="cd-top" style="display: none;">Top</a>

<script src="../js/vendor/jquery-1.12.0.min.js"></script>
<script src="../js/vendor/jquery.validate.min.js"></script>
<script src="../js/vendor/bootstrap.min.js"></script>
<script src="../js/utils.js"></script>
<script src="../js/services.js"></script>

<script>
	$('#modUserForm').submit(false);
	var rechercherUsrBtn = $("#addnewusers");
	var afficherProfilBtn = $("#showprofil");
	var applyUpdateBtn = $("#btnAppliquer");		
	var rechercherBtn = $("#rechercherBtn");
	var uparent = '${sessionScope["dejaConnecte"].numu}';
	var mesGroupes = [];
	var tmpAvatar;
	
	// Recuperer les groupes de usr
	var getListeGroupes = function(){
		var obj = { nomService : "GetListeGroupe", data : { numu : uparent } };
		
		services.call(obj, true).then(function(reponse) {
			if(reponse){
				if(reponse.returnCode === 8){
					services.showErrorAlert("errorZoneGrp", reponse.message);
				}else{
					mesGroupes = reponse.response;
					
					var res = "";
					
					//var res = "<tr><td><button type='button' id='creerGrpBtn' class='btn btn-info btn-sm btn-block'>Créer le groupe <span class='glyphicon glyphicon glyphicon-plus'></span></button></td><td></td></tr>";
					if(reponse.returnCode === 4){
						services.showErrorAlert("errorZoneGrp", reponse.message);						
					}
					$.each(reponse.response, function(i, obj){
						var lib = obj.libelle.length > 17 ? obj.libelle.substring(0,17)+"..." : obj.libelle ;
						var btn = "<button type='button' id='supp_" +obj.idgrp+ "' onclick='delGrp("+obj.idgrp+")' class='btn btn-default  btn-xs btn-block'>" +lib+ " <span class='glyphicon glyphicon-remove'></span></button>";
						res += "<tr><td class='col-md-2' style='border-right: 2px dashed #CCC;'>"+btn+"</td><td class='col-md-10' id=lsUsrs_" +obj.idgrp+ "></td></tr>";	
						getListeUsrGroupes(obj.idgrp);
					});
					$("#groupeListe").html(res).hide().fadeIn(100);
				}
			}
		});
	};

	// Permet de recuperer la liste des utilisateurs d'un groupe
	var getListeUsrGroupes = function(id){
		var obj = {
			nomService : "GetListeUtilisateursGroupe",
			data : {
				uparent : uparent,
				idgrp	: id
			}
		};
		
		services.call(obj, true).then(function(reponse) {
			if(reponse){
				if(reponse.returnCode === 8){
					services.showErrorAlert("errorZoneGrp", reponse.message);
				}else{
					if(reponse.returnCode === 4){
						$("#lsUsrs_"+id).html(reponse.message);
					}else{
						$.each(reponse.response, function(i, obj){
							var idUsr = "usr_" +id+ "_" +obj.numu;
							var nom = obj.nom || obj.login;
							var img	= "../img/avatars/" +obj.photo;
							
							$.get("tmpl/user.tmpl").success(function(contenu){
								var res = contenu
								.replace(/##idUsr##/g, idUsr)
								.replace(/##img##/g, img)
								.replace(/##nom##/g, nom)
								.replace(/##description##/g, obj.description || "-");
								
								$("#lsUsrs_"+id).append(res);	
							});
						});
					}
				}
			}
		});
	};

	
//Update du profil
	var updateprofil = function(){
		applyUpdateBtn.disabled = true;
		var obj = {
		nomService : "UpdateUtilisateurService",
			data : {
				numu : uparent,
				nom 	: encodeHtmlEntity($("#nom").val()),
				mdp 	: encodeHtmlEntity($("#mdp").val()),
				email 	: encodeHtmlEntity($("#email").val()),
				description : encodeHtmlEntity($("#description").val()),
				photo : tmpAvatar
			}
		};
		services.call(obj, true).then(function(resp) {
			if(resp){
				if(resp.returnCode === 8 || resp.returnCode === 4){
					services.showErrorAlert("errorZone", resp.message);
				}else{
					services.showErrorAlert("errorZone", resp.message, "success");
 					$("#nom").val('${sessionScope["dejaConnecte"].nom}');
					$("#email").val('${sessionScope["dejaConnecte"].email}');
					$("#description").val('${sessionScope["dejaConnecte"].description}');
					$("#backDetailUsr").fadeOut(0).fadeIn(1);
					
					
				}
			}
		});
};


	

	//Validation avant update profil	
	var validerFormprofil = function(){
		$("#modUserForm").validate({
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
					
					
					
			

	// Permet d'envoyer un message a un autre utilisateur
	var msgUsr = function(id, nom){
		var idgrp = id.split("_")[1];
		var numu = id.split("_")[2];
		var id = getDiscussionID(uparent, numu);
		if(!id){
			id = "disc_"+uparent+"_"+numu;
			var formImg = '<form method="POST" id="sendImgForm_##idDisc##" action="../upload" enctype="multipart/form-data"><input type="file" name="fichier" id="fileImg_##idDisc##" type="file" name="fichier" class="hidden" /></form>';
			var tmpl = formImg+'<div id="##idDisc##" class="col-md-12 discussion"> <div id="entete_##idDisc##" class="col-md-12 entete">##titre##</div> <div class="bas" id="bas_##idDisc##" style="display: none;"> <table class="table corps"> <tr> <td class="col-md-2 participants text-center"></td> <td class="col-md-10 dialog"><div id="dialog_##idDisc##"></div></td> </tr> </table> <div class="col-md-12 input-group pied"> <input type="text" class="form-control" id="msg_##idDisc##" placeholder="Votre message ici . . ."> <span class="input-group-btn"> <button id="envoyerImg_##idDisc##" onclick="envoyerImg(##idDisc##)" class="btn btn-default" type="button"> <span class="glyphicon glyphicon-picture"></span> </button> <button id="envoyerMsg_##idDisc##" onclick="envoyerMsg(msg_##idDisc##)" class="btn btn-default" type="button"> Envoyer </button> </span> </div> </div> </div>';
			var res = tmpl.replace(/##idDisc##/g, id).replace(/##titre##/g, "<h4> <span class='glyphicon glyphicon-align-left'></span> Discussion : Moi - " + nom + "</h4>");
			$("#chatZone").append(res);
			addDialogEvents(id);
		}
		scrollTo("#"+id);
		$("#bas_"+id).slideDown("slow");
		$("#"+id).removeClass("fermee");
		$("#notif_"+id).addClass("hidden");

		
	};
		
	// Pemet de consolter le profil d'un usr
	var viewUsr = function(id){
		var idgrp = id.split("_")[1];
		var numu = id.split("_")[2];
		
		var obj = { nomService : "LireUtilisateur", data : { numu : numu } };
		var infos = {};
		
		var res ="";
		var el;
		services.call(obj, true).then(function(resp) {
			if(resp){
				if(resp.returnCode === 8 || resp.returnCode === 4){
					services.showErrorAlert("errorZoneGrp", resp.message);
				}else{
					infos = resp.response[0];
					
					$.get("tmpl/userDetail.tmpl").success(function(contenu){
						res += contenu.replace(/##usrLogin##/g, infos.login)
						.replace(/##usrPhoto##/g, infos.photo)
						.replace(/##usrNom##/g, infos.nom)
						.replace(/##usrEmail##/g, infos.email)
						.replace(/##usrDescription##/g, infos.description);
					});
					
					//console.log(res);
				}	
			}
			}).then(function(e) {
				var obj = { nomService : "GetListeAmisUtilisateurService", data : { numu : infos.numu  } };
				services.call(obj, true).then(function(respAmis) {
					if(respAmis){
						if(respAmis.returnCode === 8){
							services.showErrorAlert("errorZoneGrp", respAmis.message);
						}else{
							$("#detailZone").html(res);
							if(respAmis.returnCode === 4){
								$("#detailZoneAmis").append("<div class='col-md-12 text-left'><small>"+respAmis.message+"</small></div>");
							}else{
								var amis = respAmis.response[0];
								$.each(respAmis.response, function(i, a){
									$("#detailZoneAmis").append("<div class='col-md-2 amisthumb small'><div class='text-center'><img src='../img/avatars/" +a.photo+ "' /></div><p class='text-center'>"+a.nom+"</p></div>")	;
								});
							}
							$("#myModal").modal();
						}
					}
				});
			});
	};

	// Permet de supprimer un usr
	var delUsr = function(id){
		var obj = {
			nomService : "SupprimerUtilisateurDunGroupeService",
			data : {
				uparent : uparent,
				ufils : id.split("_")[2],
				idgrp : id.split("_")[1]
			}
		};
		services.call(obj, true).then(function(resp) {
			if(resp){
				if(resp.returnCode === 8 || resp.returnCode === 4){
					services.showErrorAlert("errorZoneGrp", resp.message);
				}else{
					getListeGroupes();
				}	
			}
		});
	};
		
		
		
		
	// Permet d'ajouter un usr a un groupe	
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
		services.call(obj, true).then(function(resp) {
			if(resp){
				if(resp.returnCode === 8 || resp.returnCode === 4){
					services.showErrorAlert("errorZoneRecherche", resp.message);
				}else{
					services.showErrorAlert("errorZoneRecherche", resp.message, "success");
					getListeGroupes();
				}	
			}
		});
	};
		
		
		
	
	// Permet de supprimer un usr
	var delGrp = function(idgrp){
		var obj = {
			nomService : "SupprimerGroupeUtilisateurService",
			data : {
				uparent : uparent,
				idgrp : idgrp
			}
		};
		services.call(obj, true).then(function(resp) {
			if(resp){
				if(resp.returnCode === 8 || resp.returnCode === 4){
					services.showErrorAlert("errorZoneGrp", resp.message);
				}else{
					services.showErrorAlert("errorZoneGrp", resp.message, "success");
					getListeGroupes();
				}	
			}
		});
	};	
		
		
	// Permet de creer un groupe
	var creerGrp = function(){		
		 
		 var obj = { nomService : "EnregistrerGroupe", data : { libelle : encodeHtmlEntity($("#libelleGrp").val()) } };
		 var newGrp;
		services.call(obj, true).then(function(resp) {
			if(resp){
				if(resp.returnCode === 8 || resp.returnCode === 4){
					services.showErrorAlert("errorZoneGrp", resp.message);
				}else{
					newGrp = resp.response[0];
					obj = { nomService : "AjouterGroupeUtilisateurService", data : { numu : uparent, idgrp : newGrp.idgrp } };
					services.call(obj, true).then(function(resp) {
						if(resp.returnCode === 8 || resp.returnCode === 4){
							services.showErrorAlert("errorZoneGrp", resp.message);
						}else{
							getListeGroupes();
						}
					});
					
					
				}	
			}
		}); 
	};	
			
	
	
	
	
	
	
	// Gestion de la Recherche
	 var rechercherUtilisateurs = function(){
		var obj = { nomService: "RechercherUtilisateur", data: { nom: $('#nomRecherche').val() } };
		rechercherBtn.prop('disabled', true);	
		var res = "";
		services.call(obj, true).then(function(reponse){
	  		$("#errorZone").html("");
	  		$("#tableUsers").html("");
	  		rechercherBtn.prop('disabled', false);
	  		if(reponse){ 
	   			if(reponse.returnCode == 8)		{ services.showErrorAlert("errorZone", reponse.message); }
	      		else if(reponse.returnCode == 4){ services.showErrorAlert("errorZone", reponse.message, "warning"); autoHeight($("#resultatRechZone")); }
	      		else {
	          		var table=$("#tableUsers");
	          		var res = "<tr><th></th><th class='text-center'>[ LOGIN ]</th><th class='text-center'>[ NOM ]</th><th class='text-center'>[ EMAIL ]</th><th >[ ACTION ]</th></tr>";
	          		$("#tableUsers").append(res);
	          		$.each(reponse.response,function(i,o){
		          		$.get("./tmpl/rowRecherche.tmpl").success(function(contenu){
							res = contenu.replace(/##idUsr##/g, o.numu) .replace(/##login##/g, o.login) .replace(/##nom##/g, o.nom) .replace(/##email##/g, o.email) .replace(/##photo##/g, "../img/avatars/" + o.photo);
							$("#tableUsers").append(res);
							$.each(mesGroupes, function(key, value) {  
					     		$('#sel_'+o.numu) .append($("<option></option>") .attr("value",value.idgrp) .text(value.libelle));
							});
							autoHeight($("#resultatRechZone"));
						});
	          		});
	      		}
	  		}
		})
	};

rechercherBtn.on("click",rechercherUtilisateurs);
$("#nomRecherche").on("keyup", rechercherUtilisateurs );
		
var autoHeight = function autoHeightAnimate(element){
	var curHeight = element.height(), // Get Default Height
	autoHeight = element.css('height', 'auto').height(); // Get Auto Height
	element.height(curHeight); // Reset to Default Height
	element.stop().animate({ height: autoHeight }, 300); // Animate to Auto Height
};		
		 
		
	// Permet de supprimer un usr
	var dernierMsg;
	var getAllMsg = function(){
		var obj = {
			nomService : "RecupererTousLesMessageService",
			data : {
				udest : uparent,
				idmsg : dernierMsg
			}
		};
		services.call(obj, true).then(function(resp) {
			if(resp){
				if(resp.returnCode === 0){
					//$('div[id^="dialog_"]').html("");
					$.each(resp.response,function(i,o){
						var exp = o.uexp;
						var dest = o.udest;
						var date = o.dateenv;
						var id = getDiscussionID(exp,dest);
						if(!id){
							id = "disc_"+exp+"_"+dest;
							var formImg = '<form method="POST" id="sendImgForm_##idDisc##" action="../upload" enctype="multipart/form-data"><input type="file" name="fichier" id="fileImg_##idDisc##" type="file" name="fichier" class="hidden" /></form>';
							var tmpl = formImg+'<div id="##idDisc##" class="col-md-12 discussion fermee"> <div id="entete_##idDisc##" class="col-md-12 entete">##titre##</div><div class="bas" id="bas_##idDisc##" style="display: none;"><table class="table corps"> <tr><td class="col-md-2 participants text-center"><div class="col-md-12"><img src="../img/avatars/'+o.utilisateur.photo+'" alt="'+o.utilisateur.nom+'"/><h4>'+o.utilisateur.nom+'</h4></div><div class="col-md-12"><img src="../img/avatars/${sessionScope["dejaConnecte"].photo}" alt="${sessionScope["dejaConnecte"].nom}"/><h4>${sessionScope["dejaConnecte"].nom}</h4></div></td><td class="col-md-10 dialog"><div id="dialog_##idDisc##"></div></td></tr> </table> <div class="col-md-12 input-group pied"> <input type="text" class="form-control" id="msg_##idDisc##" placeholder="Votre message ici . . ."> <span class="input-group-btn"><button id="envoyerImg_##idDisc##" onclick="envoyerImg(##idDisc##)" class="btn btn-default" type="button"> <span class="glyphicon glyphicon-picture"></span> </button><button id="envoyerMsg_##idDisc##" onclick="envoyerMsg(msg_##idDisc##)" class="btn btn-default" type="button"> Envoyer </button></span> </div></div></div>';
							var res = tmpl.replace(/##idDisc##/g, id).replace(/##titre##/g, "<div class='col-md-10'><h4> <span class='glyphicon glyphicon-align-left'></span> Discussion : Moi - " + o.utilisateur.nom + "</h4></div><div id='notif_"+id+"' class='col-md-2 hidden'><i class='badge'><span class='glyphicon glyphicon-bell'></span> Nouveau message ! </i></div>");
							$("#chatZone").append(res);
							addDialogEvents(id);

						}
						var msg = "";
						if(exp == uparent){
							msg = "<div class='col-md-12' id='msg_"+o.idmsg+"'><div class='col-md-6 alert mesMsg pull-right'><strong>Moi : </strong><small>"+date +"</small><hr/>"+ o.contenu + "</div></div>"
						}else{
							msg = "<div class='col-md-12' id='msg_"+o.idmsg+"'><div class='col-md-6 alert autreMsg'><strong>"+o.utilisateur.nom+" : </strong><small>"+date +"</small><hr/>"+ o.contenu + "</div></div>"
						}
						$("#dialog_"+id).append(msg);
						$('#msg_'+o.idmsg).hide().fadeIn("slow");
						if($("#"+id).hasClass("fermee")){
							$("#notif_"+id).toggleClass("hidden");
						}
						dernierMsg = o.idmsg;
					});
				}
			}
		});
		//console.log("ID dernier Message = " + dernierMsg);
	};
	
	var addDialogEvents = function(id){
		$("#entete_"+id).on("click", function() {
			getAllMsg();
			$("#"+id).toggleClass("fermee");
			$("#notif_"+id).addClass("hidden");
			$("#bas_"+id).slideToggle("slow", function(){
				scrollTo("#msg_"+id);
				
			});
		});
		$("#msg_"+id).keypress(function(e) {
			if(e.which == 13) {
				envoyerMsg(document.getElementById("msg_"+id));
			}
			
		});
		
		$('#sendImgForm_'+id).on('submit', function (e) {
		  e.preventDefault();
			
		  
		  
	 	  var $form = $(this);
		  var formdata = (window.FormData) ? new FormData($form[0]) : null;
		  var data = (formdata !== null) ? formdata : $form.serialize();

		  $.ajax({
		      url: $form.attr('action'),
		      type: $form.attr('method'),
		      contentType: false,
		      processData: false,
		      data: data,
		      success: function (newImage) {
		      	var idModal = newImage+"_Modal";
		      	var btn = '<button type="button" id="img_'+newImage+'" class="btn btn-default" onclick="showImg(this)" ><img src="../img/avatars/' +newImage+ '"/></button>';
		      	$("#msg_"+id).val(btn);
		      	envoyerMsg(document.getElementById("msg_"+id), true);

		      	
		      					//console.log(document.getElementById("viewImg"));		      	
		      }
		  });
		});
		
		$("#fileImg_"+id).on("change", function() {
			if(event.target.files.length){
				var fimg = event.target.files[0].name;
				$('#sendImgForm_'+id).submit();
			}
		});
	};
	
	
	
	var showImg = function(el){
		var id = el.id;
		var img = id.substr(4);
		$("#prevImg").attr("src", "../img/avatars/" + img);
		$("#myImgModal").modal();
	};
	
	
	
	var getDiscussionID = function(e,d){
		var id = "disc_"+e+"_"+d;
		var di = "disc_"+d+"_"+e;
		
		if($("#"+id).length){ return id; }
		if($("#"+di).length){ return di; }
		return null;
	};
		
		
	var envoyerImg = function(e){
		var id = e.id;
		
		$("#fileImg_"+id).click();
		

		
		
	};
		
	

	
	
	var envoyerMsg = function(e, ignoreHtml){
		var id = e.id;
		var udest = id.split("_")[3] === uparent ? id.split("_")[2] : id.split("_")[3];
		var obj = {
			nomService : "EnvoyerMessageUtilisateur",
			data : {
				uexp : uparent,					
				udest : udest,
				contenu : ignoreHtml ? e.value : encodeHtmlEntity(e.value)
				//contenu : encodeHtmlEntity(e.value)
			}
		};
		e.value = "";
		//console.log(obj);
		services.call(obj, true).then(function(resp) {
			
		});
		
	};
	getAllMsg();
	
		
			
	// Selection d'une image pour l'upload
	$("#btnP").on("click", function() {
		$("#btnH").click();
	});

	
	
	
	$("#btnH").on("change", function() {
		$("#fileName").val($("#btnH").val());
		if(event.target.files.length){
			tmpAvatar = event.target.files[0].name;
			$('#myAvatarform').submit();					
		}
	});
	
	
	
	
	

	$("#creerGrpBtn").on("click", function(){
		creerGrp();
		$("#libelleGrp").val("");
		
	});
			
			


	$("#showGrps").on("click", function() {
		$("#showGrps").toggleClass("btn-primary"); 
		$("#mygroups").slideToggle(200, "swing");
	});
	

	
	
	$("#showAddGrp").on("click", function() {
		$("#crGrpDiv").slideToggle(400, "swing");
		if($("#arrow").hasClass("glyphicon-chevron-down")){
			$("#arrow").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
		}else{
			$("#arrow").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		}
	});

	
	
	
	// Flip zone detail utilisateur
	afficherProfilBtn.on("click", function() {
		afficherProfilBtn.toggleClass("btn-primary"); 
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
		rechercherUsrBtn.toggleClass("btn-primary"); 
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
	

	
	
	var uploadImage = function (e) {
	  e.preventDefault();

 	  var $form = $(this);
	  var formdata = (window.FormData) ? new FormData($form[0]) : null;
	  var data = (formdata !== null) ? formdata : $form.serialize();

	  $.ajax({
	      url: $form.attr('action'),
	      type: $form.attr('method'),
	      contentType: false,
	      processData: false,
	      data: data,
	      success: function (newImage) {
	          el.value = "<img src='../img/avatars/'"+newImage+"/>"
	      }
	  });
	};
	
	var changeAvatar = function (e) {
	  e.preventDefault();

	  var $form = $(this);
	  var formdata = (window.FormData) ? new FormData($form[0]) : null;
	  var data = (formdata !== null) ? formdata : $form.serialize();

	  $.ajax({
	      url: $form.attr('action'),
	      type: $form.attr('method'),
	      contentType: false,
	      processData: false,
	      data: data,
	      success: function (newImage) {
	          $("#usrAvatar").attr('src', '../img/avatars/' + newImage);
	      }
	  });
	};
	
	
	
	
	
 	$('#myAvatarform').on('submit', changeAvatar);
 	//$('#sendImgForm').on('submit', uploadImage);
	
	
	$(document).ready(function() {
		$('[data-toggle="popover"]').popover();   
		validerFormprofil();
		getListeGroupes();
		setInterval(getAllMsg, 300);
		
		var offset = 300,
		offset_opacity = 1200,
		$back_to_top = $('.cd-top');

	//hide or show the "back to top" link
	$(window).scroll(function(){
		( $(this).scrollTop() > offset ) ? $back_to_top.fadeIn() : $back_to_top.fadeOut();
	});

	//smooth scroll to top
	$back_to_top.on('click', function(event){
		event.preventDefault();
		$('body,html').animate({ scrollTop: 0 }, 700 );
	});
		
		
	});
	
	
	var scrollTo = function (element){	
		 $('html, body').animate({
       scrollTop: $(element).offset().top
     }, 1000);
	};
	
	
	
</script>





</body>
</html>