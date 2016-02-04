package services.core;

import services.groupe.AjouterGroupeUtilisateurService;
import services.groupe.EnregistrerGroupeService;
import services.groupe.GetListeUtilisateursGroupeService;
import services.groupe.SupprimerUtilisateurDunGroupeService;
import services.utilisateur.AjouterUtilisateurDansGroupe;
import services.utilisateur.EnregistrerUtilisateurService;
import services.utilisateur.GetListeGroupesUtilisateur;
import services.utilisateur.LireUtilisateurService;
import services.utilisateur.RechercherUtilisateurService;
import services.utilisateur.VerifierUtilisateurService;

public class ServiceFactory {


    public static AbstractService getServiceInstance(final String nomService){
        switch(nomService){
        	case "EnregistrerUtilisateur"			: return new EnregistrerUtilisateurService();
        	case "VerifierUtilisateur"				: return new VerifierUtilisateurService();
        	case "LireUtilisateur"					: return new LireUtilisateurService();
        	case "RechercherUtilisateur"			: return new RechercherUtilisateurService();
        	case "EnregistrerGroupe"				: return new EnregistrerGroupeService();
        	case "GetListeGroupe"					: return new GetListeGroupesUtilisateur();
        	case "GetListeUtilisateursGroupe"		: return new GetListeUtilisateursGroupeService();
        	case "AjouterUtilisateurDansGroupe"		: return new AjouterUtilisateurDansGroupe();
        	case "AjouterGroupeUtilisateurService"	: return new AjouterGroupeUtilisateurService();
        	case "SupprimerUtilisateurDunGroupeService"	: return new SupprimerUtilisateurDunGroupeService();
        
        	default : return null;
        }
    }


	
	
	
	
}
