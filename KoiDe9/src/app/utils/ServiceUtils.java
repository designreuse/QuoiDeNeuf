package app.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;


public class ServiceUtils {

	final public static Logger logger = Logger.getLogger(ServiceUtils.class);
	
	public static Connection getConnection(){
		final Context initCtx;
		try {
			initCtx = new InitialContext();
			final Context envCtx = (Context) initCtx.lookup("java:comp/env");
			final DataSource ds = (DataSource) envCtx.lookup("mabase");
			return  ds.getConnection();
		} catch (NamingException e) {
			logger.fatal("==> NamingException");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.fatal("==> SQLException");
			e.printStackTrace();
		}
		return null;
	}
	

	
	

	
	
	
	
	

	
	
	
}
