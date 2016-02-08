package services.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletRequest;

import app.utils.MappingBddToBeans;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.beans.Message;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public class EnvoyerMessageUtilisateur extends AbstractService {

	public EnvoyerMessageUtilisateur() {
		 this.requiredData = new String[]{TabAndCo.USER_TO_USER_UEXP, TabAndCo.USER_TO_USER_UDEST, TabAndCo.MESSAGES_CONTENU};
	}
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final String contenu = requestObject.getDataStringValue(TabAndCo.MESSAGES_CONTENU);
		final int uexp = requestObject.getDataIntValue(TabAndCo.USER_TO_USER_UEXP);
		final int udest = requestObject.getDataIntValue(TabAndCo.USER_TO_USER_UDEST);
		
		if(uexp == udest){
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Un utilisateur ne peut envoyer un message à lui meme.", null);
			return this.responseObject;
		}
		
		
		final Connection connection = ServiceUtils.getConnection();
	       
		try {
			// lock de la table messages
			final StringBuilder lock = new StringBuilder("LOCK TABLE messages IN EXCLUSIVE MODE");
			connection.setAutoCommit(false);
			final Statement stmt = connection.createStatement();
			stmt.execute(lock.toString());
			// Insert du message
	        final PreparedStatement pst1 = connection.prepareStatement("INSERT INTO messages (contenu) VALUES (?);");
	        pst1.setString(1, contenu);
	        pst1.executeUpdate();
	        
	        // Recuperer l'idmsg
	        final Statement st = connection.createStatement();
	        final ResultSet rs = st.executeQuery("Select max(idmsg) as idmsg from messages;");
	        if(rs.next()){
	        	final int idmsg = rs.getInt(TabAndCo.MESSAGES_IDMSG);
	        	final ResultSet rs2 = st.executeQuery("Select * from messages WHERE idmsg = " +idmsg+ ";");
	        	final List<Message> liste = MappingBddToBeans.resultsetToListMessage(rs2);
		        
		        final PreparedStatement pst2 = connection.prepareStatement("INSERT INTO usertouser VALUES (?,?,?);");
		        pst2.setInt(1, uexp);
		        pst2.setInt(2, idmsg);
		        pst2.setInt(3, udest);
		        pst2.executeUpdate();        	
		        connection.commit();
	        	this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "", liste);
	        	//connection.setAutoCommit(true);
	        }
		} catch (SQLException e) {
			try { connection.rollback(); } 
			catch (SQLException e1) { 
				ServiceUtils.logger.error("Erreur lors du ROLLBACK");
				e1.printStackTrace(); 
			}
            ServiceUtils.logger.error("Erreur SQLException");
            e.printStackTrace();
        }finally {
            try { connection.close(); }
            catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
        }
		return this.responseObject;
	
	}

	
	
	
	
}
