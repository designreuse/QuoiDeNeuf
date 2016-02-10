package services.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.utils.BddUtils;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.beans.Utilisateur;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public class UpdateUtilisateurService extends AbstractService {

	public UpdateUtilisateurService() {
		super();
		this.requiredData =  new String[]{TabAndCo.USERS_NUMU};
	}

    @Override
    public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
        final String numu = requestObject.getDataStringValue(TabAndCo.USERS_NUMU);
        
        // Verifier si le login est deja utilise
        boolean existeDeja = BddUtils.existeDans("users", TabAndCo.USERS_NUMU, numu);
        if(!existeDeja){
            this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Cette utilisateur n'existe pas dans la base (" + numu + ")."  , null);
        }else{
            // Creer un utilisateur
            // Associer les groupes par defaut
            
			final HttpServletRequest httpServReq = (HttpServletRequest) servletRequest;
			final HttpSession session = httpServReq.getSession();
        	Utilisateur utilisateur = (Utilisateur) session.getAttribute("dejaConnecte");
            
            
            final Connection connection = ServiceUtils.getConnection();
            final StringBuilder req = new StringBuilder("update users set mdp = md5(?), nom = ?, email = ?, description = ? , photo = ? where numu = ?;" );
            
            String mdp = requestObject.getDataStringValue(TabAndCo.USERS_MDP);
            
            System.out.println("MOT DE PASSE : " + utilisateur.getMdp());
            
            String nom = requestObject.getDataStringValue(TabAndCo.USERS_NOM);
            
            if(nom == null || nom.isEmpty()){ nom = utilisateur.getLogin(); }
            
            String email = requestObject.getDataStringValue(TabAndCo.USERS_EMAIL);
            if(email == null || email.isEmpty()){ email = utilisateur.getEmail(); }

            
            String desc = requestObject.getDataStringValue(TabAndCo.USERS_DESCRIPTION);
            if(desc == null || desc.isEmpty()){ desc = utilisateur.getDescription(); }
            
            String  photo = requestObject.getDataStringValue(TabAndCo.USERS_PHOTO);
            if(photo == null || photo.isEmpty()){ photo = utilisateur.getPhoto().isEmpty() ? "default.png" : utilisateur.getPhoto(); }
            
            
            try {
                final PreparedStatement pst = connection.prepareStatement(req.toString());
                
                pst.setString(1, mdp);
                pst.setString(2, nom);
                pst.setString(3, email);
                pst.setString(4, desc);
                pst.setString(5, photo);
                pst.setInt(6, requestObject.getDataIntValue(TabAndCo.USERS_NUMU));
                
                pst.executeUpdate();
                this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur modifié avec succès.", null);
                
                utilisateur.setNom(nom);
                utilisateur.setMdp(mdp);
                utilisateur.setEmail(email);
                utilisateur.setDescription(desc);
                utilisateur.setPhoto(photo);
                               
                session.setAttribute("dejaConnecte", utilisateur);
            } catch (SQLException e) {
                ServiceUtils.logger.error("Erreur exec SQL");
                e.printStackTrace();
            }finally {
                try { connection.close(); }
                catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
            }
        }
        return responseObject;
    }

}
