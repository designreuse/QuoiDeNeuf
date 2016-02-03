package services.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletRequest;

import app.utils.MappingBddToBeans;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.beans.Groupe;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public class GetListeGroupesUtilisateur extends AbstractService {
	
	public GetListeGroupesUtilisateur() {
		this.requiredData = new String[]{TabAndCo.USERGROUPS_NUMU};
	}
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final int numu = requestObject.getDataIntValue(TabAndCo.USERGROUPS_NUMU);
		
		final StringBuilder req = new StringBuilder("SELECT g.idgrp, libelle FROM groups g, usergroups u WHERE g.idgrp = u.idgrp AND u.numu = ? ORDER BY g.libelle ASC;");
				
		final Connection connection = ServiceUtils.getConnection();
				
		try {
			final PreparedStatement pst = connection.prepareStatement(req.toString());
			pst.setInt(1, numu);
			
			final ResultSet rs = pst.executeQuery();
			final List<Groupe> liste = MappingBddToBeans.resultsetToListGroupe(rs);
			if(liste.size() == 0){
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_WARNING, "Aucun groupe trouvé pour cet identifiant.", liste);			
			}else{
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "", liste);
			}
			
		}catch (SQLException e) {
			ServiceUtils.logger.error("Erreur SQLException");
			e.printStackTrace();
		}finally {
			try { connection.close(); } 
			catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
		}
		
		
		return this.responseObject;
	}

}
