$(document).ready(function() {
	$('#modUserForm').submit(false);
	var submitBtn = document.getElementById("submitBtn");

	
	// Verifier l'utilisateur
	var verifierUsr = function(){
		submitBtn.disabled = true;
		var obj = { nomService: "VerifierUtilisateur", data: { login: encodeHtmlEntity($("#login").val()), mdp: encodeHtmlEntity($("#mdp").val()) } };
		
		services.call(obj).then( function(reponse) {
			submitBtn.disabled = false;	
	        
	    	if(reponse){
	    		if(reponse.returnCode == 8){ services.showErrorAlert("errorZone", reponse.message); }
	    		else{ location.replace("./app/main.jsp"); }
	    	}
	   });
	};
	

	// Enreg l'utilisateur
	var enregUsr = function(){
		submitBtn.disabled = true;
		var obj = { 
				nomService: "EnregistrerUtilisateur", 
				data: {
					login	: encodeHtmlEntity($("#login").val()), 
					mdp		: encodeHtmlEntity($("#mdp").val()),
					nom		: encodeHtmlEntity($("#nom").val()) || login,
					email	: encodeHtmlEntity($("#email").val()),
					description	: encodeHtmlEntity($("#description").val())
					
				}
		};
		
		services.call(obj).then(function(reponse) {
			submitBtn.disabled = false;	
	        
			if(reponse){
				if(reponse.returnCode == 8){ 
					services.showErrorAlert("errorZone", reponse.message);
				}else{
	    			services.showErrorAlert("errorZone", reponse.message, "success");
	    			setTimeout(function(){ 
	    				location.replace("./app/main.jsp");	    			
	    			}, 2000);
	    		}
	    }
	        
	  });
	};
	
		
	
	// Validation du formulaire	
	$("#connexionForm").validate({
		rules : {
			login : "required",
			email : { required : true, email : true },
			mdp : { required : true, minlength : 3 }
		},
		messages : {
			login : "Le champ login est requis.",
			email : "Le champ email est requis.",
			mdp : {
				required : "Le champ mot de passe est requis.",
				minlength : "Taille minimale de 3."
			}
		},
		errorClass : "has-error",
		submitHandler : function() {
			if (document.getElementById("chxConnex").checked) {
				verifierUsr();
			} else {
				enregUsr();
			}
		}
	});


	// Evenements
	
	$("#chxConnex").on("click", function(){
		$("#infosDiv").slideUp(400).fadeOut(200);
		submitBtn.value = "Se connecter";
	});
	
	$("#chxInsc").on("click", function() {
		$("#infosDiv").slideDown(400).fadeIn(200);
		submitBtn.value = "S'inscrire";
	});


	
});