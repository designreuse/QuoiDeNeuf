package services.core;

import services.utilisateur.EnregistrerUtilisateurService;
import services.utilisateur.VerifierUtilisateurService;

public class ServiceFactory {

    private static AbstractService instance = null;

    public static AbstractService getServiceInstance(final String nomService){
        if(instance == null){
        	instance = createService(nomService);
        }
        return instance;
    }


    private static AbstractService createService(final String nomService){
        switch(nomService){
            case "EnregistrerUtilisateur"	: return new EnregistrerUtilisateurService();
            case "VerifierUtilisateur"		: return new VerifierUtilisateurService();
            
            default : return null;
                
        }
    }
	
	
	
	
}
