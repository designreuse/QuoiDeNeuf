package services.core;

import services.groupe.AjouterGroupeUtilisateurService;
import services.groupe.EnregistrerGroupeService;
import services.groupe.GetListeUtilisateursGroupeService;
import services.groupe.SupprimerGroupeUtilisateurService;
import services.groupe.SupprimerUtilisateurDunGroupeService;
import services.message.EnvoyerMessageGroupeService;
import services.message.EnvoyerMessageUtilisateur;
import services.message.RecupererMessageUtilisateur;
import services.message.RecupererTousLesMessageService;
import services.message.RecupererTousLesMessagesGroupesService;
import services.utilisateur.AjouterUtilisateurDansGroupe;
import services.utilisateur.EnregistrerUtilisateurService;
import services.utilisateur.GetListeAmisUtilisateurService;
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
        	case "GetListeAmisUtilisateurService"	: return new GetListeAmisUtilisateurService();
        	case "SupprimerGroupeUtilisateurService"	: return new SupprimerGroupeUtilisateurService();
        	case "EnvoyerMessageUtilisateur"	: return new EnvoyerMessageUtilisateur();
        	case "RecupererMessageUtilisateur"	: return new RecupererMessageUtilisateur();
        	case "RecupererTousLesMessageService"	: return new RecupererTousLesMessageService();
        	case "EnvoyerMessageGroupeService"	: return new EnvoyerMessageGroupeService();
        	case "RecupererTousLesMessagesGroupesService"	: return new RecupererTousLesMessagesGroupesService();
        
        	default : return null;
        }
    }


	
	
	
	
}
