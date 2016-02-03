$(document).ready(function() {
	$('#modUserForm').submit(false);
	var submitBtn = $("#submitBtn");

	
	// Verifier l'utilisateur
	var verifierUsr = function(){
		submitBtn.disabled = true;
		var obj = { 
				nomService: "VerifierUtilisateur", 
				data: {
					login: encodeHtmlEntity($("#login").val()), 
					mdp: encodeHtmlEntity($("#mdp").val())
				}
		};
		
		services.call(obj, function(data) {
			submitBtn.disabled = false;	
	    	var reponse = JSON.parse(data.responseText);
	        
	    	if(reponse){
	    		if(reponse.returnCode == 8){
	    			services.showErrorAlert("errorZone", reponse.message);
	    		}else{
	    			location.replace("./app/main.jsp");		    			
	    		}
	    	}
	        
	    });
	};
	
	
	
	// Validation du formulaire	
	$("#connexionForm").validate({
			rules: {
				login: "required",
	        	email: { required: true, email: true },
	        	mdp: { required: true, minlength:3 }
	        },
	      messages: {
	        	login: "Le champ login est requis.",
	        	email: "Le champ email est requis.",
	        	mdp: { required: "Le champ mot de passe est requis.", minlength: "Taille minimale de 3." }
	      },
	      errorClass: "invalid",
	      submitHandler: verifierUsr
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
        
        




		


