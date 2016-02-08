package services.beans;

import java.sql.Timestamp;

public class Message {

	private int idmsg;
	private Timestamp dateenv;
	private String message;
	
	
	public Message() {
		super();
	}
	
	public Message(int idmsg, Timestamp dateenv, String message) {
		super();
		this.idmsg = idmsg;
		this.dateenv = dateenv;
		this.message = message;
	}
	
	public int getIdmsg() {
		return idmsg;
	}
	public Timestamp getDateenv() {
		return dateenv;
	}
	public String getMessage() {
		return message;
	}
	public void setIdmsg(int idmsg) {
		this.idmsg = idmsg;
	}
	public void setDateenv(Timestamp dateenv) {
		this.dateenv = dateenv;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
