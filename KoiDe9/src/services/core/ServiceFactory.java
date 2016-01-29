package services.core;

import services.utilisateur.EnregistrerUtilisateurService;
import services.utilisateur.LireUtilisateurService;
import services.utilisateur.RechercherUtilisateurService;
import services.utilisateur.VerifierUtilisateurService;

public class ServiceFactory {

    private static AbstractService instance = null;

    public static AbstractService getServiceInstance(final String nomService){
        if(instance == null){
        	instance = createService(nomService);
        }
        return createService(nomService);
    }


    private static AbstractService createService(final String nomService){
        switch(nomService){
            case "EnregistrerUtilisateur"	: return new EnregistrerUtilisateurService();
            case "VerifierUtilisateur"		: return new VerifierUtilisateurService();
            case "LireUtilisateur"			: return new LireUtilisateurService();
            case "RechercherUtilisateur"	: return new RechercherUtilisateurService();
            
            default : return null;
                
        }
    }
	
	
	
	
}
