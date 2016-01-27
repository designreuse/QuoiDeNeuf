package services.objects;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
		requestObject.setRawJson(userDataJson);
		return requestObject;
	}
	
	public String getDataStringValue(String key) {
		final JsonElement el = this.data.get(key);
		
		return el == null ? "" : el.getAsString();
		
	}
	
	public int getDataIntValue(String key) {
		final JsonElement el = this.data.get(key);
		return el == null ? null : Integer.parseInt(el.getAsString());
		
	}
	
}
