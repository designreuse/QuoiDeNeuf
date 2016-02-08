package services.objects;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import app.utils.ServiceUtils;

public class RequestObject {

   	private String nomService;
	private JsonObject data;
	private String rawJson;
	
	
	public String getNomService() { return nomService; } 
	public JsonObject getData() { return data; } 
	
	public void setNomService(String nomService) { this.nomService = nomService; } 
	public void setData(JsonObject data) { this.data = data; } 
	
	public String getRawJson() { return rawJson; } 
	public void setRawJson(String rawJson) { this.rawJson = rawJson; }
	
	

	@Override
	public String toString() {
		return "RequestObject [nomService=" + nomService + ", data=" + data + ", rawJson=" + rawJson + "]";
	}
	
	
	public static RequestObject jsonToRequestObject(final String userDataJson) {
		final Gson gson = new Gson();
		final RequestObject requestObject = gson.fromJson(userDataJson, RequestObject.class);
		if(requestObject != null){
			requestObject.setRawJson(userDataJson);
		} 
		return requestObject;
	}
	
	public String getDataStringValue(String key) {
		final JsonElement el = this.data.get(key);
		try {
			return el.getAsString();
		}catch(UnsupportedOperationException e){
			ServiceUtils.logger.error("Probleme de cast " + e);
		}
		return "";
		
	}
	
	public int getDataIntValue(String key) {
		final JsonElement el = this.data.get(key);
		if(el==null){return -1;}
		try {
			
			return Integer.parseInt(el.getAsString());
		}catch(UnsupportedOperationException e){
			ServiceUtils.logger.error("Probleme de cast " + e);
		}catch(NumberFormatException e){
			ServiceUtils.logger.error("Probleme de parseInt " + e);
		}
		return -1;
	}
	
}
