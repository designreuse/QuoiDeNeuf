package services.groupe;

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

public class EnregistrerGroupeService extends AbstractService {

	public EnregistrerGroupeService() {
		this.requiredData = TabAndCo.getRequiredFields(TabAndCo.TABLE_GROUPS);
	}
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		
		final String libelle = requestObject.getDataStringValue(TabAndCo.GROUPS_LIBELLE);
		
		final Connection connection = ServiceUtils.getConnection();
		
		try {
			final StringBuilder req1 = new StringBuilder("SELECT * FROM groups WHERE libelle = ?; ");
			PreparedStatement pst = connection.prepareStatement(req1.toString());
			pst.setString(1, libelle);
			final ResultSet rs = pst.executeQuery();
			
			List<Groupe> liste = MappingBddToBeans.resultsetToListGroupe(rs);
			if(liste.isEmpty()){
				final StringBuilder req2 = new StringBuilder("INSERT INTO groups (libelle) VALUES (?); ");
				final PreparedStatement pst2 = connection.prepareStatement(req2.toString());
				
				pst2.setString(1, libelle);
				pst2.executeUpdate();
				
				
				final StringBuilder sReq = new StringBuilder("SELECT * FROM groups WHERE libelle = ?;");
				final PreparedStatement pst3 = connection.prepareStatement(sReq.toString());
				pst3.setString(1, libelle);
				
				final ResultSet rs2 = pst.executeQuery();
				liste = MappingBddToBeans.resultsetToListGroupe(rs2);
				
			}
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Groupe "+ libelle + " créé.", liste);
		
		} catch (SQLException e) {
			ServiceUtils.logger.error("Erreur SQLException");
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Erreur SQLException", null);
			e.printStackTrace();
		}finally {
			try { connection.close();
			} catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
		}
	

		
		return this.responseObject;
	}



}
