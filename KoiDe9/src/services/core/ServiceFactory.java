package services.core;

import services.groupe.EnregistrerGroupeService;
import services.groupe.GetListeUtilisateursGroupeService;
import services.utilisateur.EnregistrerUtilisateurService;
import services.utilisateur.GetListeGroupesUtilisateur;
import services.utilisateur.LireUtilisateurService;
import services.utilisateur.RechercherUtilisateurService;
import services.utilisateur.VerifierUtilisateurService;

public class ServiceFactory {


    public static AbstractService getServiceInstance(final String nomService){
        switch(nomService){
        	case "EnregistrerUtilisateur"	: return new EnregistrerUtilisateurService();
        	case "VerifierUtilisateur"		: return new VerifierUtilisateurService();
        	case "LireUtilisateur"			: return new LireUtilisateurService();
        	case "RechercherUtilisateur"	: return new RechercherUtilisateurService();
        	case "EnregistrerGroupe"		: return new EnregistrerGroupeService();
        	case "GetListeGroupe"			: return new GetListeGroupesUtilisateur();
        	case "GetListeUtilisateursGroupe"	: return new GetListeUtilisateursGroupeService();
        
        	default : return null;
        }
    }


	
	
	
	
}
