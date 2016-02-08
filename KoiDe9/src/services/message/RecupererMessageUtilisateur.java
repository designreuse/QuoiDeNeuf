package services.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletRequest;

import app.utils.MappingBddToBeans;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.beans.Discussion;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public class RecupererMessageUtilisateur extends AbstractService {

	public RecupererMessageUtilisateur() {
		this.requiredData = new String[]{TabAndCo.USER_TO_USER_UEXP, TabAndCo.USER_TO_USER_UDEST};
	}

	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final int uexp = requestObject.getDataIntValue(TabAndCo.USER_TO_USER_UEXP);
		final int udest = requestObject.getDataIntValue(TabAndCo.USER_TO_USER_UDEST);
		
		final StringBuilder req = new StringBuilder("SELECT u.idmsg, dateenv, uexp, udest, contenu from messages m join usertouser u on m.idmsg = u.idmsg where (u.uexp = ? and u.udest = ?) or (u.uexp = ? and u.udest = ?) order by m.dateenv asc;");
		final Connection connection = ServiceUtils.getConnection();
		
		try {
			final PreparedStatement pst = connection.prepareStatement(req.toString());
			pst.setInt(1, uexp);
			pst.setInt(2, udest);
			pst.setInt(3, udest);
			pst.setInt(4, uexp);
						
			final ResultSet rs = pst.executeQuery();
			final List<Discussion> liste = MappingBddToBeans.resultsetToListDiscussion(rs);			
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "", liste);
		} catch (SQLException e) {
			ServiceUtils.logger.error("Erreur exec SQL");
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Erreur exec SQL.", null);
			e.printStackTrace();
		}finally {
			try { connection.close(); }
			catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
		}

		return this.responseObject;
	}

}
