// Update du profile	
var updateProfile = function(){
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
var validerFormProfile = function(){
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
				submitHandler : updateProfile
			})
}; 	
				
				
				
		


	

	
	

	
	
	
		
		
	
	validerFormProfile();
	getListeGroupes();

		

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
							$("#detailZone").slideToggle(800, "swing");
						});
						
					}	
				}
			}, true);

		
	};
	
	var delUsr = function(id){
		var idgrp = id.split("_")[1];
		var numu = id.split("_")[2];
		alert("view de l'utilisateur " +numu+ " du groupe " + idgrp);
	};
	
	
	
	
	
	
		
		// Selection d'une image pour l'upload
		$("#btnP").on("click", function() {
			$("#btnH").click();
		});

		$("#btnH").on("change", function() {
			$("#fileName").val($("#btnH").val());
		});

		
		
		// Flip zone detail utilisateur
		$("#flipbox").on("click", function() {
			$("#card").toggleClass("flipped");
			$("#back").slideToggle(200);
			$("#front").toggle(400);
			$("#flipbox").val(function(i, text) {
				return text === "Voir profile" ? "Retour" : "Voir profile";
			})
			//$("#flipbox").slideToggle(300).slideToggle(100);;
		});
		
		$("#showGrps").on("click", function() {
			$("#mygroups").slideToggle(200);
			$("#showGrps").val(function(i, text) {
				return text === "Afficher les groupes" ? "Cacher les groupes" : "Afficher les groupes";
			})
		});
		
		
		

		